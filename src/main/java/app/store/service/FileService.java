package app.store.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    private final Logger log = LoggerFactory.getLogger(FileService.class);

    private final ProductService productService;
    private final ConfigurationService configurationService;

    public FileService(ProductService productService, ConfigurationService configurationService) {
        this.productService = productService;
        this.configurationService = configurationService;
    }




    public void uploadProduct(MultipartFile[] file, String productId) {
        productService.getProductAndReturn(productId)
                .ifPresent(product -> {

                });


        for (MultipartFile multipartFile : file) {
//            if (multipartFile.getResource().isFile())
//                "xa";
        }


    }
}
