package com.pop.codelab.chatopbackend.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 * @see JwtAuthenticationFilter
 * @see LogoutHandler
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
@OpenAPIDefinition(info = @Info(title = "ChâTop API", version = "V2")) // Defines OpenAPI information for Swagger UI
public class SecurityConfiguration {

    /**
     * The WHITE_LIST_URL variable represents a list of URLs that are allowed to bypass authentication
     * and authorization checks in the application.
     * These URLs are considered to be public and accessible to all users without any restrictions.
     *
     * @see SecurityConfiguration
     * @see JwtAuthenticationFilter
     */
    private static final String[] WHITE_LIST_SWAGGER_URL = {
            "/api-docs",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-ui/index.html"
    };

    /**
     * The JwtAuthenticationFilter class is responsible for validating and authenticating JWT (JSON Web Token) in the HTTP request.
     * <p>
     * This filter checks if the request contains a valid JWT in the "Authorization" header and authenticates the user based on the token.
     * If the token is valid, the authenticated user is stored in the SecurityContextHolder for further request processing.
     * If the token is not present or
     */
    private final JwtAuthenticationFilter jwtAuthenticationFilter;


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
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(WHITE_LIST_SWAGGER_URL)
                                .permitAll()
                                .requestMatchers("/images/**") // Assuming the URLs for serving images start with "/api/images/"
                                .permitAll()
                                .requestMatchers("/auth/register", "/auth/login").permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
