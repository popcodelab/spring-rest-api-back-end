package com.pop.codelab.chatopbackend.auth.service;

import com.pop.codelab.chatopbackend.auth.dto.requests.AuthenticateDto;
import com.pop.codelab.chatopbackend.auth.dto.requests.RegisterDto;
import com.pop.codelab.chatopbackend.auth.dto.responses.JwtDto;
import com.pop.codelab.chatopbackend.auth.dto.responses.UserDto;
import org.springframework.security.core.Authentication;

/**
 * The AuthenticationService interface provides methods for user authentication and registration.
 */
public interface AuthenticationService {

    /**
     * Registers a new user.
     *
     * @param userDto The RegisterDto object representing the user information to be registered.
     * @return The JwtDto object containing the access token for the registered user.
     */
    JwtDto register(RegisterDto userDto);

    /**
     * Authenticates a user.
     *
     * @param loginDto The AuthenticateDto object containing the user's email and password.
     * @return The JwtDto object containing the access token for the authenticated user.
     */
    JwtDto authenticate(AuthenticateDto loginDto);

    /**
     * Retrieves the information of the authenticated user.
     *
     * @param principalUser The Authentication object representing the authenticated user.
     * @return The UserDto object containing the details of the authenticated user.
     */
    UserDto getUserInformations(Authentication principalUser);
}
