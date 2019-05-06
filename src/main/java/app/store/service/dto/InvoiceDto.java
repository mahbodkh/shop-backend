package app.store.service.dto;

import app.store.service.dto.enums.InvoiceStatusDto;

import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class InvoiceDto {

    public InvoiceDto() {
    }

    private String id;
    private String userId;
    private String paymentId;
    @Size(min = 1)
    private Double discount;
    @Size(min = 1)
    private Double tax;
    @Size(min = 1)
    private Double amount;
    private Double net = 0d;
    private InvoiceStatusDto status;
    private Double deliveryCost = 0d;
    private List<ProductCartDto> productCarts = new ArrayList<>();
    private Instant persistedTime;

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

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getNet() {
        return net;
    }

    public void setNet(Double net) {
        this.net = net;
    }

    public InvoiceStatusDto getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatusDto status) {
        this.status = status;
    }

    public Double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public List<ProductCartDto> getProductCarts() {
        return productCarts;
    }

    public void setProductCarts(List<ProductCartDto> productCarts) {
        this.productCarts = productCarts;
    }

    public Instant getPersistedTime() {
        return persistedTime;
    }

    public void setPersistedTime(Instant persistedTime) {
        this.persistedTime = persistedTime;
    }
}
