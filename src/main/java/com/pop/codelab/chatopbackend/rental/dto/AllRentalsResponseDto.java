package com.pop.codelab.chatopbackend.rental.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * The AllRentalsResponseDto class represents a response containing a list of RentalDto objects.
 * <p></p>
 * It is used to return a ResponseEntity containing the list of rentals and the HTTP status code OK (200).
 * This class is typically used in the getAll() method of the RentalController class.
 * <p></p>
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 * @see RentalDto
 * <p></p>
 */
@Data
@Builder
public class AllRentalsResponseDto {

    /**
     * List of rentals
     * <p></p>
     * Type is List<RentalDto> in the class.
     * <p></p>
     * The RentalDto class inherits from the BaseDto class, which provides common properties
     * such as id, createAt, and updatedAt. The BaseDto class is an abstract class serving as
     * the base for all DTO (Data Transfer Object) classes.
     * <p></p>
     * The user property in the RentalDto class represents the user associated with the rental.
     * It is an instance of the UserDto class, which also inherits from the BaseDto class
     * <p></p>
     * The rentals variable is typically used in the getAll() method of the RentalController class
     * to retrieve and return a list of rentals.
     * <p></p>
     *
     * @see RentalDto
     * @see BaseDto
     * @see UserDto
     * @see RentalController#getAll()
     */
    private List<RentalDto> rentals;

}
