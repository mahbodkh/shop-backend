package app.store.service.dto;

import java.time.Instant;

public class PaymentDto {
    public PaymentDto() {
    }

    private String id;
    private String cartId;
    private String bankId;
    private Double amount = 0d;
    private Instant transaction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
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
}
