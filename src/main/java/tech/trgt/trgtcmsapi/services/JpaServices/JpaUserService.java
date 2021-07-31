package tech.trgt.trgtcmsapi.services.JpaServices;

import org.springframework.stereotype.Service;
import tech.trgt.trgtcmsapi.dtos.UserDto;
import tech.trgt.trgtcmsapi.mappers.UserMapper;
import tech.trgt.trgtcmsapi.models.User;
import tech.trgt.trgtcmsapi.repositories.UserRepository;
import tech.trgt.trgtcmsapi.services.ResourceNotFoundException;
import tech.trgt.trgtcmsapi.services.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JpaUserService implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public JpaUserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByUserName(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new ResourceNotFoundException();
        }

        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        user.setUuid(UUID.randomUUID().toString());

        return saveAndReturnDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User original = userRepository.findByUsername(userDto.getUsername());

        if (original == null) {
            throw new ResourceNotFoundException();
        }

        User user = userMapper.userDtoToUser(userDto);
        user.setUuid(original.getUuid());
        user.setId(original.getId());

        return saveAndReturnDto(user);
    }

    @Override
    public void deleteUserByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    private UserDto saveAndReturnDto(User user)
    {
        User savedUser = userRepository.save(user);

        return userMapper.userToUserDto(savedUser);
    }
}
