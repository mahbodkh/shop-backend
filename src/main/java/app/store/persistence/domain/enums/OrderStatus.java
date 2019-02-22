package app.store.persistence.domain.enums;

public enum OrderStatus {


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
