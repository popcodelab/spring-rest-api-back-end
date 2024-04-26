package com.pop.codelab.chatopbackend.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Data Transfer Object
 * Represents the created message.
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MessageCreationResponse {

    @JsonProperty("message")
    private String message;
}
