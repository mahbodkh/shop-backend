package app.store.persistence.domain.enums;

public enum PaymentMethod {

    ONLINE,
    COLLECT_ON_DELIVERY,
    CARD_TO_CARD,
    ;

    PaymentMethod() {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
