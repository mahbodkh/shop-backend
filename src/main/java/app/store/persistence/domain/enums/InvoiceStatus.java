package app.store.persistence.domain.enums;

public enum InvoiceStatus {

    CHECKOUT,
    ;


    InvoiceStatus() {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
