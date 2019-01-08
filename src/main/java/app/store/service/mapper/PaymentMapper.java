package app.store.service.mapper;

import app.store.persistence.domain.Payment;
import app.store.service.dto.PaymentDto;
import app.store.service.mapper.util.Utils;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Utils.class})
public interface PaymentMapper extends EntityMapper<PaymentDto, Payment> {

    @Override
    Payment toEntity(PaymentDto dto);

    @Override
    PaymentDto toDto(Payment entity);
}
