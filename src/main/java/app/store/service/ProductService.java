package app.store.service;

import app.store.persistence.domain.Product;
import app.store.persistence.repository.ProductRepository;
import app.store.service.dto.ProductDto;
import app.store.service.mapper.ProductMapper;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;


    public ProductService(ProductRepository productRepository, CategoryService categoryService, ProductMapper productMapper) {

        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
    }

    public boolean isExists(String productId) {
        return productRepository.existsById(new ObjectId(productId));
    }

    public Optional<Product> isExistTitle(String title) {
        return productRepository.findOneByTitle(title);
    }

    public String createProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        categoryService.isValid(product.getCategories());
        return productRepository.save(product).getId().toString();
    }


}
