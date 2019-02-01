package ru.kononov.authsample.mapper;

import org.apache.commons.codec.digest.DigestUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import ru.kononov.authsample.model.dao.User;
import ru.kononov.authsample.model.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "password", qualifiedByName = "passwordMapping")
    })
    User userDtoToUser(UserDto userDto);

    @Named("passwordMapping")
    default String passwordMapping(String password) {
        return DigestUtils.md2Hex(password);
    }

}
