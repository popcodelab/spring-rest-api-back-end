package com.pop.codelab.chatopbackend.business.message.dto.requests;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * The MessageToSendDto class represents a data transfer object for sending messages.
 * It is used for transferring data between the client and the server.
 * The class is annotated with @Data, indicating that it provides automatic getters and setters for its fields.
 */
@Data
public class MessageToSendDto {

    /**
     * The message field represents the message to be displayed or sent.
     * It is a string that can contain any characters.
     * The field is annotated with @NotNull, indicating that it cannot be null.
     */
    @NotNull
    private String message;

    /**
     * The user_id variable represents the ID of a user.
     * It is a Long value that uniquely identifies a user.
     * The variable is annotated with @NotNull, indicating that it cannot be null.
     */
    @NotNull
    @JsonProperty("user_id")
    private Long userId;

    /**
     * The rental_id variable represents the ID of a rental.
     * It is a Long value that uniquely identifies a rental.
     * The variable is annotated with @NotNull, indicating that it cannot be null.
     */
    @NotNull
    @JsonProperty("rental_id")
    private Long rentalId;
}
