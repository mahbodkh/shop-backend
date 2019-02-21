package app.store.service.io;///*
// * Created by Amir Moradabadi on 2017/11/7 with love.
// */
//
//package app.store.util.io;
//
//import com.vasl.vaslapp.common.global.ConfigReader;
//import com.vasl.vaslapp.common.global.general.ConfigReaderDatabase;
//import com.vasl.vaslapp.common.global.general.FileManager;
//import com.vasl.vaslapp.common.global.model.FileSave;
//import org.springframework.web.multipart.MultipartFile;
//
//
//public class FileManagerImage extends FileManager {
//
//    MultipartFile cover;
//    String appid;
//    String imageId;
//
//    FileSave coverFileSave = null;
//    String cover_file_extension = "jpg";
//
//    String path_base = null;
//    String path_main = null;
//
//    String base_dir = null;
//    private static final String prefixCover = "";
//
//    public FileManagerImage(MultipartFile cover, String appid, String imageId) {
//        this.cover = cover;
//        this.appid = appid;
//        this.imageId = imageId;
//
//        init();
//    }
//
//    private void init() {
//        String pazh_cover_files_extension = ConfigReader.getConfig().getString("pazh_cover_files_extension");
//        if (!General.isEmpty(pazh_cover_files_extension))
//            cover_file_extension = pazh_cover_files_extension;
//
//
//        path_base = "/Users/vasl-01/Desktop";
////        path_base = getPathBase(appid);
//        path_main = getPathPazhImage(appid).replace("[APPID]", appid).replace("[IMAGEID]", imageId);
//
//        path_main = General.addSlash(path_main);
//
//        base_dir = General.makeDir(path_base + path_main);
//    }
//
//
//    public MultipartFile getCover() {
//        return cover;
//    }
//
//    public void setCover(MultipartFile cover) {
//        this.cover = cover;
//    }
//
//    public String getAppid() {
//        return appid;
//    }
//
//    public void setAppid(String appid) {
//        this.appid = appid;
//    }
//
//    public String getImageId() {
//        return imageId;
//    }
//
//    public void setImageId(String imageId) {
//        this.imageId = imageId;
//    }
//
//    public FileSave getCoverFileSave() {
//        return coverFileSave;
//    }
//
//    public void setCoverFileSave(FileSave coverFileSave) {
//        this.coverFileSave = coverFileSave;
//    }
//
//    public String getCover_file_extension() {
//        return cover_file_extension;
//    }
//
//    public void setCover_file_extension(String cover_file_extension) {
//        this.cover_file_extension = cover_file_extension;
//    }
//
//    public String getPath_base() {
//        return path_base;
//    }
//
//    public void setPath_base(String path_base) {
//        this.path_base = path_base;
//    }
//
//    public String getPath_main() {
//        return path_main;
//    }
//
//    public void setPath_main(String path_main) {
//        this.path_main = path_main;
//    }
//
//    public String getBase_dir() {
//        return base_dir;
//    }
//
//    public void setBase_dir(String base_dir) {
//        this.base_dir = base_dir;
//    }
//
//    public static String getPrefixCover() {
//        return prefixCover;
//    }
//
//    public long getCoverSize() {
//        long size = 0l;
//        if (cover != null)
//            size = cover.getSize();
//        return size;
//    }
//
//    public boolean hasCover() {
//        return (cover != null);
//    }
//
//    public String getOriginalCoverExtention() {
//        String result = null;
//        if (hasCover()) {
//            result = General.getExtension(cover.getOriginalFilename());
//            if (General.isEmpty(result)) {
//                result = cover_file_extension;
//            }
//        }
//        return result;
//    }
//
//    public FileSave savePostTrackCoverPrepair() {
//
//        String savefile_extension = General.getExtension(cover == null ? "" : cover.getOriginalFilename());
//        if (savefile_extension.isEmpty())
//            savefile_extension = cover_file_extension;
////        FileSave fileSavePrepair;
//        String randNumber = General.parseString(General.randInt(1000000, 9999999));
//
//
//        String savefilename = General.parseString(prefixCover) + randNumber + "." + savefile_extension;
//        String savetofile_path = base_dir + savefilename;
//        String savetofile_url = path_main + savefilename + "?rnd=" + General.randInt(1000, 9999);
//
//        coverFileSave = new FileSave(false, savefilename, savetofile_path, savetofile_url);
//        return coverFileSave;
//    }
//
//    public FileSave savePostTrackCoverFile() {
//
//        String originalFileExt = General.getExtension(cover.getOriginalFilename());
//
//        String image_savefilename = General.parseString(prefixCover) + General.parseString(General.randInt(1000000, 9999999)) + "." + (General.isEmpty(originalFileExt) ? cover_file_extension : originalFileExt.toLowerCase());
//        String image_savetofile_path = base_dir + image_savefilename;
//        General.makeDir2(image_savetofile_path);
//        boolean filesaved = saveFile(cover, image_savetofile_path);
//        String image_savetofile_url = null;
//        if (filesaved)
//            image_savetofile_url = path_main + image_savefilename + "?rnd=" + General.randInt(1000, 9999);
//
//        coverFileSave = new FileSave(filesaved, image_savefilename, image_savetofile_path, image_savetofile_url);
//
//        return coverFileSave;
//    }
//
//    public FileSave convertCoverType(boolean deleteAfterConvert) {
//
//        return General.getImageConvertorFileSave(coverFileSave, deleteAfterConvert, cover_file_extension, path_main, base_dir);
//    }
//
//
//    private static String getPathPazhImage(String appid) {
//        String path = ConfigReaderDatabase.getSettingsInMongo(appid).getString("path_pazh_image");
//        if (path == null)
//            path = ConfigReader.getConfig().getString("path_pazh_image");
//        return path;
//    }
//
//}
