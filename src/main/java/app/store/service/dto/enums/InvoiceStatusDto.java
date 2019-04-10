package app.store.service.dto.enums;

public enum InvoiceStatusDto {

    APPROVED,
    CANCEL,
    ENTERED,
    HOLD,
    PAID,
    PENDING_REVERSAL,
    REVERSED,
    SCHEDULED,
    WAITING_ON_APPROVAL;

    InvoiceStatusDto() {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
