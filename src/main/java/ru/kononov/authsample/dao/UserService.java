package ru.kononov.authsample.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kononov.authsample.mapper.UserMapper;
import ru.kononov.authsample.model.dao.User;
import ru.kononov.authsample.model.dto.UserDto;

import static java.util.Optional.ofNullable;

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

    public User find(UserDto userDto) {
        var user = userMapper.userDtoToUser(userDto);
        return ofNullable(userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword()))
                .orElseThrow();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
