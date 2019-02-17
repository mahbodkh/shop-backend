package app.store.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private final ProductService productService;
    private final CategoryService categoryService;

    public SearchService(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    public List getSimpleQuery(String value) {
        List dtos = new ArrayList();
        productService.textSearch(value)
                .ifPresent(dtos::add);
        categoryService.textSearch(value)
                .ifPresent(dtos::add);
        return dtos;
    }

}
