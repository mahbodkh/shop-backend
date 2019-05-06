package app.store.persistence.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@org.springframework.data.mongodb.core.mapping.Document(collection = "user_log")
public class UserLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;
    @Field
    private ObjectId userId;
    @Field
    private List<ProductIdAndTime> productIdAndTimes = new CopyOnWriteArrayList<>();

    public UserLog() {
    }

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


    public List<ProductIdAndTime> getProductIdAndTimes() {
        return productIdAndTimes;
    }

    public void setProductIdAndTimes(List<ProductIdAndTime> productIdAndTimes) {
        this.productIdAndTimes = productIdAndTimes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLog userLog = (UserLog) o;
        return Objects.equals(id, userLog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", productIdAndTimes=" + productIdAndTimes +
                '}';
    }
}
