package app.store.service.io;///*
// * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
// * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
// * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
// * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
// * Vestibulum commodo. Ut rhoncus gravida arcu.
// */
//
//
//
//import org.springframework.web.multipart.MultipartFile;
//
//
//public class FileManagerVideo extends FileManager {
//
//    private static final String prefixCover = "";
//    MultipartFile file;
//    MultipartFile cover;
//    String appid;
//    String artistId;
//    String trackId;
//    String videoId;
//    boolean file_needs_cover = false;
//    FileSave videoFileSave = null;
//    FileSave coverFileSave = null;
//    String cover_file_extension = "jpg";
//    String video_file_extension = "mp4";
//    String path_base = null;
//    String path_video = null;
//    String path_video_cover = null;
//    String base_dir_video = null;
//    String base_dir_video_cover = null;
//
//    public FileManagerVideo(MultipartFile file, MultipartFile cover, String appid, String artistId, String trackId, String videoId) {
//        this.file = file;
//        this.cover = cover;
//        this.appid = appid;
//        this.artistId = artistId;
//        this.trackId = trackId;
//        this.videoId = videoId;
//
//        init();
//    }
//
//    public FileManagerVideo(MultipartFile file, MultipartFile cover, String appid, String videoId) {
//        this.file = file;
//        this.cover = cover;
//        this.appid = appid;
//        this.videoId = videoId;
//        init();
//    }
//
//    public static String getPrefixCover() {
//        return prefixCover;
//    }
//
//    private static String getPathPazhVideos(String appid) {
//        String path = ConfigReaderDatabase.getSettingsInMongo(appid).getString("path_pazh_video");
//        if (path == null)
//            path = ConfigReader.getConfig().getString("path_pazh_video");
//        return path;
//    }
//
//    private static String getPathPazhVideosCover(String appid) {
//        String path = ConfigReaderDatabase.getSettingsInMongo(appid).getString("path_pazh_videos_cover");
//        if (path == null)
//            path = ConfigReader.getConfig().getString("path_pazh_videos_cover");
//        return path;
//    }
//
//    private void init() {
//        String pazh_cover_files_extension = ConfigReader.getConfig().getString("pazh_cover_files_extension");
//        String pazh_video_files_extension = ConfigReader.getConfig().getString("pazh_video_extension");
//        if (!General.isEmpty(pazh_cover_files_extension))
//            cover_file_extension = pazh_cover_files_extension;
//        if (!General.isEmpty(pazh_video_files_extension))
//            video_file_extension = pazh_video_files_extension;
//
//
//        path_base = "/Users/vasl-01/Desktop";
////        path_main = "";
//
////        path_base = getPathBase(appid);
//        path_video = getPathPazhVideos(appid).replace("[APPID]", appid).replace("[VIDEOID]", videoId);
//        path_video_cover = getPathPazhVideosCover(appid).replace("[APPID]", appid).replace("[VIDEOID]", videoId);
//
//        path_video = General.addSlash(path_video);
//        path_video_cover = General.addSlash(path_video_cover);
//
//        base_dir_video = General.makeDir(path_base + path_video);
//        base_dir_video_cover = General.makeDir(path_base + path_video_cover);
//    }
//
//    public boolean isFile_needs_cover() {
//        return file_needs_cover;
//    }
//
//    public void setFile_needs_cover(boolean file_needs_cover) {
//        this.file_needs_cover = file_needs_cover;
//    }
//
//    public MultipartFile getFile() {
//        return file;
//    }
//
//    public MultipartFile getCover() {
//        return cover;
//    }
//
//    public String getAppid() {
//        return appid;
//    }
//
//    public String getArtistId() {
//        return artistId;
//    }
//
//    public String getTrackId() {
//        return trackId;
//    }
//
//    public String getVideoId() {
//        return videoId;
//    }
//
//    public FileSave getVideoFileSave() {
//        return videoFileSave;
//    }
//
//    public FileSave getCoverFileSave() {
//        return coverFileSave;
//    }
//
//    public String getCover_file_extension() {
//        return cover_file_extension;
//    }
//
//    public String getVideo_file_extension() {
//        return video_file_extension;
//    }
//
//    public String getPath_base() {
//        return path_base;
//    }
//
//    public String getPath_video() {
//        return path_video;
//    }
//
//    public String getPath_video_cover() {
//        return path_video_cover;
//    }
//
//    public String getBase_dir_video() {
//        return base_dir_video;
//    }
//
//    public String getBase_dir_video_cover() {
//        return base_dir_video_cover;
//    }
//
//    public long getFileSize() {
//        long size = 0l;
//        if (file != null)
//            size = file.getSize();
//        return size;
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
//    public String getOriginalCoverExtension() {
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
//    public FileSave savePostVideoFile() {
//
//
//        String music_savefilename = videoId + "." + video_file_extension;
//        String music_savetofile_path = base_dir_video + music_savefilename;
////        Util.makeDir2(music_savetofile_path);
//        boolean filesaved = saveFile(file, music_savetofile_path);
//        String music_savetofile_url = null;
//        if (filesaved)
//            music_savetofile_url = path_video + music_savefilename + "?rnd=" + General.randInt(1000, 9999);
//
//        videoFileSave = new FileSave(filesaved, music_savefilename, music_savetofile_path, music_savetofile_url);
//        return videoFileSave;
//    }
//
//    public FileSave savePostVideoCoverPrepair() {
//
//        String savefile_extension = General.getExtension(cover == null ? "" : cover.getOriginalFilename());
//        if (savefile_extension.isEmpty())
//            savefile_extension = cover_file_extension;
////        FileSave fileSavePrepair;
//        String randNumber = General.parseString(General.randInt(1000000, 9999999));
//
//
//        String savefilename = General.parseString(prefixCover) + randNumber + "." + savefile_extension;
//        String savetofile_path = base_dir_video_cover + savefilename;
//        String savetofile_url = path_video_cover + savefilename + "?rnd=" + General.randInt(1000, 9999);
//
//        coverFileSave = new FileSave(true, savefilename, savetofile_path, savetofile_url);
//        return coverFileSave;
//    }
//
//    public FileSave savePostVideoCoverFile() {
//
//        String originalFileExt = General.getExtension(cover.getOriginalFilename());
//
//        String image_savefilename = General.parseString(prefixCover) + General.parseString(General.randInt(1000000, 9999999)) + "." + (General.isEmpty(originalFileExt) ? cover_file_extension : originalFileExt.toLowerCase());
//        String image_savetofile_path = base_dir_video_cover + image_savefilename;
////        Util.makeDir2(image_savetofile_path);
//        boolean filesaved = saveFile(cover, image_savetofile_path);
//        String image_savetofile_url = null;
//        if (filesaved)
//            image_savetofile_url = path_video_cover + image_savefilename + "?rnd=" + General.randInt(1000, 9999);
//
//        return new FileSave(filesaved, image_savefilename, image_savetofile_path, image_savetofile_url);
//    }
//
//    public FileSave convertPazhVideosCoverType(boolean deleteAfterConvert) {
//        return General.getImageConvertorFileSave(coverFileSave, deleteAfterConvert, cover_file_extension, path_video_cover, base_dir_video_cover);
//    }
//
//    public String getExtension() {
//        return FilenameUtils.getExtension(file.getOriginalFilename());
//    }
//
//    public boolean hasVideo() {
//        return (file != null);
//    }
//
//}
