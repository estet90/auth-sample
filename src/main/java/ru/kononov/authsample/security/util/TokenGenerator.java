package ru.kononov.authsample.security.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class TokenGenerator {

    private final String jwtSecretKey;
    private final long jwtExpiration;


    public TokenGenerator(
            @Value("${application.jwt.secret-key}") String jwtSecretKey,
            @Value("${application.jwt.expiration}") int jwtExpiration
    ) {
        this.jwtSecretKey = jwtSecretKey;
        this.jwtExpiration = jwtExpiration;
    }

    public String generateToken(Authentication authentication) {
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

    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            log.error("Validation token error: ", e);
        }
        return false;
    }

}
