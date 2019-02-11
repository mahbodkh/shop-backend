package app.store.persistence.domain;

import java.io.Serializable;
import java.util.Objects;

public class Variant implements Serializable {
    private static final long serialVersionUID = 1L;

    private String specType;             // COLOR, KIND
    private String title;                // Name of the Spec
    private Double price;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpecType() {
        return specType;
    }

    public void setSpecType(String specType) {
        this.specType = specType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variant variant = (Variant) o;
        return Objects.equals(specType, variant.specType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specType);
    }

    @Override
    public String toString() {
        return "Variant{" +
                "specType='" + specType + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
