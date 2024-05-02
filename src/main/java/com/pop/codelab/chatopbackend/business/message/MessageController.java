package com.pop.codelab.chatopbackend.business.message;

import com.pop.codelab.chatopbackend.controllers.CrudController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * The MessageController class is a controller class that handles HTTP requests related to messages.
 * It extends the CrudController class and provides implementations for CRUD operations on MessageDto objects.
 *
 * @version 1.0
 * @see CrudController
 * @see MessageDto
 * @see MessageService
 */
@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET})
@Tag(name = "MessageController", description = "Provides endpoints for ${app.name} messages")
public class MessageController extends CrudController<MessageDto> {

    /**
     * The MessageController class is responsible for handling HTTP requests related to messages.
     *
     * @param messageService the service used for message-related operations
     */
    public MessageController(final MessageService messageService) {
        super(messageService);
    }

    /**
     * Saves the MessageDto object to the server.
     *
     * @param messageDto the object to be saved
     * @return a ResponseEntity containing the created MessageCreationResponse object and the HTTP status code CREATED (201)
     */
    @Override
    @PostMapping("/")
    @Operation(summary = "Create a new message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Message created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<?> save(final @RequestBody MessageDto messageDto) {
        super.save(messageDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Message sent with success\"}");
    }

    /**
     * Retrieves all messages from the server.
     *
     * @return a ResponseEntity containing the list of messages and the HTTP status code OK (200)
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping("/")
    @Operation(summary = "Retrieves all messages from the server.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of messages found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDto.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "No message found"),
            @ApiResponse(responseCode = "500", description = "Access Denied")
    })
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    /**
     * Retrieves a message by its ID.
     *
     * @param id the ID of the message to be retrieved
     * @return a ResponseEntity containing the message if found, or null and the HTTP status code NOT_FOUND (404) if not found
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    @GetMapping("/{id}")
    @Operation(summary = "Retrieves a message by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageDto.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Message not found"),
            @ApiResponse(responseCode = "500", description = "Access Denied")

    })
    public ResponseEntity<?> getById(final @PathVariable Long id) {
        return super.getById(id);
    }

    /**
     * Deletes a message by its ID.
     *
     * @param id the ID of the message to be deleted
     * @return a ResponseEntity with the status code and message indicating the result of the deletion
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a message by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Message deleted - No content"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Message not found"),
            @ApiResponse(responseCode = "500", description = "Access Denied")
    })
    public ResponseEntity<String> delete(final @PathVariable Long id) {
        return super.delete(id);
    }

    /**
     * Updates a message by its ID.
     *
     * @param id         the ID of the message to be updated
     * @param messageDto the updated message object
     * @return a ResponseEntity with the HTTP status code and message indicating the result of the update
     * @throws RuntimeException if an error occurs while updating the message
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Updates a message by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message updated"),
            @ApiResponse(responseCode = "404", description = "Message not found"),
            @ApiResponse(responseCode = "500", description = "Access Denied")
    })
    public ResponseEntity<String> update(
            final @PathVariable Long id,
            final @RequestBody MessageDto messageDto) {
        return super.update(id, messageDto);
    }

}






