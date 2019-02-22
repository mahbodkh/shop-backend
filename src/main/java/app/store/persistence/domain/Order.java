package app.store.persistence.domain;

import app.store.persistence.domain.enums.OrderStatus;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@org.springframework.data.mongodb.core.mapping.Document(collection = "orders")
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
    private ObjectId invoiceId;
    @Field
    private OrderStatus status;
    @Field
    private Shipping shipping;


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



}
