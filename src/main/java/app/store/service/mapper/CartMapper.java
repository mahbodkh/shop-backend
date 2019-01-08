package app.store.service.mapper;

import app.store.persistence.domain.Cart;
import app.store.service.dto.CartDto;
import app.store.service.mapper.util.Utils;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Utils.class})
public interface CartMapper extends EntityMapper<CartDto, Cart> {
    @Override
    Cart toEntity(CartDto dto);

    @Override
    CartDto toDto(Cart entity);
}
