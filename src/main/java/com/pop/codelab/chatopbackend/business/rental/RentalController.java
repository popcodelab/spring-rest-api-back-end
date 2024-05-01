package com.pop.codelab.chatopbackend.business.rental;

import com.pop.codelab.chatopbackend.business.rental.dto.AllRentalsResponseDto;
import com.pop.codelab.chatopbackend.business.rental.dto.OneRentalResponseDto;
import com.pop.codelab.chatopbackend.business.rental.dto.RentalDto;
import com.pop.codelab.chatopbackend.controllers.CrudController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Tag(name = "RentalController", description = "Provides endpoints for ${app.name} rentals")
@RestController
@RequestMapping("/api/rentals")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class RentalController extends CrudController<RentalDto> {

    /**
     * Logger for the RentalController class.
     * It is used to log messages and events related to the RentalController class.
     * The logger is initialized using the LoggerFactory.getLogger method and the class name RentalController.class.
     * The logger should be private, static, and final to ensure thread-safety and avoid any unintended modifications.
     */
    private static final Logger logger = LoggerFactory.getLogger(RentalController.class);

    /**
     * ModelMapper, here, is used to map Dto and Entity
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * The RentalController class handles the HTTP requests related to rental entities.
     *
     * @param rentalService the service used for performing CRUD operations on rental entities
     */
    public RentalController(final RentalService rentalService) {
        super(rentalService);
    }

    /**
     * Retrieves all rentals from the server.
     *
     * @return a ResponseEntity containing the list of rentals and the HTTP status code OK (200)
     */
    @Override
    @GetMapping(value = "/")
    @Operation(summary = "Get all rentals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of rentals found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AllRentalsResponseDto.class))}
            ),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "No rentals found"),
            @ApiResponse(responseCode = "500", description = "Access Denied")
    })
    public ResponseEntity<AllRentalsResponseDto> getAll() {
        logger.info("Getting all rentals");
        AllRentalsResponseDto rentalsResponseDto = AllRentalsResponseDto.builder().build();
        Object responseBody = super.getAll().getBody();
        if (responseBody instanceof List) {
            rentalsResponseDto.setRentals((List<OneRentalResponseDto>) responseBody);
        } else {
            logger.warn("Response entity is not of type List<RentalDto>");
        }
        return new ResponseEntity<>(rentalsResponseDto, HttpStatus.OK);
    }

    /**
     * Retrieves a rental by its ID.
     *
     * @param id the ID of the rental to be retrieved
     * @return a ResponseEntity containing the retrieved rental as a OneRentalResponseDto and the HTTP status code OK (200)
     */
    @Operation(summary = "Get a rental by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OneRentalResponseDto.class))}
            ),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "No rentals found"),
            @ApiResponse(responseCode = "500", description = "Access Denied")
    })
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<OneRentalResponseDto> getById(final @PathVariable Long id) {
        RentalDto rentalDto = (RentalDto) super.getById(id).getBody();
        OneRentalResponseDto responseDto = modelMapper.map(rentalDto, OneRentalResponseDto.class);
        if (rentalDto != null) {
            if (rentalDto.getPicture() != null) {
                responseDto.setPicture(getImageToServeUrl(rentalDto));
            }
            if (rentalDto.getUser() != null) {
                responseDto.setOwner_id(rentalDto.getUser().getId());
            }
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    private String getImageToServeUrl(final RentalDto rentalDto){
        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/images/")
                .path(rentalDto.getPicture().getName())
                .toUriString();
        logger.debug("Image will be served at  = {}", imageUrl);
        return imageUrl;
    }


    /**
     * Saves the rental object to the server.
     * Picture uploading feature
     *
     * @param rentalDto the object to be saved
     * @return a ResponseEntity containing the saved rentalDto and the HTTP status code CREATED (201)
     * @throws RuntimeException if the rentalDto fails to save
     */
    @Override
    @PostMapping(path = "/", consumes = {"multipart/form-data"})
    @Operation(summary = "Create a new rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rental created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<String> save(final @ModelAttribute RentalDto rentalDto) {
        logger.info("Saving rental...");
        RentalDto savedRentalDto = (RentalDto) super.save(rentalDto).getBody();
        if (savedRentalDto == null) {
            logger.error("The Rental DTO has not been saved !");
            throw new RuntimeException("The Rental DTO has not been saved !");
        }
        logger.debug("Saved : {}", savedRentalDto);
        return ResponseEntity.status  (HttpStatus.CREATED).body("{\"message\": \"Rental created !\"}");
    }

    /**
     * Updates a rental object with the specified ID.
     *
     * @param id        the ID of the rental object to be updated
     * @param rentalDto the updated rental object()
     * @return a ResponseEntity with the HTTP status code and a message indicating the result of the update
     * @throws RuntimeException if an error occurs while updating the rental object
     */
    @Operation(summary = "Update a rental by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
    })
    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(
            final @PathVariable Long id,
            final @ModelAttribute RentalDto rentalDto) {
        super.update(id, rentalDto);
        return ResponseEntity.ok().body("{\"message\": \"Rental updated !\"}");

    }

    /**
     * Deletes a rental object with the specified ID.
     *
     * @param id the ID of the rental object to be deleted
     * @return a ResponseEntity with the status code and message indicating the result of the deletion
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a rental by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Rental deleted"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "500", description = "Access Denied")
    })
    public ResponseEntity<String> delete(final @PathVariable Long id) {
        return super.delete(id);
    }

}
