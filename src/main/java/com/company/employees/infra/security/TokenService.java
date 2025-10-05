package com.company.employees.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.company.employees.domain.model.User;
import com.company.employees.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {


    @Value("${api.security.token.secret}")
    private String secret;
    private UserRepository userRepository;

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
    public UserDetails validateToken(String token){
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .withClaim("type", "refresh")
                    .build()
                    .verify(token);

            return userRepository.findByLogin(jwt.getSubject());
    }
}
