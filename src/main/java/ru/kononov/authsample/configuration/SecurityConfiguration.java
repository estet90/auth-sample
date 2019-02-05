package ru.kononov.authsample.configuration;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kononov.authsample.dao.UserRepository;
import ru.kononov.authsample.security.SampleUserDetailsService;

import static java.util.Objects.nonNull;

@Configuration
public class SecurityConfiguration {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence password) {
                return DigestUtils.md2Hex(password.toString());
            }

            @Override
            public boolean matches(CharSequence password, String encodedPassword) {
                return nonNull(password) && encodedPassword.equals(DigestUtils.md2Hex(password.toString()));
            }
        };
    }

    @Bean
    UserDetailsService userDetailService(UserRepository userRepository) {
        return new SampleUserDetailsService(userRepository);
    }

}
