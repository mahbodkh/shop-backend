package app.store.persistence.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@org.springframework.data.mongodb.core.mapping.Document(collection = "product")
public class Product extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;

    @Field
    List<Description> descriptions = new ArrayList<>();

    @Field
    private Integer stock;

    @Field
    private Brand brand;

    @Field
    private Price price;

    @Field
    private Integer sold = 0;

    @Field
    private String cover;

    @Field
    private List<Asset> assetList = new ArrayList<>();

    @Field
    private List<Variant> variants = new ArrayList<>();

    @Field
    List<ObjectId> relate = new ArrayList<>();

    @Field
    private List<Keyword> keywords = new ArrayList<>();

    @JsonIgnore
    private MultipartFile data;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<Asset> getAssetList() {
        return assetList;
    }

    public void setAssetList(List<Asset> assetList) {
        this.assetList = assetList;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public List<ObjectId> getRelate() {
        return relate;
    }

    public void setRelate(List<ObjectId> relate) {
        this.relate = relate;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public MultipartFile getData() {
        return data;
    }

    public void setData(MultipartFile data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", descriptions=" + descriptions +
                ", stock=" + stock +
                ", brand=" + brand +
                ", price=" + price +
                ", sold=" + sold +
                ", cover='" + cover + '\'' +
                ", assetList=" + assetList +
                ", variants=" + variants +
                ", relate=" + relate +
                ", keywords=" + keywords +
                ", data=" + data +
                '}';
    }
}
