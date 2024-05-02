package com.pop.codelab.chatopbackend.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The AuthenticationRequest class represents the request object used for authentication.
 * It contains the user's email and password.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    /**
     * The email which identify the user to be authenticated.
     */
    private String email;
    /**
     * The password for the user to be authenticated.
     */
    private String password;
}
