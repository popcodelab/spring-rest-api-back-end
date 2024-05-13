package com.pop.codelab.chatopbackend.common.responses;

import lombok.Builder;
import lombok.Data;

/**
 * A data transfer object class representing a message.
 */
@Data
@Builder
public class MessageDto {
    /**
     * Represents a message.
     * This variable is a private string that holds the message content.
     */
    private String message;
}
