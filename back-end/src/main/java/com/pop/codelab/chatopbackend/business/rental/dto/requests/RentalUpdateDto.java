package com.pop.codelab.chatopbackend.business.rental.dto.requests;

import lombok.Data;

import java.math.BigDecimal;

/**
 * The RentalUpdateDto class represents the data transfer object for updating a rental entity.
 */
@Data
public class RentalUpdateDto {

    /**
     * The name of a rental entity.
     */
    private String name;

    /**
     * The surface variable represents the surface area of a rental entity. It is of type BigDecimal, which allows for precise
     * calculations and storage of decimal values. The surface area is used to determine the size or dimensions of the rental
     * entity, such as the square footage or the number of rooms.
     */
    private BigDecimal surface;

    /**
     * The price of a rental entity.
     * This variable represents the price of a rental entity. It is of type BigDecimal, which allows for precise
     * calculations and storage of decimal values. The price is used to determine the cost of renting the entity.
     */
    private BigDecimal price;

    /**
     * The description variable is a private String that represents the description of a rental entity.
     * It provides additional information or details about the rental entity.
     * This variable is generally used to give a brief overview or summary of the rental entity, such as its features, location, or amenities.
     */
    private String description;
}
