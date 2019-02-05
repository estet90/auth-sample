package ru.kononov.authsample.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kononov.authsample.mapper.UserMapper;
import ru.kononov.authsample.model.dto.UserDto;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Long save(UserDto userDto) {
        var user = userMapper.userDtoToUser(userDto);
        user = userRepository.save(user);
        return user.getId();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
