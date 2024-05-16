package com.pop.codelab.chatopbackend.auth.controller;

import com.pop.codelab.chatopbackend.auth.dto.requests.AuthenticateDto;
import com.pop.codelab.chatopbackend.auth.dto.requests.RegisterDto;
import com.pop.codelab.chatopbackend.auth.dto.responses.JwtDto;
import com.pop.codelab.chatopbackend.auth.dto.responses.UserDto;
import com.pop.codelab.chatopbackend.auth.service.AuthenticationService;
import com.pop.codelab.chatopbackend.auth.service.AuthenticationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * The AuthenticationController class handles the authentication-related API endpoints.
 */
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    /**
     * The AuthenticationServiceImpl class provides methods for user authentication and registration.
     */
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Registers a new user.
     *
     * @param registerDto The RegisterDto object representing the user information to be registered.
     * @return The ResponseEntity containing the JwtDto object representing the generated JWT token.
     */
    @Operation(summary = "Registers a new user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created : User registered successfully", content = @Content(schema = @Schema(implementation = JwtDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @PostMapping("/register")
    public ResponseEntity<JwtDto> register(@Valid @RequestBody final RegisterDto registerDto) {
        return new ResponseEntity<>(this.authenticationService.register(registerDto), HttpStatus.CREATED);
    }

    /**
     * Authenticates a user.
     *
     * @param authDto The AuthenticateDto object representing the user's authentication details.
     * @return ResponseEntity<JwtDto> The response entity containing the access token for the authenticated user.
     */
    @Operation(summary = "Authenticates a user.", description = "Provide credentials  to authenticate user and receive a token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return the User token - Authenticated successfully", content = @Content(schema = @Schema(implementation = JwtDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @PostMapping(path = "/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody final AuthenticateDto authDto) {
        return ResponseEntity.ok(this.authenticationService.authenticate(authDto));
    }

    /**
     * Retrieves user information for the authenticated user.
     *
     * @param authentication The Authentication object representing the authenticated user.
     * @return The user information as a ResponseEntity containing a UserDto object.
     */
    @GetMapping("/me")
    @Operation(summary = "Retrieve authenticated user details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authenticated user details retrieved successfully.", content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized : Invalid user.")})
    public ResponseEntity<UserDto> getUserInformations(final Authentication authentication) {
        return ResponseEntity.ok(this.authenticationService.getUserInformations(authentication));
    }
}
