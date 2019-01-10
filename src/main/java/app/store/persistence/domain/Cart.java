package app.store.persistence.domain;

import app.store.persistence.domain.enums.ProductStatus;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@org.springframework.data.mongodb.core.mapping.Document(collection = "cart")
public class Cart extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;
    @Field
    private ObjectId userId;
    @Field
    private List<ObjectId> productIdList;
    @Field
    private Integer quantity = 0;
    @Field
    private Double total = 0d;
    @Field
    private ProductStatus status;


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

    public List<ObjectId> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<ObjectId> productIdList) {
        this.productIdList = productIdList;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", productIdList=" + productIdList +
                ", quantity=" + quantity +
                ", total=" + total +
                ", status=" + status +
                '}';
    }
}
