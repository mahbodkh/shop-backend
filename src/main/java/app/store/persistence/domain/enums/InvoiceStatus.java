package app.store.persistence.domain.enums;

public enum InvoiceStatus {

    APPROVED,
    CANCEL,
    ENTERED,
    HOLD,
    PAID,
    PENDING_REVERSAL,
    REVERSED,
    SCHEDULED,
    WAITING_ON_APPROVAL;


    InvoiceStatus() {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
