package app.store.service;

import app.store.persistence.domain.Category;
import app.store.persistence.domain.Product;
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

//    public List getSimpleQuery(String value) {
//        List dtos = new ArrayList();
//        List<Product> products = productService.getProductFullTextSearch(value);
//        dtos.add(products);
////        List<Category> categories = categoryService.getCategoriesFullText(value);
////        dtos.add(categories);
//        return dtos;
//    }


}
