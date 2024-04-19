package com.pop.codelab.chatopbackend.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

/**
 * JWTService generates JWT tokens using a JwtEncoder.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Service
public class JWTService {

    private JwtEncoder jwtEncoder;

    /**
     * Constructs a new JWTService with the specified JwtEncoder.
     *
     * @param jwtEncoder the JwtEncoder to be used for encoding JWT tokens
     */
    public JWTService(JwtEncoder jwtEncoder) {

        this.jwtEncoder = jwtEncoder;
    }

    /**
     * Generates a JWT token using the provided authentication information.
     *
     * @param authentication the authentication object representing the user
     * @return the generated JWT token as a string
     *
     * @since 1.0
     */
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(authentication.getName())
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

}
