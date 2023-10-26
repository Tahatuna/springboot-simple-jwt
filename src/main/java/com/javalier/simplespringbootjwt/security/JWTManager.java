package com.javalier.simplespringbootjwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTManager {

    SecretKey key = Jwts.SIG.HS256.key().build();
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
                .signWith(SignatureAlgorithm.forSigningKey(key), jwtSecretKey)
                .compact();
    }

    public boolean validateJwt(String jwt) {
        return (getUsernameJwt(jwt) != null && isJwtExpired(jwt));
    }

    public String getUsernameJwt(String jwt) {
        return getClaims(jwt).getSubject();
    }

    public boolean isJwtExpired(String jwt) {
        return getClaims(jwt).getExpiration().before(new Date(System.currentTimeMillis()));
    }

    private Claims getClaims(String jwt) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
    }

}
