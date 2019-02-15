package app.store.service.dto;

import app.store.service.util.TextHelper;

public class VariantDto {

    private String specType;
    private String title;
    private Double price;

    public VariantDto() {
    }

    public String getSpecType() {
        return specType;
    }

    public void setSpecType(String specType) {
        this.specType = specType;
    }

    public String getTitle() {
        return TextHelper.toStandardPersian(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
