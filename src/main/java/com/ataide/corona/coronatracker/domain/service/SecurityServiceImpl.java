package com.ataide.corona.coronatracker.domain.service;


import com.ataide.corona.coronatracker.domain.service.interfaces.SecurityService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SecurityServiceImpl  implements SecurityService {

    private final String JWT_TOKEN_KEY;
    private final Long JWT_TOKEN_EXPIRATION_TIME;

    public SecurityServiceImpl(@Value("${jwt.token.key}") String JWT_TOKEN_KEY, @Value("${jwt.token.expiration.time}") Long JWT_TOKEN_EXPIRATION_TIME) {
        this.JWT_TOKEN_KEY = JWT_TOKEN_KEY;
        this.JWT_TOKEN_EXPIRATION_TIME = JWT_TOKEN_EXPIRATION_TIME;
    }

    public String getSubject(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(JWT_TOKEN_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            return null;
        }

    }

    public String getToken(String username) {
        long nowMilliSeconds = System.currentTimeMillis();
        long expirationTime = JWT_TOKEN_EXPIRATION_TIME.longValue();
        Date now = new Date(nowMilliSeconds);

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, JWT_TOKEN_KEY)
                .setExpiration(new Date(nowMilliSeconds + expirationTime))
                .setIssuedAt(now)
                .setSubject(username)
                .compact();
    }

    public String encrypt(String plainText) {
        String saltGenerated = BCrypt.gensalt();
        return BCrypt.hashpw(plainText, saltGenerated);
    }

    public Boolean authenticate(String candidatePassword, String databasePassword) {
        return BCrypt.checkpw(candidatePassword, databasePassword);
    }
}
