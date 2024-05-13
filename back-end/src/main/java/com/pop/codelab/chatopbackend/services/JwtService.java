package com.pop.codelab.chatopbackend.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.pop.codelab.chatopbackend.auth.dto.responses.JwtDto;
import com.pop.codelab.chatopbackend.business.user.entity.User;

/**
 * The JwtService interface defines methods for generating and decoding JSON Web Tokens (JWT).
 */
public interface JwtService {

    /**
     * Generates a JSON Web Token (JWT) for the given user.
     *
     * @param user the user for which the JWT is generated
     * @return the JWT data transfer object containing the access token
     */
    JwtDto generateToken(User user);

    /**
     * Decodes the provided JSON Web Token (JWT).
     *
     * @param token The JWT to decode.
     * @return The decoded JWT.
     */
    DecodedJWT decodeJwtToken(String token);

}
