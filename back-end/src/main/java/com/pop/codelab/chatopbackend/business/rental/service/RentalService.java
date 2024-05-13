package com.pop.codelab.chatopbackend.business.rental.service;

import com.pop.codelab.chatopbackend.business.rental.dto.requests.RentalCreationDto;
import com.pop.codelab.chatopbackend.business.rental.dto.requests.RentalUpdateDto;
import com.pop.codelab.chatopbackend.business.rental.dto.responses.AllRentalsDto;
import com.pop.codelab.chatopbackend.business.rental.dto.responses.OneRentalDto;
import com.pop.codelab.chatopbackend.common.responses.MessageDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * The RentalService interface provides methods for managing rental entities.
 *
 * @author Pignon Pierre-Olivier
 * @version 2.0
 */
public interface RentalService {

    /**
     * Retrieves a list of all rental entities.
     *
     * @return An instance of AllRentalsDto containing the list of rental entities.
     * @see AllRentalsDto
     * @see OneRentalDto
     */
    AllRentalsDto getAllRentals();

    /**
     * Retrieves a specific rental entity based on the provided ID.
     *
     * @param id The identifier of the rental entity to retrieve.
     * @return An instance of OneRentalDto representing the rental entity with the provided ID.
     */
    OneRentalDto getRentalById(Long id);

    /**
     * Creates a new rental entity based on the provided rental data and authentication.
     *
     * @param rentalDto      The RentalCreationDto object containing the rental data.
     * @param authentication The authentication object representing the user's credentials.
     * @return A MessageDto object indicating the result of the create operation.
     */
    MessageDto createRental(RentalCreationDto rentalDto, Authentication authentication);

    /**
     * Updates the rental entity with the provided ID based on the rental data and authentication.
     *
     * @param id             the identifier of the rental entity to update
     * @param rentalDto      the RentalUpdateDto object containing the rental data
     * @param authentication the authentication object representing the user's credentials
     * @return a MessageDto object indicating the result of the update operation
     */
    MessageDto updateRental(
            Long id,
            @ModelAttribute RentalUpdateDto rentalDto, Authentication authentication);

}
