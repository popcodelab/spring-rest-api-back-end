package com.pop.codelab.chatopbackend.auth;

import com.pop.codelab.chatopbackend.security.jwt.JwtService;
import com.pop.codelab.chatopbackend.business.user.User;
import com.pop.codelab.chatopbackend.business.user.UserRepository;
import com.pop.codelab.chatopbackend.business.user.dto.UserCreationDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The AuthenticationService class is responsible for user authentication and registration.
 * <p></p>
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    /**
     * The userRepository variable is an instance of the UserRepository interface.
     * It is used in the AuthenticationService class to perform CRUD operations on User entities.
     * <p></p>
     *
     * @see UserRepository
     * @see AuthenticationService
     */
    private final UserRepository userRepository;
    /**
     * The passwordEncoder variable is an instance of the PasswordEncoder interface.
     * It is used in the AuthenticationService class to encode and decode passwords.
     *
     * @see PasswordEncoder
     * @see AuthenticationService
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * The JwtService class is responsible for generating and validating JSON Web Tokens (JWT).
     * It provides methods for extracting claims from a token, generating a token based on user details,
     * and checking if a token is valid and not expired.
     */
    private final JwtService jwtService;

    /**
     * The authenticationManager variable represents an instance of the AuthenticationManager interface,
     * which is responsible for authenticating a user.
     * <p></p>
     * The authenticationManager is used by the AuthenticationService class to authenticate user requests.
     *
     * @see AuthenticationService#authenticate(AuthenticationRequest)
     * @see AuthenticationManager
     */
    private final AuthenticationManager authenticationManager;

    /**
     * ModelMapper instance to map a Dto to an Entity and vice versa.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * The logger variable is an instance of the Logger class from the org.slf4j package.
     **/
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    /**
     * Registers a new user.
     * <p></p>
     *
     * @param userCreationDto The UserCreationDto object representing the user information to be registered.
     * @return AuthenticationResponse The response object containing the access token for the registered user.
     */
    public AuthenticationResponse register(final UserCreationDto userCreationDto) {
        logger.debug("Register user : {}", userCreationDto);
        User user = modelMapper.map(userCreationDto, User.class);
        user.setPassword(passwordEncoder.encode(userCreationDto.getPassword()));
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    /**
     * Authenticates a user.
     * <p></p>
     *
     * @param request The AuthenticationRequest object containing the user's email and password.
     * @return The response object containing the access token for the authenticated user.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
