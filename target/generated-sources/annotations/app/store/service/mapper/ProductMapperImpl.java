package app.store.service.mapper;

import app.store.persistence.domain.*;
import app.store.persistence.domain.Product;
import app.store.persistence.domain.enums.AssetType;
import app.store.service.dto.AssetDto;
import app.store.service.dto.BrandDto;
import app.store.service.dto.ProductDto;
import app.store.service.dto.DescriptionDto;
import app.store.service.dto.KeywordDto;
import app.store.service.dto.PriceDto;
import app.store.service.dto.VariantDto;
import app.store.service.dto.enums.AssetTypeDto;
import app.store.service.mapper.util.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-01-10T15:18:07+0330",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private Utils utils;

    @Override
    public List<Product> toEntity(List<ProductDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( dtoList.size() );
        for ( ProductDto productDto : dtoList ) {
            list.add( toEntity(productDto) );
        }

        return list;
    }

    @Override
    public List<ProductDto> toDto(List<Product> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( entityList.size() );
        for ( Product product : entityList ) {
            list.add( toDto(product) );
        }

        return list;
    }

    @Override
    public Product toEntity(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( utils.mapObjectId( dto.getId() ) );
        product.setStock( dto.getStock() );
        product.setBrand( brandDtoToBrand( dto.getBrand() ) );
        product.setPrice( priceDtoToPrice( dto.getPrice() ) );
        product.setSold( dto.getSold() );
        product.setCover( dto.getCover() );
        product.setAssetList( assetDtoListToAssetList( dto.getAssetList() ) );
        product.setVariants( variantDtoListToVariantList( dto.getVariants() ) );
        product.setRelate( stringListToObjectIdList( dto.getRelate() ) );
        product.setKeywords( keywordDtoListToKeywordList( dto.getKeywords() ) );

        return product;
    }

    @Override
    public ProductDto toDto(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( utils.mapId( entity.getId() ) );
        productDto.setStock( entity.getStock() );
        productDto.setBrand( brandToBrandDto( entity.getBrand() ) );
        productDto.setPrice( priceToPriceDto( entity.getPrice() ) );
        productDto.setSold( entity.getSold() );
        productDto.setCover( entity.getCover() );
        productDto.setAssetList( assetListToAssetDtoList( entity.getAssetList() ) );
        productDto.setVariants( variantListToVariantDtoList( entity.getVariants() ) );
        productDto.setRelate( objectIdListToStringList( entity.getRelate() ) );
        productDto.setKeywords( keywordListToKeywordDtoList( entity.getKeywords() ) );

        return productDto;
    }

    protected Description descriptionDtoToDescription(DescriptionDto descriptionDto) {
        if ( descriptionDto == null ) {
            return null;
        }

        Description description = new Description();

        description.setLanguage( descriptionDto.getLanguage() );
        description.setName( descriptionDto.getName() );
        description.setShortDescribe( descriptionDto.getShortDescribe() );
        description.setLongDescribe( descriptionDto.getLongDescribe() );

        return description;
    }

    protected Brand brandDtoToBrand(BrandDto brandDto) {
        if ( brandDto == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setId( utils.mapObjectId( brandDto.getId() ) );
        brand.setCover( brandDto.getCover() );
        brand.setName( brandDto.getName() );
        brand.setDescription( descriptionDtoToDescription( brandDto.getDescription() ) );

        return brand;
    }

    protected Price priceDtoToPrice(PriceDto priceDto) {
        if ( priceDto == null ) {
            return null;
        }

        Price price = new Price();

        price.setPrice( priceDto.getPrice() );
        price.setRetail( priceDto.getRetail() );
        price.setQtyRetail( priceDto.getQtyRetail() );
        price.setDiscount( priceDto.getDiscount() );
        price.setSaving( priceDto.getSaving() );
        price.setSaleValid( priceDto.getSaleValid() );

        return price;
    }

    protected AssetType assetTypeDtoToAssetType(AssetTypeDto assetTypeDto) {
        if ( assetTypeDto == null ) {
            return null;
        }

        AssetType assetType;

        switch ( assetTypeDto ) {
            case VIDEO: assetType = AssetType.VIDEO;
            break;
            case IMAGE_THUMBNAIL: assetType = AssetType.IMAGE_THUMBNAIL;
            break;
            case IMAGE_ORIGENAL: assetType = AssetType.IMAGE_ORIGENAL;
            break;
            case IMAGE_BLUR: assetType = AssetType.IMAGE_BLUR;
            break;
            case AUDIO: assetType = AssetType.AUDIO;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + assetTypeDto );
        }

        return assetType;
    }

    protected Asset assetDtoToAsset(AssetDto assetDto) {
        if ( assetDto == null ) {
            return null;
        }

        Asset asset = new Asset();

        asset.setId( utils.mapObjectId( assetDto.getId() ) );
        asset.setType( assetTypeDtoToAssetType( assetDto.getType() ) );
        asset.setName( assetDto.getName() );
        asset.setUrl( assetDto.getUrl() );
        asset.setCapacity( assetDto.getCapacity() );
        asset.setWidth( assetDto.getWidth() );
        asset.setHeight( assetDto.getHeight() );
        asset.setDuration( assetDto.getDuration() );

        return asset;
    }

    protected List<Asset> assetDtoListToAssetList(List<AssetDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Asset> list1 = new ArrayList<Asset>( list.size() );
        for ( AssetDto assetDto : list ) {
            list1.add( assetDtoToAsset( assetDto ) );
        }

        return list1;
    }

    protected Variant variantDtoToVariant(VariantDto variantDto) {
        if ( variantDto == null ) {
            return null;
        }

        Variant variant = new Variant();

        variant.setTitle( variantDto.getTitle() );
        variant.setSpecType( variantDto.getSpecType() );

        return variant;
    }

    protected List<Variant> variantDtoListToVariantList(List<VariantDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Variant> list1 = new ArrayList<Variant>( list.size() );
        for ( VariantDto variantDto : list ) {
            list1.add( variantDtoToVariant( variantDto ) );
        }

        return list1;
    }

    protected List<ObjectId> stringListToObjectIdList(List<String> list) {
        if ( list == null ) {
            return null;
        }

        List<ObjectId> list1 = new ArrayList<ObjectId>( list.size() );
        for ( String string : list ) {
            list1.add( utils.mapObjectId( string ) );
        }

        return list1;
    }

    protected Keyword keywordDtoToKeyword(KeywordDto keywordDto) {
        if ( keywordDto == null ) {
            return null;
        }

        Keyword keyword = new Keyword();

        keyword.setId( utils.mapObjectId( keywordDto.getId() ) );
        keyword.setName( keywordDto.getName() );
        keyword.setDescription( keywordDto.getDescription() );

        return keyword;
    }

    protected List<Keyword> keywordDtoListToKeywordList(List<KeywordDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Keyword> list1 = new ArrayList<Keyword>( list.size() );
        for ( KeywordDto keywordDto : list ) {
            list1.add( keywordDtoToKeyword( keywordDto ) );
        }

        return list1;
    }

    protected DescriptionDto descriptionToDescriptionDto(Description description) {
        if ( description == null ) {
            return null;
        }

        DescriptionDto descriptionDto = new DescriptionDto();

        descriptionDto.setLanguage( description.getLanguage() );
        descriptionDto.setName( description.getName() );
        descriptionDto.setShortDescribe( description.getShortDescribe() );
        descriptionDto.setLongDescribe( description.getLongDescribe() );

        return descriptionDto;
    }

    protected BrandDto brandToBrandDto(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandDto brandDto = new BrandDto();

        brandDto.setId( utils.mapId( brand.getId() ) );
        brandDto.setCover( brand.getCover() );
        brandDto.setName( brand.getName() );
        brandDto.setDescription( descriptionToDescriptionDto( brand.getDescription() ) );

        return brandDto;
    }

    protected PriceDto priceToPriceDto(Price price) {
        if ( price == null ) {
            return null;
        }

        PriceDto priceDto = new PriceDto();

        priceDto.setPrice( price.getPrice() );
        priceDto.setRetail( price.getRetail() );
        priceDto.setQtyRetail( price.getQtyRetail() );
        priceDto.setDiscount( price.getDiscount() );
        priceDto.setSaving( price.getSaving() );
        priceDto.setSaleValid( price.getSaleValid() );

        return priceDto;
    }

    protected AssetTypeDto assetTypeToAssetTypeDto(AssetType assetType) {
        if ( assetType == null ) {
            return null;
        }

        AssetTypeDto assetTypeDto;

        switch ( assetType ) {
            case VIDEO: assetTypeDto = AssetTypeDto.VIDEO;
            break;
            case IMAGE_THUMBNAIL: assetTypeDto = AssetTypeDto.IMAGE_THUMBNAIL;
            break;
            case IMAGE_ORIGENAL: assetTypeDto = AssetTypeDto.IMAGE_ORIGENAL;
            break;
            case IMAGE_BLUR: assetTypeDto = AssetTypeDto.IMAGE_BLUR;
            break;
            case AUDIO: assetTypeDto = AssetTypeDto.AUDIO;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + assetType );
        }

        return assetTypeDto;
    }

    protected AssetDto assetToAssetDto(Asset asset) {
        if ( asset == null ) {
            return null;
        }

        AssetDto assetDto = new AssetDto();

        assetDto.setId( utils.mapId( asset.getId() ) );
        assetDto.setType( assetTypeToAssetTypeDto( asset.getType() ) );
        assetDto.setName( asset.getName() );
        assetDto.setUrl( asset.getUrl() );
        assetDto.setCapacity( asset.getCapacity() );
        assetDto.setWidth( asset.getWidth() );
        assetDto.setHeight( asset.getHeight() );
        assetDto.setDuration( asset.getDuration() );

        return assetDto;
    }

    protected List<AssetDto> assetListToAssetDtoList(List<Asset> list) {
        if ( list == null ) {
            return null;
        }

        List<AssetDto> list1 = new ArrayList<AssetDto>( list.size() );
        for ( Asset asset : list ) {
            list1.add( assetToAssetDto( asset ) );
        }

        return list1;
    }

    protected VariantDto variantToVariantDto(Variant variant) {
        if ( variant == null ) {
            return null;
        }

        VariantDto variantDto = new VariantDto();

        variantDto.setSpecType( variant.getSpecType() );
        variantDto.setTitle( variant.getTitle() );

        return variantDto;
    }

    protected List<VariantDto> variantListToVariantDtoList(List<Variant> list) {
        if ( list == null ) {
            return null;
        }

        List<VariantDto> list1 = new ArrayList<VariantDto>( list.size() );
        for ( Variant variant : list ) {
            list1.add( variantToVariantDto( variant ) );
        }

        return list1;
    }

    protected List<String> objectIdListToStringList(List<ObjectId> list) {
        if ( list == null ) {
            return null;
        }

        List<String> list1 = new ArrayList<String>( list.size() );
        for ( ObjectId objectId : list ) {
            list1.add( utils.mapId( objectId ) );
        }

        return list1;
    }

    protected KeywordDto keywordToKeywordDto(Keyword keyword) {
        if ( keyword == null ) {
            return null;
        }

        KeywordDto keywordDto = new KeywordDto();

        keywordDto.setId( utils.mapId( keyword.getId() ) );
        keywordDto.setName( keyword.getName() );
        keywordDto.setDescription( keyword.getDescription() );

        return keywordDto;
    }

    protected List<KeywordDto> keywordListToKeywordDtoList(List<Keyword> list) {
        if ( list == null ) {
            return null;
        }

        List<KeywordDto> list1 = new ArrayList<KeywordDto>( list.size() );
        for ( Keyword keyword : list ) {
            list1.add( keywordToKeywordDto( keyword ) );
        }

        return list1;
    }
}
