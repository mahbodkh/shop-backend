package app.store.service.mapper;

import app.store.persistence.domain.Keyword;
import app.store.service.dto.KeywordDto;
import app.store.service.mapper.util.Utils;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Utils.class})
public interface KeywordMapper extends EntityMapper<KeywordDto, Keyword> {
}
