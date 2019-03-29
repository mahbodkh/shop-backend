package app.store.persistence.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;

public class ProductIdAndTime implements Serializable {
    private static final long serialVersionUID = 1L;

    @Field
    private ObjectId productId;
    @Field
    private Instant time;

    public ObjectId getProductId() {
        return productId;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "ProductIdAndTime{" +
                "productId=" + productId +
                ", time=" + time +
                '}';
    }
}
