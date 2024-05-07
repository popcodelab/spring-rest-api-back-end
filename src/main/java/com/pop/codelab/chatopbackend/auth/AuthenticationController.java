package com.pop.codelab.chatopbackend.auth;

import com.pop.codelab.chatopbackend.business.rental.RentalController;
import com.pop.codelab.chatopbackend.business.user.dto.UserCreationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * The AuthenticationController class handles the authentication-related API endpoints.
 */
@RestController
@RequestMapping("/api/auth")
// TODO Remove CrossOrigin anotation in ALL controllers
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST})
@RequiredArgsConstructor
public class AuthenticationController {

    /**
     * Logger for the RentalController class.
     * It is used to log messages and events related to the RentalController class.
     * The logger is initialized using the LoggerFactory.getLogger method and the class name RentalController.class.
     * The logger should be private, static, and final to ensure thread-safety and avoid any unintended modifications.
     */
    private static final Logger logger = LoggerFactory.getLogger(RentalController.class);

    /**
     * The AuthenticationService class provides methods for user authentication and registration.
     */
    private final AuthenticationService authenticationService;

    /**
     * Registers a new user.
     *
     * @param userDto The UserCreationDto object representing the user information to be registered.
     * @return ResponseEntity<AuthenticationResponse> The response entity containing the access token for the registered user.
     */
    @Operation(summary = "Registers a new user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User registered successfully", content =
                    {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody final UserCreationDto userDto
    ) {
        return ResponseEntity.ok().build();
    }

    /**
     * Authenticates a user.
     *
     * @param request The AuthenticationRequest object containing the user's email and password.
     * @return ResponseEntity<AuthenticationResponse> The response entity containing the access token for the authenticated user.
     */
    @Operation(summary = "Authenticates a user.", description = "Provide credentials  to authenticate user and receive a token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return the User token - Authenticated successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "500", description = "Bad credentials")
    })
    @PostMapping("/login")

    public ResponseEntity<AuthenticationResponse> login(
            final @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    /**
     * Retrieves the details of the authenticated user.
     *
     * @return A string representing the details of the authenticated user.
     */
    @GetMapping("/me")
    @Operation(summary = "Retrieve authenticated user details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authenticated user details retrieved successfully. ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)

    })
    public ResponseEntity<?> getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Get principal (authenticated user)
        String username = authentication.getName(); // Retrieve username
        logger.debug("Logger in user Id is : {} ", username);
        return ResponseEntity.ok().build();
    }
}
