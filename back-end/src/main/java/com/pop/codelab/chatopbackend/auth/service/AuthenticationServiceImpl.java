package com.pop.codelab.chatopbackend.auth.service;

import com.pop.codelab.chatopbackend.auth.dto.requests.AuthenticateDto;
import com.pop.codelab.chatopbackend.auth.dto.requests.RegisterDto;
import com.pop.codelab.chatopbackend.auth.dto.responses.JwtDto;
import com.pop.codelab.chatopbackend.auth.dto.responses.UserDto;
import com.pop.codelab.chatopbackend.business.user.UserRepository;
import com.pop.codelab.chatopbackend.business.user.entity.User;
import com.pop.codelab.chatopbackend.services.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The AuthenticationServiceImpl class is responsible for user authentication and registration.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationServiceImpl implements AuthenticationService {
    /**
     * The userRepository variable is an instance of the UserRepository interface.
     * It is used in the AuthenticationServiceImpl class to perform CRUD operations on User entities.
     *
     * @see UserRepository
     * @see AuthenticationServiceImpl
     */
    private final UserRepository userRepository;
    /**
     * The passwordEncoder variable is an instance of the PasswordEncoder interface.
     * It is used in the AuthenticationServiceImpl class to encode and decode passwords.
     *
     * @see PasswordEncoder
     * @see AuthenticationServiceImpl
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * The JwtServiceImpl class is responsible for generating and validating JSON Web Tokens (JWT).
     * It provides methods for extracting claims from a token, generating a token based on user details,
     * and checking if a token is valid and not expired.
     */
    private final JwtService jwtService;


    /**
     * ModelMapper instance to map a Dto to an Entity and vice versa.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Registers a user with the given user information.
     *
     * @param userDto The RegisterDto object representing the user information to be registered.
     * @return The JwtDto object representing the generated JWT token.
     */
    public JwtDto register(final RegisterDto userDto) {
        log.debug("Register user : {}", userDto);
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User registeredUser = userRepository.save(user);

        return this.jwtService.generateToken(registeredUser);
    }

    /**
     * Authenticates a user by checking the provided email and password against the user database.
     *
     * @param authDto The AuthenticateDto object containing the user's email and password.
     * @return The JwtDto object containing the generated JWT token.
     * @throws BadCredentialsException if the provided email and password do not match any user record.
     */
    public JwtDto authenticate(final AuthenticateDto authDto) {
        var user = userRepository.findByEmail(authDto.getEmail()).orElse(null);
        if (!this.checkUserPassword(user, authDto.getPassword())) {
            throw new BadCredentialsException("Wrong credentials !");
        }
        JwtDto response = jwtService.generateToken(user);
        log.debug("Authenticated user : {}", user);
        return response;
    }

    /**
     * Retrieves user information for the authenticated user.
     *
     * @param principal The Authentication object representing the authenticated user.
     * @return The user information as a UserDto object.
     */
    public UserDto getUserInformations(final Authentication principal) {
        User loggedInUser = (User) principal.getPrincipal();
        log.debug("UserDetails principal : {}", loggedInUser);
        User user = this.userRepository.findById(loggedInUser.getId()).orElse(null);
        log.debug("Me : {}", user);
        return this.modelMapper.map(user, UserDto.class);
    }

    /**
     * Checks if the provided password matches the user's stored password.
     *
     * @param user     the user object containing the stored password
     * @param password the password to check against the stored password
     * @return true if the provided password matches the stored password,
     * false otherwise or if the user object is null
     */
    private boolean checkUserPassword(final User user, final String password) {
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }


}
