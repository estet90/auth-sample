package ru.kononov.authsample.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@Slf4j
public class SampleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final String headerPrefix;
    private final String jwtSecretKey;
    private final long jwtExpiration;

    public SampleAuthenticationSuccessHandler(
            @Value("${application.header.authorization.prefix.bearer}") String headerPrefix,
            @Value("${application.jwt.secret-key}") String jwtSecretKey,
            @Value("${application.jwt.expiration}") int jwtExpiration
    ) {
        this.headerPrefix = headerPrefix;
        this.jwtSecretKey = jwtSecretKey;
        this.jwtExpiration = jwtExpiration;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        var redirectUrl = request.getParameter("redirect");
        log.debug("redirect to " + redirectUrl);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var token = generateToken(authentication);
        var authorization = headerPrefix + " " + token;
        response.setHeader(AUTHORIZATION, authorization);
        response.sendRedirect(redirectUrl);
        log.debug("redirect success");
    }

    private String generateToken(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        var now = new Date();
        var expiryDate = new Date(now.getTime() + jwtExpiration);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }

}
