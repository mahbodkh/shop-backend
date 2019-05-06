package app.store.service.dto.enums;

public enum OrderStatusDto {

    CREATED,
    ISSUED,
    SUSPEND,
    DELIVERED,
    DONE,
    ;


    OrderStatusDto() {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
