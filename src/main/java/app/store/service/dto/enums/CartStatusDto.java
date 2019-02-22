package app.store.service.dto.enums;

public enum CartStatusDto {

    ACTIVE,
    COMPLETE,
    SUSPEND,
    DEACTIVE,
    ;


    CartStatusDto() {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
