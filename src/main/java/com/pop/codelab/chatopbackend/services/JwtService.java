package com.pop.codelab.chatopbackend.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pop.codelab.chatopbackend.auth.dto.responses.JwtDto;
import com.pop.codelab.chatopbackend.business.user.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * The JwtService class is responsible for generating and validating JSON Web Tokens (JWT).
 * It provides methods for extracting claims from a token, generating a token based on user details,
 * and checking if a token is valid and not expired.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Service
@Log4j2
public class JwtService {

    /**
     * The secret key used for generating and validating JSON Web Tokens (JWT).
     * The value of this key is retrieved from the application configuration.
     *
     * @see JwtService
     */
    @Value("${application.security.jwt.secret-key}")
    private String jwtSecretKey;

    /**
     * The jwtExpiration variable represents the expiration time (in milliseconds) of JSON Web Tokens (JWT)
     * used for authentication and authorization.
     */
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;


    public JwtDto generateToken(final User user) {
        JwtDto response = JwtDto.builder().accessToken(this.buildJwtToken(user)).build();
        log.debug("Generated token : {}", response);
        return response;
    }

    public DecodedJWT decodeJwtToken(String token) {
        return JWT
                .require(generateCheckJwtSignature())
                .build()
                .verify(token);
    }


    private String buildJwtToken(final User user) {
        return JWT.create()
                .withSubject(user.getId().toString())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiration))
                .sign(generateCheckJwtSignature());
    }

    private Algorithm generateCheckJwtSignature() {
        return Algorithm.HMAC256(jwtSecretKey);
    }


}
