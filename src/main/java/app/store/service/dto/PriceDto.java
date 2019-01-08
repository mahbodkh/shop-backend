package app.store.service.dto;

import javax.validation.constraints.Size;
import java.time.Instant;

public class PriceDto {

    @Size(min = 1)
    private Double price;
    private Double retail;
    private Double qtyRetail;
    private Double discount;
    private Double saving;
    private Instant saleValid;


    public PriceDto() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRetail() {
        return retail;
    }

    public void setRetail(Double retail) {
        this.retail = retail;
    }

    public Double getQtyRetail() {
        return qtyRetail;
    }

    public void setQtyRetail(Double qtyRetail) {
        this.qtyRetail = qtyRetail;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getSaving() {
        return saving;
    }

    public void setSaving(Double saving) {
        this.saving = saving;
    }

    public Instant getSaleValid() {
        return saleValid;
    }

    public void setSaleValid(Instant saleValid) {
        this.saleValid = saleValid;
    }
}
