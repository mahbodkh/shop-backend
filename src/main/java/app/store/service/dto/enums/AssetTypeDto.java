package app.store.service.dto.enums;

public enum AssetTypeDto {

    IMAGE_ORIGINAL,
    IMAGE_HIGH,
    IMAGE_MEDIUM,
    IMAGE_THUMBNAIL,
    IMAGE_BLUR,

    VIDEO_ORIGINAL,

    AUDIO_ORIGINAL;

    AssetTypeDto() {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
