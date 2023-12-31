package com.javalier.simplespringbootjwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTManager {


    @Value("${jwt.secret.key}")
    private String jwtSecretKey;
    @Value("${jwtExpirationTime}")
    private int jwtExpirationTime;

    public String genarateJwt(String username) {
        return Jwts.builder()
                .subject(username)
                .issuer("javalier.net")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationTime))
                .signWith(key())
                .compact();
    }

    public SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
    }

    public boolean validateJwt(String jwt) {
        return (getUsernameJwt(jwt) != null && isJwtExpired(jwt));
    }

    public String getUsernameJwt(String jwt) {
        return getClaims(jwt).getSubject();
    }

    public boolean isJwtExpired(String jwt) {
        return getClaims(jwt).getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Claims getClaims(String jwt) {
        return Jwts.parser().verifyWith(key()).build().parseSignedClaims(jwt).getPayload();
    }

}
