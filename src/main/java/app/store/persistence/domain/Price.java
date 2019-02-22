package app.store.persistence.domain;

import java.io.Serializable;
import java.util.Objects;

public class Price implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double price;
    private String currency;
    private Double discount;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return Objects.equals(price, price1.price) &&
                Objects.equals(currency, price1.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, currency);
    }


    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                ", currency='" + currency + '\'' +
                ", discount=" + discount +
                '}';
    }
}
