package com.pop.codelab.chatopbackend.auth.dto.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * The UserDto class represents a data transfer object for user information.
 * It contains the user's ID, email, name, created date, and updated date.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    /**
     * The private variable {@code id} represents the unique identifier of a user.
     * This variable is of type {@code Long} and is used to uniquely identify a user in the system.
     */
    private Long id;

    /**
     * The private variable {@code email} represents the email address of a user.
     * This variable is of type {@code String} and is used to store the email address associated with a user account.
     */
    private String email;

    /**
     * The private variable {@code name} represents the name of a user.
     * This variable is of type {@code String} and is used to store the name associated with a user.
     */
    private String name;

    /**
     * The variable {@code createdAt} represents the date when the user information was created.
     * It is of type {@code LocalDate} and is annotated with {@code @JsonProperty("created_at")} to indicate its mapping in JSON serialization/deserialization.
     */
    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate createdAt;

    /**
     * The {@code updatedAt} variable represents the date and time when the user information was last updated.
     * It is of type {@code LocalDate} and is annotated with {@code @JsonFormat} to specify the pattern "yyyy/MM/dd".
     */
    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate updatedAt;
}
