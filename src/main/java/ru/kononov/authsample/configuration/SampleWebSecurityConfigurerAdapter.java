package ru.kononov.authsample.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SampleWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${application.default-role}")
    private String defaultRole;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .successForwardUrl("/")
                .successHandler((request, response, authentication) -> {
                    System.out.println("siski!!!");
                    response.sendRedirect(request.getParameter("redirect"));
                })
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }

}
