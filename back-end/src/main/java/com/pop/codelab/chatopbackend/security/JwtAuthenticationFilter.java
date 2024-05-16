package com.pop.codelab.chatopbackend.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pop.codelab.chatopbackend.business.user.repository.UserRepository;
import com.pop.codelab.chatopbackend.business.user.entity.User;
import com.pop.codelab.chatopbackend.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * The JwtAuthenticationFilter class is a filter that performs authentication using a JSON Web Token (JWT) for authorization.
 * It extends the OncePerRequestFilter class, which ensures that the filter is only applied once per request.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    /**
     * The JwtServiceImpl class is responsible for generating and validating JSON Web Tokens (JWT).
     * It provides methods for extracting claims from a token, generating a token based on user details,
     * and checking if a token is valid and not expired.
     */
    private final JwtService jwtService;

    /**
     * The userRepository variable represents an instance of the UserRepository interface.
     * It is used in the JwtAuthenticationFilter class to extract the User entity from a JSON Web Token (JWT).
     * The UserRepository interface is responsible for providing CRUD operations for User entities in the system,
     * including finding a user by email.
     *
     * @see UserRepository
     * @see JwtAuthenticationFilter
     */
    private final UserRepository userRepository;

    /**
     * Filters the incoming request and response and performs authentication using a JSON Web Token (JWT) for authorization.
     *
     * @param request     The HttpServletRequest object representing the incoming request.
     * @param response    The HttpServletResponse object representing the outgoing response.
     * @param filterChain The FilterChain object for invoking the next filter or the servlet.
     * @throws ServletException If an error occurs during the servlet request processing.
     * @throws IOException      If an I/O error occurs during the servlet request processing.
     */
    @Override
    protected void doFilterInternal(
            final @NonNull HttpServletRequest request,
            final @NonNull HttpServletResponse response,
            final @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final byte END_OF_BEARER_PREFIX_INDEX = 7;
        String bearerToken = authHeader.substring(END_OF_BEARER_PREFIX_INDEX);

        DecodedJWT decodedToken;
        try {
            decodedToken = this.jwtService.decodeJwtToken(bearerToken);
        } catch (JWTVerificationException e) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            if (decodedToken != null && extractUserFromToken(decodedToken).isPresent()) {
                User user = extractUserFromToken(decodedToken).get();
                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private Optional<User> extractUserFromToken(final DecodedJWT token) {
        Long userId = Long.parseLong(token.getSubject());
        return userRepository.findById(userId);
    }
}
