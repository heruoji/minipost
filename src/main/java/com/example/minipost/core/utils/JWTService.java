package com.example.minipost.core.utils;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JWTService {

    private final String secretKey;
    private final long expirationTime;

    public JWTService(String secretKey, long expirationTime) {
        this.secretKey = secretKey;
        this.expirationTime = expirationTime;
    }

    public String createJWT(JWTPrinciple principle) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder().setIssuer(principle.issuer).setSubject(principle.id.toString())
                .claim("id", principle.id)
                .claim("username", principle.username)
                .claim("role", principle.role)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expirationTime))
                .signWith(key).compact();
    }
}
