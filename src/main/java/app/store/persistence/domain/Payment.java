package app.store.persistence.domain;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@org.springframework.data.mongodb.core.mapping.Document(collection = "payment")
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;
    @Field
    private ObjectId cartId;
    @Field
    private ObjectId bankId;
    @Field
    private Double amount = 0d;
    @Field
    private Instant transaction;
    @Field
    private String method;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getCartId() {
        return cartId;
    }

    public void setCartId(ObjectId cartId) {
        this.cartId = cartId;
    }

    public ObjectId getBankId() {
        return bankId;
    }

    public void setBankId(ObjectId bankId) {
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", cartId=" + cartId +
                ", bankId=" + bankId +
                ", amount=" + amount +
                ", transaction=" + transaction +
                ", method=" + method +
                '}';
    }
}
