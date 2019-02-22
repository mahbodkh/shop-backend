package app.store.service.mapper;

import app.store.persistence.domain.Order;
import app.store.service.dto.OrderDto;
import app.store.service.mapper.util.Utils;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Utils.class})
public interface OrderMapper extends EntityMapper<OrderDto, Order> {
}
