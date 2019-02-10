package app.store.web.rest;

import app.store.service.CategoryService;
import app.store.service.ProductService;
import app.store.service.dto.ProductDto;
import app.store.web.rest.error.ProductAlreadyUsedException;
import app.store.web.rest.error.ProductInvalidException;
import app.store.web.rest.error.ProductNotFoundException;
import app.store.web.rest.util.HeaderUtil;
import app.store.web.rest.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

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
        } else if (productService.getProductByTitle(productDto.getTitle()).isPresent()) {
            throw new ProductAlreadyUsedException();
        }
        String productId = productService.createProduct(productDto);

        return ResponseEntity.created(new URI("/api/product/" + productId))
                .headers(HeaderUtil.createAlert("productManagement.created", productId))
                .body(productId);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProduct(@Valid @PathVariable String id) {
        log.debug("REST request to get Product : {}", id);
        Optional<ProductDto> product = productService.getProduct(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert("category.get", "isLogin or "))
                .body(product.get());
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto, @Valid @PathVariable String id) {
        log.debug("REST request to update Product : {} with id : {}", productDto, id);
        if (!productService.isExists(id)) {
            throw new ProductNotFoundException();
        }
        Optional<ProductDto> result = productService.updateProduct(productDto, id);
        return ResponseUtil.wrapOrNotFound(result,
                HeaderUtil.createAlert("productManagement.updated", result.get().getId()));
    }


}
