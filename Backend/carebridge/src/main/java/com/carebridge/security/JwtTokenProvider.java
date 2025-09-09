package com.carebridge.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;


@Component
public class JwtTokenProvider {

private final String Jwt_SECRET  = "carebridgeSecretKey0001";
private final long JWT_EXPIRATION= 86400000; // 1 day in milliseconds this is for 24 hours


public String generateToken(String username){
    Date now= new Date();
    Date expiryDate= new Date(now.getTime() + JWT_EXPIRATION);
    return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, Jwt_SECRET)
            .compact();
}

public String getUsernameFromJWT(String token){
    Claims claims = Jwts.parser()
                        .setSigningKey(Jwt_SECRET)
                        .parseClaimsJws(token)
                        .getBody();

    return claims.getSubject();
}


public  boolean validateToken(String token){
    try{
        Jwts.parser().setSigningKey(Jwt_SECRET).parseClaimsJws(token);
        return true;
    }catch(ExpiredJwtException | MalformedJwtException | SignatureException ex ){
        return false;
    }
}
}
