package com.pop.codelab.chatopbackend.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JwtAuthenticationFilter is a filter that is responsible for validating and authenticating JWT (JSON Web Token) in the HTTP request.
 * <p>
 * This filter checks if the request contains a valid JWT in the "Authorization" header and authenticates the user based on the token.
 * If the token is valid, the authenticated user is stored in the SecurityContextHolder for further request processing.
 * If the token is not present or not valid, the filter allows the request to proceed without authentication.
 * <p>
 * This filter extends the OncePerRequestFilter class, ensuring that it is executed only once per request.
 * <p>
 * Usage:
 * Include this filter in the SecurityConfiguration class by adding it using the addFilterBefore method.
 * This ensures that the filter is executed before the UsernamePasswordAuthenticationFilter.
 * <p>
 * Constructor:
 * - JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService)
 * Constructs a new JwtAuthenticationFilter with the provided JwtService and UserDetailsService implementations.
 * <p>
 * Methods:
 * - doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
 * Implements the authentication logic. Performs the following steps:
 * 1. Checks if the request path contains "/api/auth". If true, allows the request to proceed without authentication.
 * 2. Retrieves the JWT token from the "Authorization" header.
 * 3. Extracts the email from the JWT token using the JwtService.extractUsername method.
 * 4. If the email is not null and there is no existing authentication in the SecurityContextHolder, performs the following steps:
 * - Retrieves the UserDetails for the email using the UserDetailsService.loadUserByUsername method.
 * - Validates the token using the JwtService.isTokenValid method.
 * - Builds a new UsernamePasswordAuthenticationToken with the UserDetails, null credentials, and authorities.
 * - Sets the details of the authentication token using the WebAuthenticationDetailsSource.
 * - Sets the authentication in the SecurityContextHolder.
 * 5. Allows the request to continue down the filter chain.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    /**
     * The JwtService class is responsible for generating and validating JSON Web Tokens (JWT).
     * It provides methods for extracting claims from a token, generating a token based on user details,
     * and checking if a token is valid and not expired.
     */
    private final JwtService jwtService;
    /**
     * The UserDetailsService variable represents a service that is responsible for fetching user details.
     * It is used in the JwtAuthenticationFilter class.
     * <p>
     * This variable is declared as private and final to ensure immutability after initialization.
     * <p>
     * Please refer to the documentation of the JwtAuthenticationFilter class for more information on how this variable is utilized.
     *
     * @see JwtAuthenticationFilter
     */
    private final UserDetailsService userDetailsService;

    /**
     * Performs the filtering of the incoming request and response.
     *
     * @param request     the HTTP servlet request
     * @param response    the HTTP servlet response
     * @param filterChain the filter chain object
     * @throws ServletException if there is a servlet error
     * @throws IOException      if there is an I/O error
     */
    @Override
    protected void doFilterInternal(
            final @NonNull HttpServletRequest request,
            final @NonNull HttpServletResponse response,
            final @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final byte END_OF_BEARER_PREFIX_INDEX = 7;
        jwt = authHeader.substring(END_OF_BEARER_PREFIX_INDEX);
        userEmail = jwtService.extractUsername(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
