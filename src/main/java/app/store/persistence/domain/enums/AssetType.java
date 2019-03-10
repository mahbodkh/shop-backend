package app.store.persistence.domain.enums;

public enum AssetType {

    IMAGE_ORIGINAL,
    IMAGE_HIGH,
    IMAGE_MEDIUM,
    IMAGE_THUMBNAIL,
    IMAGE_BLUR,

    VIDEO_ORIGINAL,

    AUDIO_ORIGINAL;

    AssetType() {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
