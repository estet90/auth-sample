package ru.kononov.authsample.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kononov.authsample.dao.UserRepository;

import java.util.List;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Slf4j
public class SampleUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Value("${default-role}")
    private String defaultRole;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("User try log in with username {}", username);
        var user = ofNullable(userRepository.findByLogin(username))
                .orElseThrow(() -> {
                    var exception = new UsernameNotFoundException(String.format("User with login %s was not found", username));
                    log.error("Error: ", exception);
                    return exception;
                });
        var authorities = List.of(new SimpleGrantedAuthority(defaultRole));
        log.debug("Log in complete");
        return new User(user.getLogin(), user.getPassword(), authorities);
    }

}
