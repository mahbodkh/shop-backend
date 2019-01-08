package app.store.service.mapper;

import app.store.persistence.domain.Invoice;
import app.store.service.dto.InvoiceDto;
import app.store.service.mapper.util.Utils;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Utils.class})
public interface InvoiceMapper extends EntityMapper<InvoiceDto, Invoice> {
    @Override
    Invoice toEntity(InvoiceDto dto);

    @Override
    InvoiceDto toDto(Invoice entity);
}
