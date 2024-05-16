package com.pop.codelab.chatopbackend.business.user.controller;

import com.pop.codelab.chatopbackend.auth.dto.responses.UserDto;
import com.pop.codelab.chatopbackend.business.user.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The UserController class is a REST controller that provides endpoints for managing users in ${app.name}.
 *
 * @author Pignon Pierre-Olivier
 * @version 2.0
 */
@Tag(name = "UserController", description = "Provides endpoints for ${app.name} users - Just the GET methods work.")
@RestController
@RequestMapping("user")
public class UserController {

    /**
     * The userService variable represents an instance of the UserServiceImpl class, which provides CRUD operations for UserDto objects. It is autowired and used to find a user by
     * their ID.
     *
     * @see UserServiceImpl
     * @see UserDto
     */
    @Autowired
    private UserServiceImpl userService;

    /**
     * Retrieves a user by its ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The ResponseEntity containing the UserDto if found, or an error response if the user is not found.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Retrieves an user by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated", content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found : the user has not been retrieved")
    })
    public ResponseEntity<UserDto> findUserById(final @PathVariable Long id) {
        return ResponseEntity.ok(this.userService.findUserById(id));
    }
}
