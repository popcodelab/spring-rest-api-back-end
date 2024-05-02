package com.pop.codelab.chatopbackend.business.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Data Transfer Object
 * Represents the created message.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MessageCreationResponse {

    /**
     * The message variable represents a string message.
     * <p>
     * MessageCreationResponse is a Data Transfer Object class that represents the created message.
     * It contains a private String field named "message".</p>
     */
    @JsonProperty("message")
    private String message;
}
