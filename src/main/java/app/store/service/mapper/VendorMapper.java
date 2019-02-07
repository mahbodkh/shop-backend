package app.store.service.mapper;

import app.store.persistence.domain.Vendor;
import app.store.service.dto.VendorDto;
import app.store.service.mapper.util.Utils;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Utils.class})
public interface VendorMapper extends EntityMapper<VendorDto, Vendor> {
}
