package app.store.service;

import app.store.persistence.domain.Media;
import app.store.service.error.FileStorageException;
import app.store.service.util.ConfigReader;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {
    private final Logger log = LoggerFactory.getLogger(FileService.class);

    private final ProductService productService;
    private final ConfigurationService configurationService;
    private static Path fileLocation = null;
    private static String PATH_BASE = "";
    private static String PATH_BASE_DIRECTORY = ConfigReader.getString("file_upload_path_base_location");
    private static String PATH_PRODUCT_BASE = ConfigReader.getString("file_upload_image_product_path_base");
    private static String PATH_CATEGORY_BASE = ConfigReader.getString("file_upload_image_category_path_base");


    public FileService(ProductService productService, ConfigurationService configurationService) {
        this.productService = productService;
        this.configurationService = configurationService;

        initialPathPersistence();
    }


    public void initialPathPersistence() {
        PATH_BASE = "/" + PATH_BASE_DIRECTORY + "/" + PATH_PRODUCT_BASE + "/";

        getPathBase();
    }

    private Path getPathBase() {
        return FileSystems.getDefault().getPath("").toAbsolutePath();
    }


    public void uploadProduct(MultipartFile[] files, String productId) {
        Arrays.stream(files)
                .map(file -> {
                    Media media = storeFile(file);
                    productService.updateProductMedia(productId, media);
                    log.debug("the media has been persist", media);
                    return media;
                })
                .collect(Collectors.toList());
    }
    public void uploadProduct(List<MultipartFile> files, String productId) {

    }


    public Media storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            if (fileName.contains(".."))
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);


            Path path = checkDirectoryExist();

            Path targetLocation = Paths.get(getPathBase() + "/" + PATH_BASE + "/" + fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return getMediaPersistence(file, fileLocation);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    private Media getMediaPersistence(MultipartFile file, Path fileLocation) {
        Media mediaFile = new Media();
        mediaFile.setName(file.getOriginalFilename());
        mediaFile.setUrl(fileLocation.toString());
        mediaFile.setExtension(file.getContentType());
        mediaFile.setCapacity(file.getSize());
        mediaFile.setExtension(getExtension(file));
        return mediaFile;
    }


    public String getExtension(MultipartFile file) {
        return FilenameUtils.getExtension(file == null ? "" : file.getOriginalFilename());
    }

    private Path checkDirectoryExist() {
        try {
            return Files.createDirectories(Paths.get(getPathBase() + "/" + PATH_BASE));
        } catch (IOException e) {
            throw new FileStorageException("Could not create directory " + PATH_BASE + ". Please try again!", e);
        }

    }

//    private Resource loadFileAsResource(String fileName) {
//        try {
//            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//            if (resource.exists()) {
//                return resource;
//            } else {
//                throw new FileNotFoundException("File not found " + fileName);
//            }
//        } catch (MalformedURLException ex) {
//            throw new FileNotFoundException("File not found " + fileName, ex);
//        }
//    }


}