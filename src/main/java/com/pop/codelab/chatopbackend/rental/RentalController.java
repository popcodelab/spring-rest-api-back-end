package com.pop.codelab.chatopbackend.rental;

import com.pop.codelab.chatopbackend.controllers.CrudController;
import com.pop.codelab.chatopbackend.rental.dto.AllRentalsResponseDto;
import com.pop.codelab.chatopbackend.rental.dto.OneRentalResponseDto;
import com.pop.codelab.chatopbackend.rental.dto.RentalDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/rentals")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class RentalController extends CrudController<RentalDto> {

    private static final Logger logger = LoggerFactory.getLogger(RentalController.class);

    @Autowired
    private ModelMapper modelMapper;

    /**
     * The RentalController class handles the HTTP requests related to rental entities.
     *
     * @param rentalService the service used for performing CRUD operations on rental entities
     */
    public RentalController(RentalService rentalService) {
        super(rentalService);
    }

    @Override
    @GetMapping(value = "/")
    public ResponseEntity<AllRentalsResponseDto> getAll() {
        logger.info("Getting all rentals");
        AllRentalsResponseDto rentalsResponseDto = AllRentalsResponseDto.builder().build();
        Object responseBody = super.getAll().getBody();
        if (responseBody instanceof List) {
            rentalsResponseDto.setRentals((List<RentalDto>) responseBody);
        } else {
            logger.error("Response body is not of type List<RentalDto>");
        }
        return new ResponseEntity<>(rentalsResponseDto, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<OneRentalResponseDto> getById(@PathVariable Long id) {
        RentalDto rentalDto = (RentalDto) super.getById(id).getBody();
        OneRentalResponseDto responseDto = modelMapper.map(rentalDto, OneRentalResponseDto.class);
        if (rentalDto != null) {
            if (rentalDto.getPicture() != null) {
                String fileName = rentalDto.getPicture().getName();
                responseDto.setPicture(fileName);
            }
            if (rentalDto.getUser() != null) {
                responseDto.setOwner_id(rentalDto.getUser().getId());
            }
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
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
    public ResponseEntity<RentalDto> save(@ModelAttribute RentalDto rentalDto) {
        logger.info("Saving rental...");
        RentalDto savedRentalDto = (RentalDto) super.save(rentalDto).getBody();
        if (savedRentalDto == null) {
            logger.error("The Rental DTO has not been saved !");
            throw new RuntimeException("The Rental DTO has not been saved !");
        }
        logger.debug("Saved : {}", savedRentalDto);
        return new ResponseEntity<>(savedRentalDto, HttpStatus.CREATED);
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @ModelAttribute RentalDto rentalDto) {
        super.update(id, rentalDto);
        return ResponseEntity.ok().body("{\"message\": \"Rental updated !\"}");

    }

}
