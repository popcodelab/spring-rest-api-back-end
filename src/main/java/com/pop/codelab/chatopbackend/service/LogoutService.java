package com.pop.codelab.chatopbackend.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

/**
 * The LogoutService class implements the LogoutHandler interface to handle user logout functionality.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    /**
     * Logs out a user by invalidating the provided JWT token from the request headers.
     *
     * @param request        the HTTP servlet request
     * @param response       the HTTP servlet response
     * @param authentication the authentication object representing the current user
     */
    @Override
    public void logout(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Authentication authentication
    ) {
        // TOODO implement the logout
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//        }
    }
}
