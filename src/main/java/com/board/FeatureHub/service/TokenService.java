package com.board.FeatureHub.service;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;

public class TokenService {

    private SecretKey key = Jwts.SIG.HS256.key().build();

    public String createJWT(String userId) {

        String token = null;
        LocalDateTime now = LocalDateTime.now();
        // Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        token = Jwts.builder()
                .header()
                    .keyId("aJwtKey")
                    .and()
                .claim("userId", userId)
                .subject("Bob")
                .expiration(now)
                .signWith(key)
                .compact();


        return token;
    }

}
