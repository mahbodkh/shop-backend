package app.store.service.mapper;

import app.store.persistence.domain.Cart;
import app.store.service.dto.CartDto;
import app.store.service.mapper.util.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {Utils.class})
public interface CartMapper extends EntityMapper<CartDto, Cart> {
    @Override
    @Mapping(target = "total", ignore = true)
    Cart toEntity(CartDto dto);

    @Override
    CartDto toDto(Cart entity);

    @Override
    List<Cart> toEntity(List<CartDto> dtoList);

    @Override
    List<CartDto> toDto(List<Cart> entityList);
}
