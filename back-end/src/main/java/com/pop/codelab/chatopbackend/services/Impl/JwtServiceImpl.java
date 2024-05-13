package com.pop.codelab.chatopbackend.services.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pop.codelab.chatopbackend.auth.dto.responses.JwtDto;
import com.pop.codelab.chatopbackend.business.user.entity.User;
import com.pop.codelab.chatopbackend.services.JwtService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * The JwtServiceImpl class is responsible for generating and validating JSON Web Tokens (JWT).
 * It provides methods for extracting claims from a token, generating a token based on user details,
 * and checking if a token is valid and not expired.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Service
@Log4j2
public class JwtServiceImpl implements JwtService {

    /**
     * The secret key used for generating and validating JSON Web Tokens (JWT).
     * The value of this key is retrieved from the application configuration.
     *
     * @see JwtServiceImpl
     */
    @Value("${application.security.jwt.secret-key}")
    private String jwtSecretKey;

    /**
     * The jwtExpiration variable represents the expiration time (in milliseconds) of JSON Web Tokens (JWT)
     * used for authentication and authorization.
     */
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    /**
     * Generates a JSON Web Token (JWT) for the given user.
     *
     * @param user The user for whom the token is generated.
     * @return The generated JWT as a JwtDto object.
     */
    public JwtDto generateToken(final User user) {
        JwtDto response = JwtDto.builder().accessToken(this.buildJwtToken(user)).build();
        log.debug("Generated token : {}", response);
        return response;
    }

    /**
     * Decodes a JSON Web Token (JWT) and returns the decoded token.
     *
     * @param token The JWT token to decode
     * @return The decoded JWT token as a DecodedJWT object
     */
    public DecodedJWT decodeJwtToken(final String token) {
        return JWT
                .require(generateCheckJwtSignature())
                .build()
                .verify(token);
    }

    /**
     * Builds a JSON Web Token (JWT) for the given user.
     *
     * @param user The user for whom the token is generated.
     * @return The generated JWT as a string.
     */
    private String buildJwtToken(final User user) {
        return JWT.create()
                .withSubject(user.getId().toString())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiration))
                .sign(generateCheckJwtSignature());
    }

    /**
     * Generates the signature for checking the JSON Web Token (JWT).
     * The signature is generated using the HMAC256 algorithm and the secret key.
     *
     * @return The generated JWT signature as an Algorithm object.
     */
    private Algorithm generateCheckJwtSignature() {
        return Algorithm.HMAC256(jwtSecretKey);
    }


}
