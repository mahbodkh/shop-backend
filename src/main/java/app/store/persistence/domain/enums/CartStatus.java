package app.store.persistence.domain.enums;

public enum CartStatus {

    ACTIVE,
    COMPLETE,
    SUSPEND,
    DEACTIVE,
    ;

    CartStatus() {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
