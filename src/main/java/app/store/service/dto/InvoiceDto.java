package app.store.service.dto;

import javax.validation.constraints.Size;


public class InvoiceDto {

    public InvoiceDto() {
    }

    private String id;

    @Size(min = 1)
    private Double discount;

    @Size(min = 1)
    private Double tax;

    @Size(min = 1)
    private Double amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
