package app.store.service;

import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private final ProductService productService;

    public SearchService(ProductService productService) {
        this.productService = productService;
    }


}
