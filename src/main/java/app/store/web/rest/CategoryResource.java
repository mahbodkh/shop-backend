package app.store.web.rest;

import app.store.service.CategoryService;
import app.store.service.dto.CategoryDto;
import app.store.web.rest.error.BadRequestAlertException;
import app.store.web.rest.error.CategoryAlreadyUsedException;
import app.store.web.rest.error.CategoryNotFoundException;
import app.store.web.rest.util.HeaderUtil;
import app.store.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.created(new URI("/category/" + categoryId))
                .headers(HeaderUtil.createAlert("category.created", "isLogin or "))
                .body(categoryId);
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategory(@Valid @PathVariable String id) {
        log.debug("REST request to get Category : {}", id);
        if (id == null || !categoryService.isExists(id))
            throw new CategoryNotFoundException();
        Optional<CategoryDto> category = categoryService.getCategory(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert("category.get", "isLogin or "))
                .body(category.get());
    }


    // sub
    @GetMapping("/category/sub/{id}")
    public ResponseEntity<List<CategoryDto>> getSubCategories(@Valid @PathVariable String id) {
        log.debug("REST request to get Sub Categories : {}", id);
        if (id == null || !categoryService.isExists(id))
            throw new CategoryNotFoundException();
        Optional<List<CategoryDto>> subCategory = categoryService.getSubCategory(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert("sub.category.get", "isLogin or "))
                .body(subCategory.get());
    }

    //root
    @GetMapping("/category/root")
    public ResponseEntity<List<CategoryDto>> getRootCategories() {
        log.debug("REST request to get all root Categories");
        Optional<List<CategoryDto>> subCategory = categoryService.getRootCategories();
        return ResponseEntity.ok()
                .headers(HeaderUtil.createAlert("root.categories.get", "isLogin or "))
                .body(subCategory.get());
    }


    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @Valid @PathVariable String id) throws URISyntaxException {
        log.debug("REST request to update Category: {} with id : {}", categoryDto, id);
        if (id == null)
            throw new BadRequestAlertException("Category ID is null", "CategoryDto", "updateCategory");
        else if (categoryDto == null || !categoryService.isExists(id))
            throw new CategoryNotFoundException();
        else {
            Optional<CategoryDto> result = categoryService.updateCategory(categoryDto, id);
            return ResponseEntity.created(new URI("/category/" + result.get().getId()))
                    .headers(HeaderUtil.createAlert("category.created", ""))
                    .body(result.get());
        }
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteCategory(@Valid @PathVariable String id) {
        log.debug("REST request to delete Category : {}", id);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("category.deleted", id)).build();
    }

    @GetMapping("/categories")
    public ResponseEntity<Page<CategoryDto>> getAllCategories(Pageable pageable) {
        log.debug("REST request to get all Category by pageable: {}", pageable);
        final Page<CategoryDto> page = categoryService.getAllCategories(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/category/");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

}
