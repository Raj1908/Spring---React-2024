package com.management.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;

public class JwtProvider {

    private static final SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET.getBytes());

    public static String generateToken(Authentication authentication) {
        return Jwts.builder().issuedAt(new Date())
                .expiration(new Date(new Date().getTime() * 86400000))
                .claim("email",authentication.getName())
                .signWith(key)
                .compact();
    }

    public static String getEmailFromToken(String jwt) {
        Claims claims = Jwts.parser().decryptWith(key).build().parseUnsecuredClaims(jwt).getPayload();
        return String.valueOf(claims.get("email"));
    }
}
