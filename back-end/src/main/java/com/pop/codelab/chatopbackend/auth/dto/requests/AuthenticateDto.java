package com.pop.codelab.chatopbackend.auth.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * The AuthenticateDto class represents the request object used for authentication.
 * It contains the user's email and password.
 *
 * @author Pignon Pierre-Olivier
 * @version 2.0
 */
@Data
@Builder
public class AuthenticateDto {

    /**
     * The email which identify the user to be authenticated.
     */
    @Email
    @NotNull
    private String email;
    /**
     * The password for the user to be authenticated.
     */
    @NotNull
    private String password;
}
