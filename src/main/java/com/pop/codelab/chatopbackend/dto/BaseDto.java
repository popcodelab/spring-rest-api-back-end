package com.pop.codelab.chatopbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * The BaseDto class is an abstract class that serves as the base for all DTO (Data Transfer Object) classes.
 * <p></p>
 * It provides common properties such as id, createAt, and updatedAt that can be inherited by the child DTO classes.
 * <p></p>
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDto {

    /**
     * The id variable stores the unique identifier for this object.
     * It is a Long type and is used to uniquely identify an instance of the class.
     */
    private Long id;

    /**
     * The `createAt` variable represents the date and time when an object was created.
     * It is of type `LocalDate` and is annotated with `@JsonProperty("create_at")` to map it to the corresponding
     * field in JSON.
     * This variable is part of the `BaseDto` class, which is an abstract class serving as the base
     * for all DTO (Data Transfer Object) classes.
     * The `createAt` variable, along with other common properties such as `id` and `updatedAt`, can be inherited by
     * child DTO classes.
     *
     * @see BaseDto
     */
    @JsonProperty("create_at")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate createAt;

    /**
     * The `updatedAt` variable represents the date and time when an object was last updated.
     * It is of type `LocalDate` and is annotated with `@JsonProperty("update_at")` to map it to the corresponding
     * field in JSON.
     * This variable is part of the `BaseDto` class, which is an abstract class serving as the base
     * for all DTO (Data Transfer Object) classes.
     * The `updatedAt` variable, along with other common properties such as `id` and `createAt`, can be inherited by
     * child DTO classes.
     *
     * @see BaseDto
     */
    @JsonProperty("update_at")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate updatedAt;

}

