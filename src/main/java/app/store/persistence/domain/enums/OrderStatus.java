package app.store.persistence.domain.enums;

public enum OrderStatus {

    CREATED,
    ISSUED,
    SUSPEND,
    DELIVERED,
    DONE,
    ;


    OrderStatus() {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
