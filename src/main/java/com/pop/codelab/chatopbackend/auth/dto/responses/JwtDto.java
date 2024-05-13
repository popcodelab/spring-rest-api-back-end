package com.pop.codelab.chatopbackend.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * A class representing the response object for authentication.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    /**
     * The access token used for authentication.
     * This variable is a string representation of the access token.
     * It is annotated with @JsonProperty to specify the JSON property name "token".
     *
     * Note: This variable is a private member of the AuthenticationResponse class.
     *
     * @see AuthenticationResponse
     */
    @JsonProperty("token")
    private String accessToken;
}
