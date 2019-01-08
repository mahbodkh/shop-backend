package app.store.persistence.domain;

import java.io.Serializable;

public class Dimension implements Serializable {
    private static final long serialVersionUID = 1L;


    private Double width = 0d;
    private Double height = 0d;
    private Double length = 0d;
    private Double weight = 0d;

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Dimension{" +
                "width=" + width +
                ", height=" + height +
                ", length=" + length +
                ", weight=" + weight +
                '}';
    }
}
