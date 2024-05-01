package com.pop.codelab.chatopbackend.security;

import com.pop.codelab.chatopbackend.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * SecurityConfiguration is a class that configures the security settings for the application.
 * It is annotated with @Configuration to indicate that it is a configuration class.
 * <p>
 * It uses Spring Security annotations such as @EnableWebSecurity, @EnableMethodSecurity, and @Bean to configure the security filters and providers.
 * </p>
 * The class has the following dependencies:
 * - JwtAuthenticationFilter: A filter that validates and authenticates JWT (JSON Web Token) in the HTTP request.
 * - AuthenticationProvider: An authentication provider that authenticates user credentials.
 * - LogoutHandler: A logout handler that performs actions when a user logs out.
 * <p></p>
 *
 * @see JwtAuthenticationFilter
 * @see AuthenticationProvider
 * @see LogoutHandler
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    /**
     * The WHITE_LIST_URL variable represents a list of URLs that are allowed to bypass authentication
     * and authorization checks in the application.
     * These URLs are considered to be public and accessible to all users without any restrictions.
     *<p></p>
     * The URLs in the WHITE_LIST_URL variable are matched against the request path to determine
     * if the request should be allowed or not.
     * If the request path matches any of the URLs in the list, the request is allowed to proceed
     * without authentication or authorization checks.
     *<p></p>
     * The WHITE_LIST_URL variable is an array of strings, where each string represents a URL pattern.
     * Wildcard characters such as "*" can be used in the URL patterns to match multiple URLs.
     *<p></p>
     * Note:
     * The WHITE_LIST_URL variable is defined as a private static final field in the SecurityConfiguration class.
     * This variable is used to configure the security filters in the application and ensure that certain URLs
     * are accessible without authentication or authorization.
     * The JwtAuthenticationFilter class, which is responsible for validating and authenticating JWT tokens,
     * also utilizes the WHITE_LIST_URL variable to determine whether a request
     * should be allowed to proceed without authentication.
     *
     * @see SecurityConfiguration
     * @see JwtAuthenticationFilter
     */
    private static final String[] WHITE_LIST_URL = {"/api/auth/**",
            "/api-docs",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };

    /**
     * The JwtAuthenticationFilter class is responsible for validating and authenticating JWT (JSON Web Token) in the HTTP request.
     * <p></p>
     * This filter checks if the request contains a valid JWT in the "Authorization" header and authenticates the user based on the token.
     * If the token is valid, the authenticated user is stored in the SecurityContextHolder for further request processing.
     * If the token is not present or*/
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * The authenticationProvider variable represents an instance of the AuthenticationProvider interface.
     * <p></p>
     * This variable is used in the JwtAuthenticationFilter class to perform user authentication based on the provided token.
     * It is responsible for*/
    private final AuthenticationProvider authenticationProvider;

    /**
     * The logoutHandler variable represents a handler responsible for handling logout functionality.
     * It is used in the SecurityConfiguration class for configuring the logout process.
     * <p></p>
     * This variable is marked as private final, indicating that it cannot be modified after initialization.
     * <p></p>
     * Please note that this variable does not have a specific type listed in the given code,
     * so the actual type and implementation details may not be provided here.
     * The logoutHandler should have methods and logic related to handling logout functionality.
     * <p></p>
     * Refer to the containing class SecurityConfiguration for more information on how this variable is used.
     * <p></p>
     *
     * @see SecurityConfiguration
     */
    private final LogoutHandler logoutHandler;

    /**
     * The securityFilterChain method is used to configure the security filters for the application.
     * It takes an HttpSecurity object as a parameter and returns a SecurityFilterChain object.
     * This method is annotated with @Bean to indicate that it is a Spring bean.
     *
     * @param http The HttpSecurity object used to configure the security filters.
     * @return The configured SecurityFilterChain object.
     * @throws Exception If an error occurs during the configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers("/images/**") // Assuming the URLs for serving images start with "/api/images/"
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );
        return http.build();
    }
}
