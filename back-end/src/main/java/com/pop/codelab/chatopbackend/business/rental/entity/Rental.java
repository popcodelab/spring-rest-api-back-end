package com.pop.codelab.chatopbackend.business.rental.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pop.codelab.chatopbackend.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

/**
 * The Rental class represents a rental in the system.
 * It extends the BaseEntity class and is annotated with @Entity, indicating that it is a JPA entity.
 * The class represents a table called "rentals" in the database.
 *
 * @author Pignon Pierre-Olivier
 * @version 2.0
 * @see BaseEntity
 */
@Data
@Entity // JPA entity
@Table(name = "rentals")
public class Rental extends BaseEntity {
    /**
     * The `name` variable represents the name of a rental in the system.
     * It is a field of type String, and it is marked as private, indicating that it can only be accessed within the class.
     * This variable is used to store the name of the rental.
     */
    @Column(length = 248, nullable = false)
    private String name;

    /**
     * The `surface` variable represents the surface area of a rental in the system.
     * It is a field of type BigDecimal, and it is marked as private, indicating that it can only be accessed within the class.
     * This variable is used to store the surface area of the rental.
     *
     * @see Rental
     */
    @Column(nullable = false)
    private BigDecimal surface;

    /**
     * The `price` variable represents the price of a rental in the system.
     * It is a field of type BigDecimal, and it is marked as private and nullable,
     * indicating that it can only be accessed within the class and cannot be null in the database.
     * This variable is used to store the price of the rental.
     *
     * @see Rental
     */
    @Column(nullable = false)
    private BigDecimal price;

    /**
     * The `picture` variable represents the file name of a rental picture in the system.
     * It is a field of type String, and it is marked as private, indicating that it can only be accessed within the class.
     * This variable is used to store the name of the rental picture.
     */
    private String picture;

    /**
     * The description of a rental in the system.
     * It represents the text description of the rental.
     * The "length" attribute is set to 2000, indicating the maximum length of the description is 2000 characters.
     * The "nullable" attribute is set to false, indicating that the description cannot be null in the database.
     *
     * @see Rental
     */
    @Column(length = 2000, nullable = false)
    private String description;

    /**
     * The Rental class represents a rental in the system.
     * It extends the BaseEntity class and is annotated with @Entity, indicating that it is a JPA entity.
     * The class represents a table called "rentals" in the database.
     *
     * @see BaseEntity
     */
    @Column(nullable = false, name = "owner_id")
    private Long ownerId;
}
