package app.store.service.dto;

import java.util.ArrayList;
import java.util.List;

public class CommodityDto {

    public CommodityDto() {
    }

    private String id;
    private List<DescriptionDto> description;
    private Integer stock;
    private BrandDto brand;
    private PriceDto price;
    private Integer sold = 0;
    private String cover;
    private List<AssetDto> assetList = new ArrayList<>();
    private List<VariantDto> variants = new ArrayList<>();
    List<String> relate = new ArrayList<>();
    private List<KeywordDto> keywords = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<DescriptionDto> getDescription() {
        return description;
    }

    public void setDescription(List<DescriptionDto> description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }

    public PriceDto getPrice() {
        return price;
    }

    public void setPrice(PriceDto price) {
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

    public List<AssetDto> getAssetList() {
        return assetList;
    }

    public void setAssetList(List<AssetDto> assetList) {
        this.assetList = assetList;
    }

    public List<VariantDto> getVariants() {
        return variants;
    }

    public void setVariants(List<VariantDto> variants) {
        this.variants = variants;
    }

    public List<String> getRelate() {
        return relate;
    }

    public void setRelate(List<String> relate) {
        this.relate = relate;
    }

    public List<KeywordDto> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<KeywordDto> keywords) {
        this.keywords = keywords;
    }
}

