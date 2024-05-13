package com.pop.codelab.chatopbackend.business.rental.dto.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The OneRentalDto class represents a rental entity in the system.
 *
 * @author Pignon Pierre-Olivier
 * @version 2.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OneRentalDto {

    /**
     * The id variable represents the identifier of a rental entity.
     */

    private Long id;
    /**
     * The name variable can be used to store the name of the rental entity.
     */
    private String name;

    /**
     * The surface variable represents the surface of a rental entity.
     * It is of type BigDecimal and can be used to store the surface value in decimal format with high precision.
     * The surface value indicates the total area or size of the rental entity.
     */
    private BigDecimal surface;
    /**
     * The price variable represents the price of a rental entity.
     * It is of type BigDecimal and can be used to store the price value in decimal format with high precision.
     * The price value indicates the cost or amount to be paid for the rental entity.
     */
    private BigDecimal price;
    /**
     * The picture variable represents the file path of the rental picture.
     * It is of type String and can be used to store the picture URL or file path.
     */
    private String picture;
    /**
     * The description value provides additional information or details about the rental entity.
     */
    private String description;
    /**
     * The owner_id variable represents the identifier of the rental owner.
     * The owner_id value is typically used to establish a relationship between a rental entity and its owner.
     */
    @JsonProperty("owner_id")
    private Long ownerId;

    /**
     * The createdAt variable represents the date and time when the rental entity was created.
     */
    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate createdAt;

    /**
     * The updatedAt variable represents the date and time when the rental entity was last updated.
     */
    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate updatedAt;

}
