package com.pop.codelab.chatopbackend.business.rental.dto.responses;

import lombok.Builder;
import lombok.Data;

/**
 * A data transfer object class representing a rental creation result.
 */
@Data
@Builder
public class RentalCreatedDto {

    /**
     * A private variable that holds a message.
     */
    private String message;
}
