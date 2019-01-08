package app.store.persistence.domain.enums;

public enum AssetType {

    VIDEO,
    IMAGE_THUMBNAIL,
    IMAGE_ORIGENAL,
    IMAGE_BLUR,
    AUDIO;

    AssetType() {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
