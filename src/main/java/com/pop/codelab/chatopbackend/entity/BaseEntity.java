package com.pop.codelab.chatopbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

/**
 * BaseEntity is an abstract class that serves as a base class for entities in the system.
 * It provides common fields such as id, createdAt, and updatedAt for all entities.
 * <p></p>
 * This class is annotated with @MappedSuperclass, indicating that it is not a persistent entity itself
 * but can be used as a superclass for other entities.
 * <p></p>
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@MappedSuperclass
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

    /**
     * The `id` variable is a unique identifier for an entity in the system.
     * It is annotated with @Id, indicating that it represents the primary key of the entity.
     * The @Column annotation specifies that this field is mapped to a column in the database table,
     * with the "unique" attribute set to true, indicating that each value of this field must be unique,
     * and the "nullable" attribute set to false, indicating that this field cannot be null.
     * The @GeneratedValue annotation specifies the automatic generation of values for this field,
     * with the strategy set to GenerationType.IDENTITY, which means that the database will generate a value for this field.
     *
     * @see BaseEntity
     */
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The `createAt` variable represents the date and time when the entity was created.
     * It is annotated with @CreationTimestamp, indicating that the value of this field will be automatically generated
     * and set to the current timestamp when a new entity is created.
     * The @Column annotation specifies the mapping of this field to a column in the database table.
     * The "name" attribute specifies the name of the column as "created_at".
     * The "columnDefinition" attribute defines the SQL column definition for this field.
     * In this case, it is set to "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
     * which sets the default value for this column to the current timestamp and automatically updates it
     * whenever the entity is updated.
     * <p></p>
     * Example usage:
     * <pre>{@code
     * LocalDate createAt = entity.getCreateAt();
     * }</pre>
     */
    @CreationTimestamp
    @Column(name = "created_at",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDate createAt;

    /**
     * The `updatedAt` variable represents the date and time when the entity was last updated.
     * It is annotated with @UpdateTimestamp, indicating that the value of this field will be automatically generated
     * and set to the current timestamp whenever the entity is updated.
     * The @Column annotation specifies the mapping of this field to a column in the database table.
     * The "name" attribute specifies the name of the column as "updated_at".
     * The "columnDefinition" attribute defines the SQL column definition for this field.
     * In this case, it is set to "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
     * which sets the default value for this column to the current timestamp and automatically updates it
     * whenever the entity is updated.
     * <p></p>
     * Example usage:
     * <?java
     * LocalDate updatedAt = entity.getUpdatedAt();
     * ?>
     */
    @UpdateTimestamp
    @Column(name = "updated_at",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDate updatedAt;
}
