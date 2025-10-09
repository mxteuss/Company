package com.company.employees.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.company.employees.domain.model.User;import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateAccessToken(User user){
        try{
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getLogin())
                    .withClaim("type", "acces")
                    .withExpiresAt(Instant.now().plus(15, ChronoUnit.MINUTES))
                    .sign(Algorithm.HMAC256(secret));}

        catch (JWTCreationException e){
            throw new RuntimeException("Error while generating token", e);
        }
    }

    public String generateRefreshToken(User user){
        return JWT.create()
                .withIssuer("auth-api")
                .withSubject(user.getLogin())
                .withClaim("type", "refresh")
                .withExpiresAt(Instant.now().plus(7, ChronoUnit.DAYS))
                .sign(Algorithm.HMAC256(secret));
    }
    public String validateToken(String token){
            Algorithm algorithm = Algorithm.HMAC256(secret);
        try{
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .withClaim("type", "refresh")
                    .build()
                    .verify(token)
                    .getSubject();

    } catch (JWTVerificationException e) {
           return e.getLocalizedMessage();
        }
}
}
