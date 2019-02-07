package app.store.web.rest;

import app.store.service.CategoryService;
import app.store.service.dto.CategoryDto;
import app.store.web.rest.error.CategoryAlreadyUsedException;
import app.store.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CategoryResource {
    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    private final CategoryService categoryService;

    public CategoryResource(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDto categoryDto) throws URISyntaxException {
        log.debug("REST request to save Category : {}", categoryDto);

        if (categoryService.isExistByName(categoryDto.getName()).isPresent()) {
            throw new CategoryAlreadyUsedException();
        }
        String categoryId = categoryService.createCategory(categoryDto);
        return ResponseEntity.created(new URI("/api/cart/" + categoryId))
                .headers(HeaderUtil.createAlert("cart.created", "isLogin or "))
                .body(categoryId);
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategory(@Valid @PathVariable String id) {
        log.debug("REST request to get Category : {}", id);
        Optional<CategoryDto> category = categoryService.getCategory(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert("cart.get", "isLogin or "))
                .body(category.get());
    }



    // sub
    @GetMapping("/category/sub/{id}")
    public ResponseEntity<List<CategoryDto>> getSubCategories(@PathVariable String id) {
        log.debug("REST request to get Sub Categories : {}", id);
        Optional<List<CategoryDto>> subCategory = categoryService.getSubCategory(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert("cart.get", "isLogin or "))
                .body(subCategory.get());
    }



}
