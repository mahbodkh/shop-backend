package app.store.web.rest;

import app.store.service.CategoryService;
import app.store.service.ProductService;
import app.store.service.dto.ProductDto;
import app.store.web.rest.error.ProductAlreadyUsedException;
import app.store.web.rest.error.ProductInvalidException;
import app.store.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1")
public class ProductResource {
    private final Logger log = LoggerFactory.getLogger(ProductResource.class);


    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductResource(ProductService productService, CategoryService categoryService) {

        this.productService = productService;
        this.categoryService = categoryService;
    }


    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductDto productDto) throws URISyntaxException {
        log.debug("REST request to save Product : {}", productDto);
        if (productDto == null) {
            throw new ProductInvalidException();
        }
        else if (productService.getProductByTitle(productDto.getTitle()).isPresent()){
            throw new ProductAlreadyUsedException();
        }
        String productId = productService.createProduct(productDto);

        return ResponseEntity.created(new URI("/api/product/" + productId))
                .headers(HeaderUtil.createAlert("productManagement.created", productId))
                .build();
    }





}
