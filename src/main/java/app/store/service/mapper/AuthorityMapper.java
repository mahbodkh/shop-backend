package app.store.service.mapper;

import app.store.persistence.domain.Authority;
import app.store.service.dto.AuthorityDto;
import app.store.service.mapper.util.Utils;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Utils.class})
public interface AuthorityMapper extends EntityMapper<AuthorityDto, Authority> {
}
