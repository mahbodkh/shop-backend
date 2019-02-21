package app.store.service.io;//package app.store.util.io;
//
//
//import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.lang.ArrayUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//public class FileManagerFile extends FileManager {
//
//
//    private MultipartFile file;
//    private String appid;
//    private String fileId;
//
//    private FileSave fileSave = null;
//
//    private String path_base = "";
//    private String path_main = "";
//    private String base_dir = "";
//    private String originalFileExt = "";
//    private static final String prefixFile = "pazh_";
//
//    private String[] post_audio_file_extention = ConfigReader.getConfig().getString("pazh_upload_audio_extensions").split(",");
//    private String[] post_image_file_extention = ConfigReader.getConfig().getString("pazh_upload_image_extensions").split(",");
//    private String[] post_video_file_extention = ConfigReader.getConfig().getString("pazh_upload_video_extensions").split(",");
//
//    public FileManagerFile(MultipartFile file, String appid, String fileId) {
//        this.file = file;
//        this.appid = appid;
//        this.fileId = fileId;
//        init();
//    }
//
//    private void init() {
//        if (post_image_file_extention != null || post_audio_file_extention != null || post_video_file_extention != null) {
//            originalFileExt = FilenameUtils.getExtension(file.getOriginalFilename());
//        }
//        path_base = "";
//        path_main = "";
//        path_main = General.addSlash(path_main);
//        base_dir = General.makeDir(path_base + path_main);
//    }
//
//    private FileSave saveFilePrepare() {
//
//        String savefile_extension = getExtension(file);
//        assert savefile_extension != null;
//        if (savefile_extension.isEmpty())
//            savefile_extension = originalFileExt;
//        String randNumber = General.parseString(General.randInt(1000000, 9999999));
//
//        if (ArrayUtils.contains(post_image_file_extention, originalFileExt)) {
//            //todo
//        } else if (ArrayUtils.contains(post_audio_file_extention, originalFileExt)) {
//            String saveAudioName = General.parseString(prefixFile) + randNumber + "." + savefile_extension;
//            String savetoaudio_path = base_dir + saveAudioName;
//            String savetoaudio_url = path_main + saveAudioName + "?rnd=" + General.randInt(1000, 9999);
//            fileSave = new FileSave(false, saveAudioName, savetoaudio_path, savetoaudio_url);
//            return fileSave;
//        } else if (ArrayUtils.contains(post_video_file_extention, originalFileExt)) {
//            String saveVideoName = General.parseString(prefixFile) + randNumber + "." + savefile_extension;
//            String savetofile_path = base_dir + saveVideoName;
//            String savetofile_url = path_main + saveVideoName + "?rnd=" + General.randInt(1000, 9999);
//            fileSave = new FileSave(false, saveVideoName, savetofile_path, savetofile_url);
//            return fileSave;
//        }
//        //todo
//        return null;
//    }
//
//
//    public String getExtension(MultipartFile file) {
//        return FilenameUtils.getExtension(file == null ? "" : file.getOriginalFilename());
//    }
//
//    public long getFileSize() {
//        long size = 0l;
//        if (file != null)
//            size = file.getSize();
//        return size;
//    }
//
//    public boolean hasFile() {
//        return (file != null);
//    }
//
//
//    public MultipartFile getFile() {
//        return file;
//    }
//
//    public void setFile(MultipartFile file) {
//        this.file = file;
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
//    public String getFileId() {
//        return fileId;
//    }
//
//    public void setFileId(String fileId) {
//        this.fileId = fileId;
//    }
//}
