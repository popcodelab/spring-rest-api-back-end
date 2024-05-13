package com.pop.codelab.chatopbackend.business.rental.dto.responses;

import lombok.Builder;
import lombok.Data;

/**
 * The AllRentalsDto class represents the response DTO (Data Transfer Object)
 * for a list of rental entities.
 *
 * @author Pignon Pierre-Olivier
 * @version 2.0
 * @see OneRentalDto
 */
@Data
@Builder
public class AllRentalsDto {

    /**
     * The rentals variable represents a collection of OneRentalDto objects.
     * It is of type Iterable<OneRentalDto>.
     * This variable is typically used to store a list of rental entities.
     *
     * @see OneRentalDto
     */
    private Iterable<OneRentalDto> rentals;

}
