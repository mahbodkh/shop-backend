package app.store.service.dto;

import app.store.service.dto.enums.PaymentMethodDto;

import java.time.Instant;

public class PaymentDto {
    public PaymentDto() {
    }

    private String id;
    private String  userId;
    private String invoiceId;
    private String bankId;
    private Double amount = 0d;
    private Instant transaction;
    private PaymentMethodDto method;
    private Boolean isPaid = false;
}
