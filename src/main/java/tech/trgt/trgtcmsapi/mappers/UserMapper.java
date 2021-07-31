package tech.trgt.trgtcmsapi.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.trgt.trgtcmsapi.dtos.UserDto;
import tech.trgt.trgtcmsapi.models.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
}
