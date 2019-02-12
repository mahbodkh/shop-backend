package app.store.service;

import app.store.persistence.domain.Category;
import app.store.persistence.repository.CategoryRepository;
import app.store.persistence.repository.ProductRepository;
import app.store.service.dto.CategoryDto;
import app.store.service.mapper.CategoryMapper;
import app.store.web.rest.error.CategoryAlreadyUsedException;
import app.store.web.rest.error.CategoryCanNotDeletedException;
import app.store.web.rest.error.CategoryNotFoundException;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class CategoryService {
    private final Logger log = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, ProductRepository productRepository) {

        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.categoryMapper = categoryMapper;

    }

    public Optional<Category> isExistByName(String name) {
        return categoryRepository.findOneByName(name);
    }

    public boolean isExists(String categoryId) {
        log.debug("Check Information for Category id: {}", categoryId);
        return categoryRepository.existsById(new ObjectId(categoryId));
    }


    public String createCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        log.debug("Save Information for Category: {}", category);
        if (category.getAncestors() != null) {
            updateAncestors(category.getAncestors());

        }
        return categoryRepository.save(category).getId().toString();
    }

    private void updateAncestors(List<ObjectId> ancestors) {
        for (ObjectId id : ancestors) {
            Optional<Category> category = categoryRepository.findOneById(id);
            if (category.isPresent()) {
                Category cat = category.get();
                cat.setMember(cat.getMember() + 1);

                //Todo we can update, parent other data
                //
                categoryRepository.save(cat);
            } else
                throw new CategoryNotFoundException("Category ancestor is not exist");
        }
    }


    public Optional<CategoryDto> getCategory(String id) {
        Optional<Category> category = categoryRepository.findById(new ObjectId(id));
        Category result = category.get();
        log.debug("Get Information for Category: {}", id);
        return Optional.of(categoryMapper.toDto(result));
    }


    public Optional<List<CategoryDto>> getRootCategories() {
        CompletableFuture<List<Category>> categoryList = categoryRepository.findAllByParentIsNullAndAncestorsNotNull();
        List<Category> result = null;
        try {
            result = categoryList.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        log.debug("Get Information for All root Categories");
        return Optional.of(categoryMapper.toDto(result));
    }

    public Optional<List<CategoryDto>> getSubCategory(String id) {
        CompletableFuture<List<Category>> allByAncestors = categoryRepository.findAllByAncestors(new ObjectId(id));
        log.debug("Get Information for Sub Categories: {}", id);
        try {
            return Optional.of(categoryMapper.toDto(allByAncestors.get()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public Optional<CategoryDto> updateCategory(CategoryDto categoryDto, String id) {
        return Optional.of(categoryRepository.findOneById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(category -> {
                    category.setName(categoryDto.getName());
                    category.setPath(categoryDto.getPath());
                    category.setDescription(categoryDto.getDescription());
                    category.setFacets(categoryDto.getFacets());
                    category.setCover(categoryDto.getCover());
                    if (categoryDto.getParent() != null)
                        category.setParent(new ObjectId(categoryDto.getParent()));

                    if (categoryDto.getAncestors() != null) {
                        List<String> ancestors = categoryDto.getAncestors();
                        List<ObjectId> ancestorObjectId = new ArrayList<>();
                        for (String value : ancestors) {
                            ObjectId parentObjectId = new ObjectId(value);
                            ancestorObjectId.add(parentObjectId);
                            if (categoryRepository.existsById(parentObjectId)) {
                                Optional<Category> categoryParent = categoryRepository.findOneById(parentObjectId);
                                categoryParent.ifPresent(r -> {
                                    if (r.getMember() == null) {
                                        r.setMember(1);
                                    } else
                                        r.setMember(r.getMember() + 1);

                                });

                            } else {
                                throw new CategoryAlreadyUsedException();
                            }
                        }
                        category.setAncestors(ancestorObjectId);
                    }
                    log.debug("Changed Information for Category: {}", category);
                    categoryRepository.save(category);
                    return categoryMapper.toDto(category);
                });
    }


    public void deleteCategory(String id) {
        categoryRepository.findOneById(new ObjectId(id))
                .ifPresent(category -> {
                    if (isAncestors(category.getId())) {
//                        if (isProductExistByCategoryId(category.getId()))
                        categoryRepository.delete(category);
                    }
                    log.debug("Deleted Category: {}", category);
                });
    }

//    private boolean isProductExistByCategoryId(ObjectId categoryId) {
//        return productService.isProductExistByCategoryId(categoryId.toString());
//    }

    private boolean isAncestors(ObjectId id) {
        CompletableFuture<List<Category>> allByAncestors = categoryRepository.findAllByAncestors(id);
        try {
            List<Category> categoryList = allByAncestors.get();
            if (categoryList.size() == 0)
                return true;
            else
                throw new CategoryCanNotDeletedException();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Page<CategoryDto> getAllCategories(Pageable pageable) {
        return categoryRepository.findAllBy(pageable)
                .map(categoryMapper::toDto);
    }
}
