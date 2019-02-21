package app.store.service.io;///*
// * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
// * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
// * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
// * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
// * Vestibulum commodo. Ut rhoncus gravida arcu.
// */
//
//package app.store.util.io;
//
//import com.vasl.vaslapp.common.global.ConfigReader;
//import com.vasl.vaslapp.common.global.general.ConfigReaderDatabase;
//import com.vasl.vaslapp.common.global.general.FileManager;
//import com.vasl.vaslapp.common.global.model.FileSave;
//import org.apache.commons.io.FilenameUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//
//public class FileManagerAudio extends FileManager {
//
//    private static final String prefixCover = "";
//    MultipartFile file;
//    MultipartFile cover;
//    String appid;
//    String artistId;
//    String albumId;
//    String audioId;
//    boolean file_needs_cover = false;
//    FileSave audioFileSave = null;
//    FileSave coverFileSave = null;
//    String cover_file_extension = "jpg";
//    String audio_file_extension = "mp3";
//    String path_base = null;
//    String path_audio = null;
//    String path_audio_cover = null;
//    String base_dir_audio = null;
//    String base_dir_audio_cover = null;
//
//    public FileManagerAudio(MultipartFile file, MultipartFile cover, String appid, String artistId, String albumId, String audioId) {
//        this.file = file;
//        this.cover = cover;
//        this.appid = appid;
//        this.artistId = artistId;
//        this.albumId = albumId;
//        this.audioId = audioId;
//
//        init();
//    }
//
//    public FileManagerAudio(MultipartFile file, MultipartFile cover, String appid, String audioId) {
//        this.file = file;
//        this.cover = cover;
//        this.appid = appid;
//        this.audioId = audioId;
//
//        init();
//    }
//
//    public static String getPrefixCover() {
//        return prefixCover;
//    }
//
//    private static String getPathPazhAudios(String appid) {
//        String path = ConfigReaderDatabase.getSettingsInMongo(appid).getString("path_pazh_audio");
//        if (path == null)
//            path = ConfigReader.getConfig().getString("path_pazh_audio");
//        return path;
//    }
//
//    private static String getPathPazhAudiosCover(String appid) {
//        String path = ConfigReaderDatabase.getSettingsInMongo(appid).getString("path_pazh_audios_cover");
//        if (path == null)
//            path = ConfigReader.getConfig().getString("path_pazh_audios_cover");
//        return path;
//    }
//
//    private void init() {
//        String pazh_cover_files_extension = ConfigReader.getConfig().getString("pazh_cover_files_extension");
//        String posts_music_files_extension = ConfigReader.getConfig().getString("pazh_audio_extension");
//        if (!General.isEmpty(pazh_cover_files_extension))
//            cover_file_extension = pazh_cover_files_extension;
//        if (!General.isEmpty(posts_music_files_extension))
//            audio_file_extension = posts_music_files_extension;
//
//
////        path_base = getPathBase(appid);
//        path_base = "/Users/vasl-01/Desktop";
//        path_audio = getPathPazhAudios(appid).replace("[APPID]", appid).replace("[AUDIOID]", audioId);
//        path_audio_cover = getPathPazhAudiosCover(appid).replace("[APPID]", appid).replace("[AUDIOID]", audioId);
//
//
//        path_audio = General.addSlash(path_audio);
//        path_audio_cover = General.addSlash(path_audio_cover);
//
//        base_dir_audio = General.makeDir(path_base + path_audio);
//        base_dir_audio_cover = General.makeDir(path_base + path_audio_cover);
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
//    public String getAlbumId() {
//        return albumId;
//    }
//
//    public String getAudioId() {
//        return audioId;
//    }
//
//    public FileSave getAudioFileSave() {
//        return audioFileSave;
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
//    public String getAudio_file_extension() {
//        return audio_file_extension;
//    }
//
//    public String getPath_base() {
//        return path_base;
//    }
//
//    public String getPath_audio() {
//        return path_audio;
//    }
//
//    public String getPath_audio_cover() {
//        return path_audio_cover;
//    }
//
//    public String getBase_dir_audio() {
//        return base_dir_audio;
//    }
//
//    public String getBase_dir_audio_cover() {
//        return base_dir_audio_cover;
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
//    public FileSave savePostAudioFile() {
//
//
//        String music_savefilename = audioId + "." + audio_file_extension;
//        String music_savetofile_path = base_dir_audio + music_savefilename;
////        Util.makeDir2(music_savetofile_path);
//        boolean filesaved = saveFile(file, music_savetofile_path);
//        String music_savetofile_url = null;
//        if (filesaved)
//            music_savetofile_url = path_audio + music_savefilename + "?rnd=" + General.randInt(1000, 9999);
//
//        audioFileSave = new FileSave(filesaved, music_savefilename, music_savetofile_path, music_savetofile_url);
//        return audioFileSave;
//    }
//
//    public FileSave savePostAudioCoverPrepair() {
//
//        String savefile_extension = General.getExtension(cover == null ? "" : cover.getOriginalFilename());
//        if (savefile_extension.isEmpty())
//            savefile_extension = cover_file_extension;
////        FileSave fileSavePrepair;
//        String randNumber = General.parseString(General.randInt(1000000, 9999999));
//
//
//        String savefilename = General.parseString(prefixCover) + randNumber + "." + savefile_extension;
//        String savetofile_path = base_dir_audio_cover + savefilename;
//        String savetofile_url = path_audio_cover + savefilename + "?rnd=" + General.randInt(1000, 9999);
//
//        coverFileSave = new FileSave(true, savefilename, savetofile_path, savetofile_url);
//        return coverFileSave;
//    }
//
//    public FileSave savePostAudioCoverFile() {
//
//        String originalFileExt = General.getExtension(cover.getOriginalFilename());
//
//        String image_savefilename = General.parseString(prefixCover) + General.parseString(General.randInt(1000000, 9999999)) + "." + (General.isEmpty(originalFileExt) ? cover_file_extension : originalFileExt.toLowerCase());
//        String image_savetofile_path = base_dir_audio_cover + image_savefilename;
////        Util.makeDir2(image_savetofile_path);
//        boolean filesaved = saveFile(cover, image_savetofile_path);
//        String image_savetofile_url = null;
//        if (filesaved)
//            image_savetofile_url = path_audio_cover + image_savefilename + "?rnd=" + General.randInt(1000, 9999);
//
//        return new FileSave(filesaved, image_savefilename, image_savetofile_path, image_savetofile_url);
//    }
//
//    public FileSave convertPazhAudiosCoverType(boolean deleteAfterConvert) {
//        return General.getImageConvertorFileSave(coverFileSave, deleteAfterConvert, cover_file_extension, path_audio_cover, base_dir_audio_cover);
//    }
//
//    public String getOriginalAudioExtention() {
//        String result = null;
//        if (hasAudio()) {
//            result = General.getExtension(file.getOriginalFilename());
//            if (General.isEmpty(result)) {
//                result = audio_file_extension;
//            }
//        }
//        return result;
//    }
//
//    public boolean hasAudio() {
//        return (file != null);
//    }
//
//    public String getExtension() {
//        return FilenameUtils.getExtension(file == null ? "" : file.getOriginalFilename());
//    }
//
//
//}
