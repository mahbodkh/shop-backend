package app.store.service.mapper;

import app.store.persistence.domain.Category;
import app.store.service.dto.CategoryDto;
import app.store.service.mapper.util.Utils;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {Utils.class})
public interface CategoryMapper extends EntityMapper<CategoryDto, Category> {

    @Override
    Category toEntity(CategoryDto dto);

    @Override
    CategoryDto toDto(Category entity);

    @Override
    List<Category> toEntity(List<CategoryDto> dtoList);

    @Override
    List<CategoryDto> toDto(List<Category> entityList);
}
