package app.store.persistence.domain;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@org.springframework.data.mongodb.core.mapping.Document(collection = "product")
public class Product extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private ObjectId id;
    @TextIndexed(weight = 1)
    @Field
    private String title;
    @TextIndexed(weight = 1)
    @Field
    private List<Description> descriptions = new ArrayList<>();
    @Field
    private String categoryPath;
    @Field
    private List<ObjectId> categories = new ArrayList<>();
    @Field
    private Integer stock;
    @Field
    private Vendor vendor;
    @Field
    private Price price;
    @Field
    private Integer sold = 0;
    @Field
    private Long visit;
    @Field
    private String cover;
    @Field
    private Dimension dimension;
    @Field
    private List<Media> mediaList = new ArrayList<>();
    @TextIndexed(weight = 2)
    @Field
    private List<Variant> variants = new ArrayList<>();
    @Field
    private List<ObjectId> relateProduct = new ArrayList<>();
    @TextIndexed(weight = 2)
    @Field
    private List<Keyword> keywords = new ArrayList<>();
    @Field
    private List<Specification> specifications = new ArrayList<>();


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public List<ObjectId> getCategories() {
        return categories;
    }

    public void setCategories(List<ObjectId> categories) {
        this.categories = categories;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
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

    public Long getVisit() {
        return visit;
    }

    public void setVisit(Long visit) {
        this.visit = visit;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public List<ObjectId> getRelateProduct() {
        return relateProduct;
    }

    public void setRelateProduct(List<ObjectId> relateProduct) {
        this.relateProduct = relateProduct;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public List<Specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Specification> specifications) {
        this.specifications = specifications;
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
                ", title='" + title + '\'' +
                ", descriptions=" + descriptions +
                ", categoryPath='" + categoryPath + '\'' +
                ", categories=" + categories +
                ", stock=" + stock +
                ", vendor=" + vendor +
                ", price=" + price +
                ", sold=" + sold +
                ", visit=" + visit +
                ", cover='" + cover + '\'' +
                ", dimension=" + dimension +
                ", mediaList=" + mediaList +
                ", variants=" + variants +
                ", relateProduct=" + relateProduct +
                ", keywords=" + keywords +
                ", specifications=" + specifications +
                '}';
    }
}
