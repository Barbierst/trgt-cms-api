package tech.trgt.trgtcmsapi.services;

import tech.trgt.trgtcmsapi.dtos.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserByUserName(String username);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void deleteUserByUsername(String username);
}
