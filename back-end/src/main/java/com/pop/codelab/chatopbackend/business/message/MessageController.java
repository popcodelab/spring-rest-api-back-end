package com.pop.codelab.chatopbackend.business.message;

import com.pop.codelab.chatopbackend.business.message.dto.requests.MessageToSendDto;
import com.pop.codelab.chatopbackend.business.message.service.MessageService;
import com.pop.codelab.chatopbackend.common.responses.MessageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides endpoints for ${app.name} messages.
 *
 * @author Pignon Pierre-Olivier
 * @version 2.0
 */
@RestController
@RequestMapping("messages")
@Tag(name = "MessageController", description = "Provides endpoints for ${app.name} messages")
public class MessageController {

    /**
     * Variable representing the MessageService component.
     * The MessageService is responsible for creating messages using the provided MessageToSendDto. It provides a contract
     * for creating messages through the create() method.
     *
     * @see MessageService
     */
    @Autowired
    private MessageService messageService;

    /**
     * Saves a message by calling the create method in MessageService with the provided MessageToSendDto.
     *
     * @param messageDto The MessageToSendDto object containing the message, user ID, and rental ID.
     * @return The created MessageDto object.
     */
    @PostMapping("")
    @Operation(summary = "Creates a message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created : Message sent", content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request : Message, user or rental must be provided"),
            @ApiResponse(responseCode = "401", description = "Unauthorized : Invalid user"),

    })
    public ResponseEntity<MessageDto> sendMessage(final @Valid @RequestBody MessageToSendDto messageDto) {
        return new ResponseEntity<>(this.messageService.create(messageDto), HttpStatus.CREATED);
    }

}






