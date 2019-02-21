package app.store.web.rest;

import app.store.service.FileService;
import app.store.service.ProductService;
import app.store.service.dto.ProductDto;
import app.store.web.rest.error.ProductAlreadyUsedException;
import app.store.web.rest.error.ProductInvalidException;
import app.store.web.rest.error.ProductNotFoundException;
import app.store.web.rest.util.HeaderUtil;
import app.store.web.rest.util.PaginationUtil;
import app.store.web.rest.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductResource {
    private final Logger log = LoggerFactory.getLogger(ProductResource.class);


    private final ProductService productService;
    private final FileService fileService;

    public ProductResource(ProductService productService, FileService fileService) {

        this.productService = productService;
        this.fileService = fileService;
    }


    @PostMapping("/product")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductDto productDto, @Valid @RequestParam("file") MultipartFile[] file) throws URISyntaxException {
        log.debug("REST request to save Product : {}", productDto);
        if (productDto == null) {
            throw new ProductInvalidException();
        } else if (productService.getProductByTitle(productDto.getTitle()).isPresent()) {
            throw new ProductAlreadyUsedException();
        }
        String productId = productService.createProduct(productDto);
        fileService.uploadProduct(file, productId);

        return ResponseEntity.created(new URI("/api/product/" + productId))
                .headers(HeaderUtil.createAlert("product.created", productId))
                .body(productId);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProduct(@Valid @PathVariable String id) {
        log.debug("REST request to get Product : {}", id);
        Optional<ProductDto> product = productService.getProduct(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert("product.get", "isLogin or "))
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
                HeaderUtil.createAlert("product.updated", result.get().getId()));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@Valid @PathVariable String id) {
        log.debug("REST request to delete Product : {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("product.deleted", id)).build();
    }


    @GetMapping("/product/keyword")
    public ResponseEntity<List<ProductDto>> getProductListByKeywordName(@Valid String name) {
        log.debug("REST request to get Products by keyword name: {}", name);
        Optional<List<ProductDto>> result = productService.getProductsWithKeywordName(name);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert("product.keyword.get", " "))
                .body(result.get());
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(Pageable pageable) {
        log.debug("REST request to get all Product by pageable: {}", pageable);
        final Page<ProductDto> page = productService.getAllProducts(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
