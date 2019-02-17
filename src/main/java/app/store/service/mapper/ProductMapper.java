package app.store.service.mapper;

import app.store.persistence.domain.Product;
import app.store.service.dto.ProductDto;
import app.store.service.mapper.util.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {Utils.class})
public interface ProductMapper extends EntityMapper<ProductDto, Product> {
    @Override
    @Mapping(target = "sold", ignore = true)
    Product toEntity(ProductDto dto);

    @Override
    ProductDto toDto(Product entity);
}
