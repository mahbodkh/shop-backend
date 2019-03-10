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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Instant getTransaction() {
        return transaction;
    }

    public void setTransaction(Instant transaction) {
        this.transaction = transaction;
    }

    public PaymentMethodDto getMethod() {
        return method;
    }

    public void setMethod(PaymentMethodDto method) {
        this.method = method;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
}
