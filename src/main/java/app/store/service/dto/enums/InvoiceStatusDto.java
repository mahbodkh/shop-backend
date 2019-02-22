package app.store.service.dto.enums;

public enum InvoiceStatusDto {

    CHECKOUT,
    ;

    InvoiceStatusDto() {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
