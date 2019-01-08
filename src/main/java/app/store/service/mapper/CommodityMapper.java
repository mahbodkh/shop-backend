package app.store.service.mapper;

import app.store.persistence.domain.Commodity;
import app.store.service.dto.CommodityDto;
import app.store.service.mapper.util.Utils;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Utils.class})
public interface CommodityMapper extends EntityMapper<CommodityDto, Commodity> {
    @Override
    Commodity toEntity(CommodityDto dto);

    @Override
    CommodityDto toDto(Commodity entity);
}
