package app.store.persistence.domain;


import app.store.persistence.domain.enums.InvoiceStatus;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@org.springframework.data.mongodb.core.mapping.Document(collection = "invoice")
public class Invoice extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;
    @Field
    private ObjectId userId;
    @Field
    private ObjectId paymentId;
    @Field
    @Size(min = 1)
    private Double discount;
    @Field
    @Size(min = 1)
    private Double tax;
    @Field
    @Size(min = 1)
    private Double amount;
    @Field
    private InvoiceStatus status;
    @Field
    private List<ProductCart> productCarts = new ArrayList<>();
    @Field
    private Double net = 0d;
    @Field
    private Double deliveryCost = 0d;

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

    public ObjectId getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(ObjectId paymentId) {
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

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public List<ProductCart> getProductCarts() {
        return productCarts;
    }

    public void setProductCarts(List<ProductCart> productCarts) {
        this.productCarts = productCarts;
    }

    public Double getNet() {
        return net;
    }

    public void setNet(Double net) {
        this.net = net;
    }

    public Double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", userId=" + userId +
                ", paymentId=" + paymentId +
                ", discount=" + discount +
                ", tax=" + tax +
                ", amount=" + amount +
                ", status=" + status +
                ", productCarts=" + productCarts +
                ", net=" + net +
                ", deliveryCost=" + deliveryCost +
                '}';
    }
}
