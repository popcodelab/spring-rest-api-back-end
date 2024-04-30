package com.pop.codelab.chatopbackend.business.user;

import com.pop.codelab.chatopbackend.business.user.dto.UserDto;
import com.pop.codelab.chatopbackend.controllers.CrudController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * The UserController class is a REST controller that provides endpoints for managing users in ${app.name}.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Tag(name = "UserController", description = "Provides endpoints for ${app.name} users - Just the GET methods work.")
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET})
public class UserController extends CrudController<UserDto> {

    /**
     * The UserController class is a controller that handles HTTP requests related to User entities.
     * It extends the CrudController class and provides CRUD operations for UserDto objects.
     *
     * @param userService The UserService object used for accessing and manipulating User data.
     */
    public UserController(final UserService userService) {
        super(userService);
    }

    /**
     * Saves the given UserDto object to the server.
     *
     * @param userDto the object to be saved
     * @return a ResponseEntity object containing the saved object and the HTTP status code CREATED (201)
     */
    @Override
    @PostMapping("/")
    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Access Denied")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> save(final @RequestBody UserDto userDto) {
        return super.save(userDto);
    }

    /**
     * Retrieves all users from the server.
     *
     * @return a ResponseEntity containing the list of users and the HTTP status code OK (200)
     */
    @Override
    @GetMapping("/")
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "No users found"),
            @ApiResponse(responseCode = "500", description = "Access Denied")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    /**
     * Retrieves a user object by its ID.
     *
     * @param id the ID of the user object to be retrieved
     * @return a ResponseEntity containing the user object if found, or null and the HTTP status code NOT_FOUND (404) if not found
     */
    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Access Denied")
    })
    public ResponseEntity<?> getById(final @PathVariable Long id) {
        return super.getById(id);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to be deleted
     * @return a ResponseEntity with the status code indicating the result of the deletion
     */
    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Access Denied")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delete(final @PathVariable Long id) {
        return super.delete(id);
    }

    /**
     * Updates a user with the specified ID.
     * <p>
     * This method updates a user object in the server with the given ID.
     * The updated user object is provided as a UserDto object in the request body.
     * </p>
     *
     * @param id      the ID of the user object to be updated
     * @param userDto the updated user object
     * @return a ResponseEntity with the HTTP status code and message indicating the result of the update
     * @throws RuntimeException if an error occurs while updating the user object
     */
    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Update a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Access Denied")
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public ResponseEntity<String> update(
            final @PathVariable Long id,
            final @RequestBody UserDto userDto) {
        return super.update(id, userDto);
    }
}
