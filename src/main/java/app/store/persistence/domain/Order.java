package app.store.persistence.domain;

import app.store.persistence.domain.enums.OrderStatus;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@org.springframework.data.mongodb.core.mapping.Document(collection = "user_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Field
    private ObjectId id;
    @Field
    private ObjectId userId;
    @Field
    private Instant checkout;
    @Field
    private List<Tracking> tracking = new ArrayList<>();
    @Field
    private ObjectId paymentId;
    @Field
    private Boolean cashOnDelivery;
    @Field
    private ObjectId invoiceId;
    @Field
    private OrderStatus status;
    @Field
    private Shipping shipping;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public Instant getCheckout() {
        return checkout;
    }

    public void setCheckout(Instant checkout) {
        this.checkout = checkout;
    }

    public List<Tracking> getTracking() {
        return tracking;
    }

    public void setTracking(List<Tracking> tracking) {
        this.tracking = tracking;
    }

    public ObjectId getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(ObjectId paymentId) {
        this.paymentId = paymentId;
    }

    public Boolean getCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(Boolean cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

    public ObjectId getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(ObjectId invoiceId) {
        this.invoiceId = invoiceId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", checkout=" + checkout +
                ", tracking=" + tracking +
                ", paymentId=" + paymentId +
                ", cashOnDelivery=" + cashOnDelivery +
                ", invoiceId=" + invoiceId +
                ", status=" + status +
                ", shipping=" + shipping +
                '}';
    }
}
