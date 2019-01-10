package app.store.service;

import app.store.persistence.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean isExists(String productId) {
        return productRepository.existsById(new ObjectId(productId));
    }
}
