package com.pop.codelab.chatopbackend.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The AuthenticationResponse class represents the response object returned by an authentication mechanism.
 * It contains the access token that is used to authenticate.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  @JsonProperty("token")
  private String accessToken;
}
