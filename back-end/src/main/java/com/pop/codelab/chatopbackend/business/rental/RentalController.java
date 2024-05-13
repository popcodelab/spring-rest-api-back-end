package com.pop.codelab.chatopbackend.business.rental;

import com.pop.codelab.chatopbackend.business.rental.dto.requests.RentalCreationDto;
import com.pop.codelab.chatopbackend.business.rental.dto.requests.RentalUpdateDto;
import com.pop.codelab.chatopbackend.business.rental.dto.responses.AllRentalsDto;
import com.pop.codelab.chatopbackend.business.rental.dto.responses.OneRentalDto;
import com.pop.codelab.chatopbackend.business.rental.service.RentalServiceImpl;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Retrieves all rentals from the repository.
 *
 * @author Pignon Pierre-Olivier
 * @version 2.0
 * @return a ResponseEntity containing AllRentalsDto object representing all rentals and the appropriate HTTP status code
 * @throws ResourceNotFoundException if no rentals are found
 */
@Tag(name = "RentalController", description = "Provides endpoints for ${app.name} rentals")
@RestController
@RequestMapping("rentals")
public class RentalController {

    /**
     * Represents the rental service used in the RentalController class.
     * <p>
     * The rental service is responsible for handling the retrieval, creation, and update of rentals.
     * <p>
     * Usage example:
     * <p>
     * rentalService.getAllRentals();
     * rentalService.getRentalById(1L);
     * rentalService.createRental(rentalDto, authentication);
     * rentalService.updateRental(1L, rentalDto, authentication);
     */
    @Autowired
    private RentalServiceImpl rentalService;


    /**
     * Retrieves all rentals from the repository.
     *
     * @return a ResponseEntity containing AllRentalsDto object representing all rentals and the appropriate HTTP status code
     * @throws ResourceNotFoundException if no rentals are found
     */
    @GetMapping(value = "")
    @Operation(summary = "Get all rentals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK : List of rentals found", content = @Content(schema = @Schema(implementation = AllRentalsDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized : the user must authenticate itself to get" +
                    " the requested response"),
            @ApiResponse(responseCode = "404", description = "Not Found : No rentals found")
    })
    public ResponseEntity<AllRentalsDto> getAllRentails() {
        return ResponseEntity.ok(this.rentalService.getAllRentals());

    }

    /**
     * Retrieves a rental by its ID.
     *
     * @param id the ID of the rental to be retrieved
     * @return a ResponseEntity containing the rental with the specified ID and the appropriate HTTP status code
     */
    @Operation(summary = "Get a rental by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK : Rental found", content = @Content(schema = @Schema(implementation = OneRentalDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized : the user must authenticate itself to get" +
                    " the requested response"),
            @ApiResponse(responseCode = "404", description = "Not Found : the rental has not been retrieved")

    })
    @GetMapping("/{id}")
    public ResponseEntity<OneRentalDto> getRentalById(final @PathVariable Long id) {
        return ResponseEntity.ok(this.rentalService.getRentalById(id));
    }

    /**
     * Creates a new rental with the given rentalDto and authentication.
     *
     * @param rentalDto      the RentalCreationDto object containing the rental details
     * @param authentication the Authentication object representing the logged-in user
     *                       we need it to set the rental owner
     * @return a ResponseEntity<MessageDto> containing the status code and message
     */
    @PostMapping(path = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Create a new rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created : Rental created", content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized : the user must authenticate itself to get" +
                    " the requested response")
    })
    public ResponseEntity<MessageDto> createRental(final @Valid @ModelAttribute RentalCreationDto rentalDto, final Authentication authentication) {
        return new ResponseEntity<>(this.rentalService.createRental(rentalDto, authentication), HttpStatus.CREATED);
    }

    /**
     * Updates a rental with the specified ID.
     *
     * @param id             the ID of the rental to be updated
     * @param rentalDto      the updated rental object
     * @param authentication the authentication object for user authorization to check
     *                       if the logged user owns the rental.
     * @return a ResponseEntity containing a MessageDto with a success message and the appropriate HTTP status code
     */
    @Operation(summary = "Update a rental by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental updated", content = @Content(schema = @Schema(implementation = MessageDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized : the user must authenticate itself to get" +
                    " the requested response"),
            @ApiResponse(responseCode = "404", description = "Not Found : the rental has not been retrieved")

    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<MessageDto> updateRental(
            final @PathVariable Long id,
            final @Valid @ModelAttribute RentalUpdateDto rentalDto, final Authentication authentication) {
        return ResponseEntity.ok(this.rentalService.updateRental(id, rentalDto, authentication));

    }

}
