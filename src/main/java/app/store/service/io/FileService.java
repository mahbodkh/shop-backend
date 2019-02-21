package app.store.service.io;
//
//import com.mongodb.client.model.Filters;
//import com.mongodb.client.result.UpdateResult;
//import org.bson.Document;
//import org.bson.conversions.Bson;
//import org.bson.types.ObjectId;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FileService {
//
//    @Override
//    public Pazh.QuestionList.Builder addLeagueQuestion(QuestionAddModel questionAddModel) {
//        Pazh.QuestionList.Builder ret = Pazh.QuestionList.newBuilder();
//
//        IReturn_Status_Codes return_status_codes = questionAddModel.getReturn_status_codes();
//        if (return_status_codes.getCode() != Return_Status_Codes_Common.ok_validform.getCode()) {
//            return questionAddModel.getOutput().returnResponseObject(ret, return_status_codes);
//        }
//        MongoDB mongodb_app = questionAddModel.getOutput().getMongodb_app();
//        PazhLeagues pazhLeagues = new PazhLeagues(mongodb_app);
//        PazhQuestions pazhQuestions = new PazhQuestions(mongodb_app);
//
//        ObjectId leagueId = null;
//        leagueId = questionAddModel.getObjectId();
//        if (leagueId == null || General.isEmpty(leagueId.toString())) {
//            System.out.println("Cannot insert to pazhLeagueQuestion in mongo");
//            return_status_codes = Return_Status_Codes_Common.SC_INTERNAL_SERVER_ERROR;
//            return questionAddModel.getOutput().returnResponseObject(ret, return_status_codes);
//        }
//        String appid = questionAddModel.getAppid();
//        String attachment_host = ConfigReaderDatabase.getSettingsInMongo(appid).getString("attachment_host");
//        ObjectId questionId = new ObjectId();
//
//
//        Document doc = pazhQuestions.createQuestionDocument(questionAddModel);
//        doc.put(PazhLeagues.Fields.ID.getValue(), questionId);
//        UpdateResult result = pazhLeagues.updatePushArrayAndIncrementCount(leagueId, PazhLeagues.Fields.QUESTIONS.getValue(),
//                "questionCount", doc);
//        if (!result.isModifiedCountAvailable()) {
//            System.out.println(String.format("Cannot Add Question to League(%s) ", leagueId));
//            return_status_codes = Return_Status_Codes_Common.SC_INTERNAL_SERVER_ERROR;
//            return questionAddModel.getOutput().returnResponseObject(ret, return_status_codes);
//        }
//
//
//        String value = questionAddModel.getType();
//        if (value.equals(ContentType.IMAGE.getValue())) {
//            ArrayList<Document> photos = new ArrayList<>();
//            FileManagerImage fileManagerImage = new FileManagerImage(questionAddModel.getLink(), questionAddModel.getAppid(), questionId.toString());
//            FileSave filePrepairSave = fileManagerImage.savePostTrackCoverPrepair();
//            FileSave fileSave = filePrepairSave;
//            String posts_image_files_extension = ConfigReader.getConfig().getString("pazh_image_files_extension");
//
//            if (fileManagerImage.hasCover()) {
//                fileSave = fileManagerImage.savePostTrackCoverFile();
//                boolean image_filesaved = fileSave.isFileSaved();
//                boolean image_needs_conversion = image_filesaved && !posts_image_files_extension.equalsIgnoreCase(fileManagerImage.getOriginalCoverExtention());
//                if (image_needs_conversion) {
//                    fileSave = fileManagerImage.convertCoverType(false);
//                }
//                General.fixImageSize(fileSave.getPath(), false);
//                General.convertJpg2ProgressiveImage(fileSave.getPath(), fileSave.getPath());
//            }
//            if (fileManagerImage.hasCover()) {
//                Document smallImageDocument = General.makeSmallImageDocument(fileSave.getPath(), PostAttachType.IMAGE_SMALL, attachment_host, fileManagerImage.getPath_main());
//                if (smallImageDocument != null) {
//                    photos.add(smallImageDocument);
//                }
//                Document blurImageDocument = General.makeBlurImageDocument(fileSave.getPath(), PostAttachType.IMAGE_BLUR, attachment_host, fileManagerImage.getPath_main());
//                if (blurImageDocument != null) {
//                    photos.add(blurImageDocument);
//                }
//
//                Document attach_image = new Document();
//                General.addAttachmentDimensions(attach_image, fileSave.getPath());
//
//                fileSave = filePrepairSave;
//                attach_image.put(PostAttachment.ATTACHMENT_HOST.getValue(), attachment_host);
//                attach_image.put(PostAttachment.ATTACHMENT_TYPE.getValue(), PostAttachType.IMAGE.getValue());
//                attach_image.put(PostAttachment.ATTACHMENT_MIME_TYPE.getValue(), General.getMimeType2(fileSave.getPath()));
//                attach_image.put(PostAttachment.ATTACHMENT_SIZE.getValue(), fileManagerImage.getCoverSize());
//                attach_image.put(PostAttachment.ATTACHMENT_PATH.getValue(), fileSave.getPath());
//                attach_image.put(PostAttachment.ATTACHMENT_URL.getValue(), fileSave.getUrl());
//                photos.add(attach_image);
//            }
//            if (photos != null && !photos.isEmpty()) {
//                Document updatedoc = new Document();
//                updatedoc.put(PazhLeagues.Fields.QUESTIONS.getValue() + ".$." + PazhLeagues.Fields.IMAGES.getValue(), photos);
//
//                List<Bson> findList = new ArrayList<>();
//                findList.add(Filters.eq(PazhLeagues.Fields.ID.getValue(), leagueId));
//                findList.add(Filters.eq(PazhLeagues.Fields.QUESTIONS.getValue() + "." + PazhLeagues.Fields.ID.getValue(), questionId));
//                Bson find_doc = Filters.and(findList);
//
//                pazhLeagues.updateOne(find_doc, new Document("$set", updatedoc));
//            }
//
//        } else if (value.equals(ContentType.VIDEO.getValue())) {
//            ArrayList<Document> photos = new ArrayList<>();
//            FileManagerVideo fileManagerVideo = new FileManagerVideo(questionAddModel.getLink(), questionAddModel.getCover(), questionAddModel.getAppid(), questionId.toString());
//            boolean file_needs_cover;
//
//            FileSave fileSaveVideo = fileManagerVideo.savePostVideoFile();
//            if (!fileSaveVideo.isFileSaved()) {
//                System.out.println("Cannot save post VIDEO file to disk");
//                return_status_codes = Return_Status_Codes_Common.SC_INTERNAL_SERVER_ERROR;
//                return questionAddModel.getOutput().returnResponseObject(ret, return_status_codes);
//            }
//
//
//            String posts_image_files_extension = ConfigReader.getConfig().getString("pazh_image_files_extension");
//            FileSave fileSaveCover = fileManagerVideo.savePostVideoCoverPrepair();
//
//
//            Document smallImageDocument = null;
//            Document blurImageDocument = null;
//            Document attach_cover = null;
//
//            if (fileManagerVideo.hasCover()) {
//                fileSaveCover = fileManagerVideo.savePostVideoCoverFile();
//                boolean cover_filesaved = fileSaveCover.isFileSaved();
//                boolean cover_needs_conversion = cover_filesaved && !posts_image_files_extension.equalsIgnoreCase(fileManagerVideo.getOriginalCoverExtension());
//                if (cover_needs_conversion) {
//                    fileSaveCover = fileManagerVideo.convertPazhVideosCoverType(false);
//                }
//                file_needs_cover = !cover_filesaved;
//            } else {
//                file_needs_cover = true;
//            }
//
//            FileSave fileSaveCoverFinal = fileSaveCover;
//            if (fileManagerVideo.hasCover()) {
//                smallImageDocument = General.makeSmallImageDocument(fileSaveCoverFinal.getPath(), PostAttachType.VIDEO_COVER_THUMBNAIL, attachment_host, fileManagerVideo.getPath_video_cover());
//                if (smallImageDocument != null) {
//                    photos.add(smallImageDocument);
//                }
//                blurImageDocument = General.makeBlurImageDocument(fileSaveCoverFinal.getPath(), PostAttachType.VIDEO_COVER_BLUR, attachment_host, fileManagerVideo.getPath_video_cover());
//                if (blurImageDocument != null) {
//                    photos.add(blurImageDocument);
//                }
//                attach_cover = new Document();
//                General.addAttachmentDimensions(attach_cover, fileSaveCoverFinal.getPath());
//
//
//                attach_cover.put(PostAttachment.ATTACHMENT_HOST.getValue(), attachment_host);
//                attach_cover.put(PostAttachment.ATTACHMENT_TYPE.getValue(), PostAttachType.VIDEO_COVER.getValue());
//                attach_cover.put(PostAttachment.ATTACHMENT_MIME_TYPE.getValue(), General.getMimeType2(fileSaveCoverFinal.getPath()));
//                attach_cover.put(PostAttachment.ATTACHMENT_SIZE.getValue(), fileManagerVideo.getCoverSize());
//                attach_cover.put(PostAttachment.ATTACHMENT_PATH.getValue(), fileSaveCoverFinal.getPath());
//                attach_cover.put(PostAttachment.ATTACHMENT_URL.getValue(), fileSaveCoverFinal.getUrl());
//
//                photos.add(attach_cover);
//            }
//
//            General.fixImageSize(fileSaveCover.getPath(), false);
//
//            General.convertJpg2ProgressiveImage(fileSaveCover.getPath(), fileSaveCover.getPath());
//
//
//            if (fileManagerVideo.hasVideo()) {
//                Document attach_video = new Document();
//                attach_video.put("path", fileSaveVideo.getPath());
//                attach_video.put("url", fileSaveVideo.getUrl());
//                attach_video.put("size", fileManagerVideo.getFileSize());
//                attach_video.put("extension", fileManagerVideo.getExtension());
//                attach_video.put("cover_url", attach_cover);
//                attach_video.put("cover_thumbnail", smallImageDocument);
//                attach_video.put("cover_blur", blurImageDocument);
//
//
//                Document updatedoc = new Document();
//                updatedoc.put(PazhLeagues.Fields.QUESTIONS.getValue() + ".$." + PazhLeagues.Fields.VIDEOS.getValue(), attach_video);
//
//                List<Bson> findList = new ArrayList<>();
//                findList.add(Filters.eq(PazhLeagues.Fields.ID.getValue(), leagueId));
//                findList.add(Filters.eq(PazhLeagues.Fields.QUESTIONS.getValue() + "." + PazhLeagues.Fields.ID.getValue(), questionId));
//                Bson find_doc = Filters.and(findList);
//
//                pazhLeagues.updateOne(
//                        find_doc,
//                        new Document("$set", updatedoc)
//                );
//
//
//            }
//
//        } else if (value.equals(ContentType.AUDIO.getValue())) {
//            ArrayList<Document> photos = new ArrayList<>();
//            FileManagerAudio fileManagerAudio = new FileManagerAudio(questionAddModel.getLink(), questionAddModel.getCover(), questionAddModel.getAppid(), questionId.toString());
//            FileSave fileSave = fileManagerAudio.savePostAudioFile();
//            if (!fileSave.isFileSaved()) {
//                System.out.println("Cannot save post AUDIO file to disk");
//                return_status_codes = Return_Status_Codes_Common.SC_INTERNAL_SERVER_ERROR;
//                return questionAddModel.getOutput().returnResponseObject(ret, return_status_codes);
//            }
//            String posts_image_files_extension = ConfigReader.getConfig().getString("pazh_image_files_extension");
///////////////////////////////
///////////////////////////////
///////////////////////////////
//            FileSave fileSaveCover = fileManagerAudio.savePostAudioCoverPrepair();
//
//
//            boolean file_needs_cover;
//            Document smallImageDocument = null;
//            Document blurImageDocument = null;
//            Document attach_cover = null;
//
//            if (fileManagerAudio.hasCover()) {
//                fileSaveCover = fileManagerAudio.savePostAudioCoverFile();
//                boolean cover_filesaved = fileSaveCover.isFileSaved();
//                boolean cover_needs_conversion = cover_filesaved && !posts_image_files_extension.equalsIgnoreCase(fileManagerAudio.getOriginalCoverExtension());
//                if (cover_needs_conversion) {
//                    fileSaveCover = fileManagerAudio.convertPazhAudiosCoverType(false);
//                }
//                file_needs_cover = !cover_filesaved;
//            } else {
//                file_needs_cover = true;
//            }
//
//            FileSave fileSaveCoverFinal = fileSaveCover;
//            if (fileManagerAudio.hasCover()) {
//                smallImageDocument = General.makeSmallImageDocument(fileSaveCoverFinal.getPath(), PostAttachType.MUSIC_COVER_THUMBNAIL, attachment_host, fileManagerAudio.getPath_audio_cover());
//                if (smallImageDocument != null) {
//                    photos.add(smallImageDocument);
//                }
//                blurImageDocument = General.makeBlurImageDocument(fileSaveCoverFinal.getPath(), PostAttachType.MUSIC_COVER_BLUR, attachment_host, fileManagerAudio.getPath_audio_cover());
//                if (blurImageDocument != null) {
//                    photos.add(blurImageDocument);
//                }
//                attach_cover = new Document();
//                General.addAttachmentDimensions(attach_cover, fileSaveCoverFinal.getPath());
//
//
//                attach_cover.put(PostAttachment.ATTACHMENT_HOST.getValue(), attachment_host);
//                attach_cover.put(PostAttachment.ATTACHMENT_TYPE.getValue(), PostAttachType.MUSIC_COVER.getValue());
//                attach_cover.put(PostAttachment.ATTACHMENT_MIME_TYPE.getValue(), General.getMimeType2(fileSaveCoverFinal.getPath()));
//                attach_cover.put(PostAttachment.ATTACHMENT_SIZE.getValue(), fileManagerAudio.getCoverSize());
//                attach_cover.put(PostAttachment.ATTACHMENT_PATH.getValue(), fileSaveCoverFinal.getPath());
//                attach_cover.put(PostAttachment.ATTACHMENT_URL.getValue(), fileSaveCoverFinal.getUrl());
//
//                photos.add(attach_cover);
//            }
//
//            General.fixImageSize(fileSaveCover.getPath(), false);
//            General.convertJpg2ProgressiveImage(fileSaveCover.getPath(), fileSaveCover.getPath());
//
//
///////////////////////////////
///////////////////////////////
///////////////////////////////
//            if (fileManagerAudio.hasAudio()) {
//                Document attach_audio = new Document();
//                attach_audio.put("path", fileSave.getPath());
//                attach_audio.put("size", fileManagerAudio.getFileSize());
//                attach_audio.put("extension", fileManagerAudio.getExtension());
//                attach_audio.put("image_main_cover", attach_cover);
//                attach_audio.put("image_small_cover", smallImageDocument);
//                attach_audio.put("image_blur_cover", blurImageDocument);
//
//
//                Document updatedoc = new Document();
//                updatedoc.put(PazhLeagues.Fields.QUESTIONS.getValue() + ".$." + PazhLeagues.Fields.AUDIOS.getValue(), attach_audio);
//
//                List<Bson> findList = new ArrayList<>();
//                findList.add(Filters.eq(PazhLeagues.Fields.ID.getValue(), leagueId));
//                findList.add(Filters.eq(PazhLeagues.Fields.QUESTIONS.getValue() + "." + PazhLeagues.Fields.ID.getValue(), questionId));
//                Bson find_doc = Filters.and(findList);
//
//                pazhLeagues.updateOne(
//                        find_doc,
//                        new Document("$set", updatedoc)
//                );
//            }
//        }
//        return questionAddModel.getOutput().returnResponseObject(ret, Return_Status_Codes_Common.SC_OK);
//    }
//}
