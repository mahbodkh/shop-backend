package app.store.service.dto.enums;

public enum AssetTypeDto {

    VIDEO,
    IMAGE_THUMBNAIL,
    IMAGE_ORIGINAL,
    IMAGE_BLUR,
    AUDIO;

    AssetTypeDto() {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
