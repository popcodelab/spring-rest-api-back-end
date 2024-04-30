package com.pop.codelab.chatopbackend.business.rental;

import com.pop.codelab.chatopbackend.business.user.User;
import com.pop.codelab.chatopbackend.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Rental is a class representing a rental in the system.
 *
 * <p>It is annotated with @Data, @Builder, @NoArgsConstructor, and @AllArgsConstructor,
 * which are Lombok annotations that generate basic boilerplate code such as getters, setters,
 * constructors, and toString methods.</p>
 *
 * <p>It is also annotated with @Entity, which marks it as a JPA entity, and @Table, which specifies the name
 * of the database table that this entity is mapped to.</p>
 *
 * <p>Rental extends BaseEntity, which is an abstract class that serves as a base class for entities in the system.
 * It provides common fields such as id, createdAt, and updatedAt for all entities. BaseEntity is also annotated with
 *
 * <p>Rental has the following fields:</p>
 * <ul>
 *     <li>name: A String representing the name of the rental.</li>
 *     <li>surface: A BigDecimal representing the surface area of the rental. It is marked as @Column(nullable = false),
 *     indicating that this field cannot be null in the database.</li>
 *     <li>price: A BigDecimal representing the price of the rental. It is marked as @Column(nullable = false),
 *     indicating that this field cannot be null in the database.</li>
 *     <li>picture: A String representing the file name of the rental picture.</li>
 *     <li>description: A String representing the description of the rental.</li>
 *     <li>user: A User object representing the owner of the rental. It is marked as @ManyToOne, indicating that
 *     many rentals can be owned by one user.</li>
 * </ul>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity // JPA entity
@Table(name = "rentals")
public class Rental extends BaseEntity {
    /**
     * The `name` variable represents the name of a rental in the system.
     * It is a field of type String, and it is marked as private, indicating that it can only be accessed within the class.
     * This variable is used to store the name of the rental.
     * <p></p>
     */
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
     * It is a field of type BigDecimal, and it is marked as private, indicating that it can only be accessed within the class.
     * This variable is used to store the price of the rental.
     * The @Column annotation specifies the mapping of this field to a column in the database table.
     * The "nullable" attribute is set to false, indicating that this field cannot be null in the database.
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
     * Represents the description of a Rental.
     */
    private String description;

    /**
     * The user variable represents the owner of a rental.
     * <p></p>
     * This variable is a foreign key field of the Rental class, which represents a rental entity in the system.
     * It is of type User and is used to associate a specific user as the owner of the rental.
     * The user field is mapped to the "owner_id" column in the "rentals" table using the @ManyToOne and @JoinColumn annotations.
     *
     * @see Rental
     */
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;
}
