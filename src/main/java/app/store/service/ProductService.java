package app.store.service;

import app.store.persistence.domain.*;
import app.store.persistence.domain.enums.AssetType;
import app.store.persistence.repository.CategoryRepository;
import app.store.persistence.repository.KeywordRepository;
import app.store.persistence.repository.ProductRepository;
import app.store.service.dto.*;
import app.store.service.mapper.ProductMapper;
import app.store.web.rest.error.CategoryNotFoundException;
import app.store.web.rest.error.ProductNotFoundException;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {
    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final KeywordRepository keywordRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;


    public ProductService(ProductRepository productRepository, KeywordRepository keywordRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {

        this.productRepository = productRepository;
        this.keywordRepository = keywordRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public boolean isExists(String productId) {
        return productRepository.existsById(new ObjectId(productId));
    }

    public boolean isProductExistByCategoryId(String categoryId) {
        CompletableFuture<List<Product>> allByCategories = productRepository.findAllByCategories(new ObjectId(categoryId));
        try {
            List<Product> products = allByCategories.get();
            log.debug("is exist product by category id: {} ", categoryId);
            return products.size() != 0;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ProductDto> listProductByCategoryId(String categoryId) {
        CompletableFuture<List<Product>> allByCategories = productRepository.findAllByCategories(new ObjectId(categoryId));
        try {
            List<Product> products = allByCategories.get();
            return productMapper.toDto(products);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Optional<Product> getProductByTitle(String title) {
        return productRepository.findOneByTitle(title);
    }

    public String createProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        isValidByCategoriesId(product.getCategories());
        if (product.getKeywords() != null)
            keywordPersistence(product.getKeywords());
        return productRepository.save(product).getId().toString();
    }

    private void keywordPersistence(List<Keyword> keywords) {
        keywordRepository.saveAll(keywords);
    }

    public Optional<ProductDto> updateProduct(ProductDto productDto, String id) {
        return Optional.of(productRepository.findOneById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(product -> {
                    product.setTitle(productDto.getTitle());
                    product.setCover(productDto.getCover());
                    product.setCategoryPath(productDto.getCategoryPath());
                    product.setSold(productDto.getSold());
                    product.setStock(productDto.getStock());

                    //categories
                    List<String> categories = productDto.getCategories();
                    List<ObjectId> categoriesObjectId = new ArrayList<>();
                    categories.forEach(catId -> {
                        if (categoryRepository.existsById(new ObjectId(catId)))
                            categoriesObjectId.add(new ObjectId(catId));
                        else throw new CategoryNotFoundException();
                    });
                    product.setCategories(categoriesObjectId);

                    //description
                    if (productDto.getDescription() != null) {
                        List<DescriptionDto> productDtoDescription = productDto.getDescription();
                        List<Description> productDescriptions = new ArrayList<>();
                        productDtoDescription.forEach(desc -> {
                            Description description = getDescription(desc);
                            productDescriptions.add(description);
                        });
                        product.setDescriptions(productDescriptions);
                    }

                    //dimension
                    if (productDto.getDimensionDto() != null) {
                        DimensionDto dimensionDto = productDto.getDimensionDto();
                        Dimension dimension = new Dimension();
                        dimension.setHeight(dimensionDto.getHeight());
                        dimension.setLength(dimensionDto.getLength());
                        dimension.setWeight(dimensionDto.getWeight());
                        dimension.setWidth(dimensionDto.getWidth());
                        product.setDimension(dimension);
                    }

                    //media
                    if (productDto.getMediaList() != null) {
                        List<MediaDto> mediaProductDto = productDto.getMediaList();
                        List<Media> mediaProduct = new ArrayList<>();
                        mediaProductDto.forEach(mediaDto -> {
                            Media media = new Media();
                            if (mediaDto.getId() != null && !mediaDto.getId().equals(""))
                                media.setId(new ObjectId(mediaDto.getId()));
                            media.setName(mediaDto.getName());
                            media.setCapacity(mediaDto.getCapacity());
                            media.setUrl(mediaDto.getUrl());
                            media.setDuration(mediaDto.getDuration());
                            media.setHeight(mediaDto.getHeight());
                            media.setWidth(mediaDto.getWidth());
                            media.setType(
                                    Enum.valueOf(AssetType.class, mediaDto.getType().name())
                            );
                            // TODO Upload Multipart Data
                            if (media.getData() != null) {
                                //
                                media.setData(null);
                            }

                            mediaProduct.add(media);
                        });
                        product.setMediaList(mediaProduct);
                    }

                    //relateProduct
                    if (productDto.getRelateProduct() != null) {
                        List<String> relateProductDto = productDto.getRelateProduct();
                        List<ObjectId> relateProduct = new ArrayList<>();
                        relateProductDto.forEach(relate -> {
                            if (isExists(relate))
                                relateProduct.add(new ObjectId(relate));
                            else throw new ProductNotFoundException();
                        });
                        product.setRelateProduct(relateProduct);
                    }

                    //variant
                    if (productDto.getVariants() != null) {
                        List<VariantDto> variantProductDto = productDto.getVariants();
                        List<Variant> variantProduct = new ArrayList<>();
                        variantProductDto.forEach(var -> {
                            Variant variant = new Variant();
                            variant.setTitle(var.getTitle());
                            variant.setSpecType(var.getSpecType());
                            variantProduct.add(variant);
                        });
                        product.setVariants(variantProduct);
                    }

                    //vendor
                    if (productDto.getVendorDto() != null) {
                        VendorDto vendorDto = productDto.getVendorDto();
                        Vendor vendor = new Vendor();
                        vendor.setId(new ObjectId(vendorDto.getId()));
                        vendor.setName(vendorDto.getName());
                        Description descriptionVendor = getDescription(vendorDto.getDescription());
                        vendor.setDescription(descriptionVendor);
                        vendor.setCover(vendorDto.getCover());
                        product.setVendor(vendor);
                    }

                    //keyword
                    if (productDto.getKeywords() != null) {
                        List<KeywordDto> keywordProductDto = productDto.getKeywords();
                        List<Keyword> keywordProduct = new ArrayList<>();
                        keywordProductDto.forEach(keywordDto -> {
                            Keyword keyword = new Keyword();
                            if (keywordDto.getId() != null && !keywordDto.getId().equals(""))
                                keyword.setId(new ObjectId(keywordDto.getId()));
                            keyword.setName(keywordDto.getName());
                            keyword.setLanguage(keywordDto.getLanguage());
                            keyword.setDescription(keywordDto.getDescription());
                            keywordProduct.add(keyword);
                            //TODO save Keyword Separately
                            keywordRepository.save(keyword);

                        });

                        product.setKeywords(keywordProduct);


                    }

                    //price
                    if (productDto.getPrice() != null) {
                        PriceDto priceProductDto = productDto.getPrice();
                        Price priceProduct = new Price();
                        priceProduct.setPrice(priceProductDto.getPrice());
                        priceProduct.setDiscount(priceProductDto.getDiscount());
                        priceProduct.setQtyRetail(priceProductDto.getQtyRetail());
                        priceProduct.setRetail(priceProductDto.getRetail());
                        priceProduct.setSaleValid(priceProductDto.getSaleValid());
                        priceProduct.setSaving(priceProductDto.getSaving());
                        product.setPrice(priceProduct);
                    }
                    //
                    log.debug("Changed Information for Product: {}", product);
                    return productRepository.save(product);
                }).map(productMapper::toDto);
    }


    public Optional<ProductDto> getProduct(String id) {
        return Optional.of(productRepository.findOneById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(productMapper::toDto);
    }

    private Description getDescription(DescriptionDto DescriptionDto) {
        Description description = new Description();
        description.setName(DescriptionDto.getName());
        description.setLanguage(DescriptionDto.getLanguage());
        description.setShortDescribe(DescriptionDto.getShortDescribe());
        description.setLongDescribe(DescriptionDto.getLongDescribe());
        return description;
    }

    private void isValidByCategoriesId(List<ObjectId> categories) {
        if (categories != null) {
            for (ObjectId id : categories) {
                if (!categoryRepository.existsById(id))
                    throw new CategoryNotFoundException();
            }
        }
    }

    public void deleteProduct(String id) {
        productRepository.findOneById(new ObjectId(id)).ifPresent(product -> {
            productRepository.delete(product);
            log.debug("Deleted Product: {}", product);
        });
    }

    public Optional<List<ProductDto>> getProductsWithKeywordName(String name) {
        CompletableFuture<List<Product>> allByKeywords = productRepository.findAllByKeywordsName(name);
        try {
            List<Product> products = allByKeywords.get();
            return Optional.of(productMapper.toDto(products));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Page<ProductDto> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toDto);
    }

    public Optional<List<ProductDto>> textSearch(String text) {
        CompletableFuture<List<Product>> allBy = productRepository.findTextSearch(text);
        try {
            return Optional.of(productMapper.toDto(allBy.get()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
