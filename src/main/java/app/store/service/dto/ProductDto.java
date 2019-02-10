package app.store.service.dto;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ProductDto {

    public ProductDto() {
    }

    private String id;
    @NotNull
    private String title;
    private List<DescriptionDto> description = new ArrayList<>();
    private String categoryPath;
    private List<String> categories = new ArrayList<>();
    private Integer stock;
    private VendorDto vendorDto;
    private PriceDto price;
    private DimensionDto dimensionDto;
    private Integer sold = 0;
    private String cover;
    private List<MediaDto> mediaList = new ArrayList<>();
    private List<VariantDto> variants = new ArrayList<>();
    private List<String> relateProduct = new ArrayList<>();
    private List<KeywordDto> keywords = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DescriptionDto> getDescription() {
        return description;
    }

    public void setDescription(List<DescriptionDto> description) {
        this.description = description;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public VendorDto getVendorDto() {
        return vendorDto;
    }

    public void setVendorDto(VendorDto vendorDto) {
        this.vendorDto = vendorDto;
    }

    public PriceDto getPrice() {
        return price;
    }

    public void setPrice(PriceDto price) {
        this.price = price;
    }

    public DimensionDto getDimensionDto() {
        return dimensionDto;
    }

    public void setDimensionDto(DimensionDto dimensionDto) {
        this.dimensionDto = dimensionDto;
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

    public List<MediaDto> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<MediaDto> mediaList) {
        this.mediaList = mediaList;
    }

    public List<VariantDto> getVariants() {
        return variants;
    }

    public void setVariants(List<VariantDto> variants) {
        this.variants = variants;
    }

    public List<String> getRelateProduct() {
        return relateProduct;
    }

    public void setRelateProduct(List<String> relateProduct) {
        this.relateProduct = relateProduct;
    }

    public List<KeywordDto> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<KeywordDto> keywords) {
        this.keywords = keywords;
    }
}

