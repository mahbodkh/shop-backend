package app.store.service.mapper;

import app.store.persistence.domain.User;
import app.store.service.dto.UserDto;
import app.store.service.mapper.util.Utils;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {Utils.class})
public interface UserMapper extends EntityMapper<UserDto, User> {

    @Override
//    @Mappings({
//            @Mapping(target = "", source = ""),
//            @Mapping(target = "", source = ""),
//    })
    User toEntity(UserDto dto);

    @Override
//    @Mappings({
//            @Mapping(target = "", source = ""),
//            @Mapping(target = "", source = ""),
//    })
    UserDto toDto(User entity);
}
