package com.pop.codelab.chatopbackend.business.rental.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

/**
 * RentalCreationDto represents the data transfer object for creating a rental.
 *
 * @author Pignon Pierre-Olivier
 * @version 2
 */
@Data

public class RentalCreationDto {

    /**
     * Represents the name of a rental.
     */
    @NotNull
    private String name;

    /**
     * The surface of a rental property.
     */
    @NotNull
    private BigDecimal surface;

    /**
     * Represents the price of a rental.
     */
    @NotNull
    private BigDecimal price;

    /**
     * Represents a picture file that can be uploaded for a rental.
     */
    private MultipartFile picture;

    /**
     * Represents the description of a rental.
     */
    @NotNull
    private String description;
}
