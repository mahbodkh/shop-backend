package app.store.persistence.domain;


import app.store.persistence.domain.enums.PaymentMethod;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@org.springframework.data.mongodb.core.mapping.Document(collection = "payment")
public class Payment extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;
    @Field
    private ObjectId userId;
    @Field
    private ObjectId invoiceId;
    @Field
    private ObjectId bankId;
    @Field
    private Double amount = 0d;
    @Field
    private Instant transaction;
    @Field
    private PaymentMethod method;
    @Field
    private Boolean isPaid = false;


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

    public ObjectId getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(ObjectId invoiceId) {
        this.invoiceId = invoiceId;
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

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
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
                ", userId=" + userId +
                ", invoiceId=" + invoiceId +
                ", bankId=" + bankId +
                ", amount=" + amount +
                ", transaction=" + transaction +
                ", method=" + method +
                ", isPaid=" + isPaid +
                '}';
    }
}
