package app.store.persistence.domain;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Objects;

public class ProductCart implements Serializable {
    private static final long serialVersionUID = 1L;

    private ObjectId productId;
    private String property;
    private Integer quantity = 0;

    public ProductCart() {
    }

    public ObjectId getProductId() {
        return productId;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCart that = (ProductCart) o;
        return quantity == that.quantity &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(property, that.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, property, quantity);
    }

    @Override
    public String toString() {
        return "ProductCart{" +
                "productId=" + productId +
                ", property='" + property + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
