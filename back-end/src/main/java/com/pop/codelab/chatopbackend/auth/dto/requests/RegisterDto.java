package com.pop.codelab.chatopbackend.auth.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * The RegisterDto class represents the data transfer object (DTO) for registering a user.
 */
@Data
@Builder
public class RegisterDto {

    /**
     * The email address of the user.
     * <p>
     * Constraints:
     * - Cannot be null.
     * - Must be a valid email address format.
     */
    @NotNull
    @Email
    private String email;

    /**
     * The password for the RegisterDto class.
     * <p>
     * Constraints:
     * - Cannot be null.
     */
    @NotNull
    private String password;

    /**
     * The name of the user for registration.
     * <p>
     * Constraints:
     * - Cannot be null.
     */
    @NotNull
    private String name;
}
