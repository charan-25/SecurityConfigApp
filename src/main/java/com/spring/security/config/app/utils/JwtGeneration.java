package com.spring.security.config.app.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtGeneration {

    private final String secretKey = "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b";

    @Value("${security.jwt.expiration-time}")
    private long expiry;


    public String extractUserName(String token){
        return extractClaims(token,Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims,T> claimsResolve){
        Claims claims = extractAllClaims(token);
        return claimsResolve.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isValidToken(String token){
        return !extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }

    public String generateToken(UserDetails user){
        return buildToken(new HashMap<>(),user);
    }

    private String buildToken(Map<String,Object> claims, UserDetails user){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiry))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public long getExpirationTime(){
        return expiry;
    }
}
