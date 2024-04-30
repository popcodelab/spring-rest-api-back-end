package com.pop.codelab.chatopbackend.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * A class representing the response object for authentication.
 * <p></p>
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
     * Represents an access token for authentication.
     */
    @JsonProperty("token")
    private String accessToken;
}
