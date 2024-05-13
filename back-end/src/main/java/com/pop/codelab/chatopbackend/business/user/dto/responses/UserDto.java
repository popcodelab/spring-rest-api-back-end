package com.pop.codelab.chatopbackend.business.user.dto.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * The UserDto class represents a data transfer object for a user.
 * It contains information about the user including the unique identifier, email, name, creation date, and update date.
 * Use this class to transfer user data between different layers of the application, such as between the presentation layer and the data access layer.
 */
@Data
public class UserDto {

    /**
     * The id variable stores the unique identifier for this object.
     * It is a Long type and is used to uniquely identify an instance of the class.
     */
    private Long id;

    /**
     * The email variable stores the email address of a user.
     * It is a String type and is used to identify and communicate with the user.
     */
    private String email;

    /**
     * The name variable stores the name of a user.
     * It is a String type and is used to identify the user by their name.
     */
    private String name;

    /**
     * The `createdAt` variable represents the creation date of a user in the UserDto class.
     * It is annotated with `@JsonProperty("created_at")` to ensure proper serialization and deserialization when interacting with JSON data.
     */
    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate createdAt;

    /**
     * The `updatedAt` variable represents the update date of a user in the UserDto class.
     * It is annotated with `@JsonProperty("updated_at")` to ensure proper serialization and deserialization when interacting with JSON data.
     */
    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate updatedAt;
}
