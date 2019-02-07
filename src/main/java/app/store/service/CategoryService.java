package app.store.service;

import app.store.persistence.domain.Category;
import app.store.persistence.repository.CategoryRepository;
import app.store.service.dto.CategoryDto;
import app.store.service.mapper.CategoryMapper;
import app.store.web.rest.error.CategoryAlreadyUsedException;
import app.store.web.rest.error.CategoryNotFoundException;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final Logger log = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {

        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public Optional<Category> isExistByName(String name) {
        return categoryRepository.findOneByName(name);
    }

    public boolean isExists(String productId) {
        return categoryRepository.existsById(new ObjectId(productId));
    }


    public String createCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        return categoryRepository.save(category).getId().toString();
    }

    public void isValid(List<ObjectId> categories) {
        if (categories != null) {
            for (ObjectId id : categories) {
                if (!categoryRepository.existsById(id))
                    throw new CategoryNotFoundException();
            }
        }
    }

    public Optional<CategoryDto> getCategory(String id) {
        Optional<Category> category = categoryRepository.findById(new ObjectId(id));
        Category result = category.get();
        return Optional.of(categoryMapper.toDto(result));
    }

    public Optional<List<CategoryDto>> getSubCategory(String id) {
        Optional<Category> category = categoryRepository.findById(new ObjectId(id));
        List<CategoryDto> categoryList = new ArrayList<>();
        Category result = category.get();
        if (result.getAncestors() != null) {
            for (ObjectId childsId : result.getAncestors()){
                Optional<Category> oneById = categoryRepository.findOneById(childsId);
                categoryList.add(categoryMapper.toDto(oneById.get()));
            }
        }
        return Optional.of(categoryList);
    }


    public Optional<CategoryDto> updateCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        if (category.getAncestors() != null) {
            // update the father member
            List<ObjectId> parents = category.getAncestors();
            for (ObjectId id : parents) {
                if (categoryRepository.existsById(id)) {
                    Optional<Category> categoryParent = categoryRepository.findOneById(id);


                } else {
                    throw new CategoryAlreadyUsedException();
                }
            }
        }
        return null;
    }

}
