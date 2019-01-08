package app.store.service.mapper;

import app.store.persistence.domain.Brand;
import app.store.service.dto.BrandDto;
import app.store.service.mapper.util.Utils;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Utils.class})
public interface BrandMapper extends EntityMapper<BrandDto, Brand> {

    @Override
    Brand toEntity(BrandDto dto);

    @Override
    BrandDto toDto(Brand entity);
}
