package com.pop.codelab.chatopbackend.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * The JwtService class is responsible for generating and validating JSON Web Tokens (JWT).
 * It provides methods for extracting claims from a token, generating a token based on user details,
 * and checking if a token is valid and not expired.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Service
public class JwtService {

    /**
     * The secret key used for generating and validating JSON Web Tokens (JWT).
     * The value of this key is retrieved from the application configuration.
     *
     * @see JwtService
     */
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    /**
     * The jwtExpiration variable represents the expiration time (in milliseconds) of JSON Web Tokens (JWT)
     * used for authentication and authorization.
     * <p>
     * The value of this variable is retrieved from the application configuration.
     * </p>
     * <p>
     * This variable is part of the JwtService class, which is responsible for generating and validating JWTs.
     * </p>
     *
     * @see JwtService
     * @see JwtService#generateToken(Map, UserDetails)
     * @see JwtService#buildToken(Map, UserDetails, long)
     * @see JwtService#getSignInKey()
     * @see JwtService#extractUsername(String)
     * @see JwtService#isTokenExpired(String)
     * @see JwtService#extractExpiration(String)
     */
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    /**
     * Extracts the username from the provided token.
     *
     * @param token the token containing the username
     * @return the extracted username
     */
    public String extractUsername(final String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a claim from the given token using the provided claims resolver.
     *
     * @param token          The token from which to extract the claim.
     * @param claimsResolver The resolver function that maps the extracted claims to the desired type.
     * @param <T>            The type of the claim to extract.
     * @return The extracted claim of type T.
     */
    public <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generates a token for a given user.
     *
     * @param userDetails The UserDetails object representing the user information.
     * @return The generated token as a string.
     */
    public String generateToken(final UserDetails userDetails) {

        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates an authentication token based on the given extra claims and user details.
     *
     * @param extraClaims a map containing additional claims to be included in the token
     * @param userDetails the user details used to generate the token
     * @return the generated authentication token
     */
    public String generateToken(
            final Map<String, Object> extraClaims,
            final UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    /**
     * Builds a JSON Web Token (JWT) with the given extra claims, userDetails, and expiration.
     *
     * @param extraClaims the extra claims to add to the token
     * @param userDetails the UserDetails object containing the user's information
     * @param expiration  the token expiration time in milliseconds
     * @return a JWT string representation of the token
     */
    private String buildToken(
            final Map<String, Object> extraClaims,
            final UserDetails userDetails,
            final long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Checks if the given token is valid for the specified user details.
     *
     * @param token       The token to be validated.
     * @param userDetails The user details against which the token should be validated.
     * @return True if the token is valid for the user details, false otherwise.
     */
    public boolean isTokenValid(
            final String token,
            final UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Checks if the given token is expired.
     *
     * @param token The token to check for expiration
     * @return true if the token is expired, false otherwise
     */
    private boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the given token.
     *
     * @param token the token from which to extract the expiration date
     * @return the expiration date extracted from the token
     */
    private Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts all claims from a given token.
     *
     * @param token the token from which to extract the claims
     * @return the extracted claims
     */
    private Claims extractAllClaims(final String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Retrieve the sign-in key for authentication.
     *
     * @return the sign-in key as a Key object
     */
    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
