package app.store.service.dto;

import app.store.service.dto.enums.OrderStatusDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    public OrderDto() {
    }
    private String id;
    @NotNull
    private String userId;
    @JsonIgnore
    private Instant checkout;
    @NotNull
    private List<ProductCartDto> productCartDtos = new ArrayList<>();
    private List<TrackingDto> tracking = new ArrayList<>();
    private String paymentId;
    private Boolean cashOnDelivery;
    private String invoiceId;
    private OrderStatusDto status;
    private ShippingDto shipping;

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

    public Instant getCheckout() {
        return checkout;
    }

    public void setCheckout(Instant checkout) {
        this.checkout = checkout;
    }

    public List<ProductCartDto> getProductCartDtos() {
        return productCartDtos;
    }

    public void setProductCartDtos(List<ProductCartDto> productCartDtos) {
        this.productCartDtos = productCartDtos;
    }

    public List<TrackingDto> getTracking() {
        return tracking;
    }

    public void setTracking(List<TrackingDto> tracking) {
        this.tracking = tracking;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Boolean getCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(Boolean cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public OrderStatusDto getStatus() {
        return status;
    }

    public void setStatus(OrderStatusDto status) {
        this.status = status;
    }

    public ShippingDto getShipping() {
        return shipping;
    }

    public void setShipping(ShippingDto shipping) {
        this.shipping = shipping;
    }
}
