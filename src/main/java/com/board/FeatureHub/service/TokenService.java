package com.board.FeatureHub.service;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;

public class TokenService {

    private SecretKey key = Jwts.SIG.HS256.key().build();

    // 토큰 발급
    public String createJWT(String id) {

        String token = null;
        // LocalDateTime now = LocalDateTime.now();

        token = Jwts.builder()
                .header()
                    .keyId("aJwtKey")
                    .and()
                .claim("id", id)
                .subject("temp")
                .signWith(key)
                .compact();

        return token;
    }

    // 토큰

}
