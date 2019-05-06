package app.store.service.dto.enums;

public enum CartStatusDto {

    ACTIVE,
    COMPLETE,
    SUSPEND,
    DEACTIVATED,
    FINISH
    ;


    CartStatusDto() {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
