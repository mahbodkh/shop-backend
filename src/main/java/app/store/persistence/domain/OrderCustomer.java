package app.store.persistence.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@org.springframework.data.mongodb.core.mapping.Document(collection = "orderCustomer")
public class OrderCustomer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Field
    private ObjectId id;
    @Field
    private ObjectId userId;
    @Field
    private Shipping shipping;
    @Field
    private String deliveryNote;
    @Field
    private Instant checkout;
    @Field
    private Tracking tracking;
    @Field
    private Payment payment;
    @Field
    private Invoice invoice;
    @Field
    private List<Product> productList = new ArrayList<>();

    @Field
    private Price price;

    @Field
    @Size(min = 1)
    private Double tax = 1d;

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

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public Instant getCheckout() {
        return checkout;
    }

    public void setCheckout(Instant checkout) {
        this.checkout = checkout;
    }

    public Tracking getTracking() {
        return tracking;
    }

    public void setTracking(Tracking tracking) {
        this.tracking = tracking;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCustomer orderCustomer = (OrderCustomer) o;
        return Objects.equals(id, orderCustomer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OrderCustomer{" +
                "id=" + id +
                ", userId=" + userId +
                ", shipping=" + shipping +
                ", deliveryNote='" + deliveryNote + '\'' +
                ", checkout=" + checkout +
                ", tracking=" + tracking +
                ", payment=" + payment +
                ", invoice=" + invoice +
                ", productList=" + productList +
                ", price=" + price +
                ", tax=" + tax +
                '}';
    }
}
