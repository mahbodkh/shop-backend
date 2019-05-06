package app.store.service.dto.enums;

public enum PaymentMethodDto {

    ONLINE,
    COLLECT_ON_DELIVERY,
    CARD_TO_CARD,
    ;

    PaymentMethodDto() {
    }

    private String value;

    public String getValue() {
        return value;
    }
}
