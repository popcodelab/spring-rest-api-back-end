package com.pop.codelab.chatopbackend.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

/**
 * Represents the configuration for the Spring Security framework.
 * The class is used to configure the filter chain, user details service,
 * JWT encoder and decoder, and password encoder.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {

    private String jwtKey = "2BfXcPWbWiH/CezNlhYk/zy9pGqeSExLUilenkcn/mc=\n";

    /**
     * Returns a SecurityFilterChain object that represents the configuration for filtering and handling HTTP requests
     * and responses in the given HttpSecurity object.
     *
     * @param http the HttpSecurity object to configure the filter chain
     * @return a SecurityFilterChain representing the configured filter chain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Disable Cross-Site Request Forgery attack protection
                // STATELESS for RestControllers
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // ll HTTP requests are required to be authenticated, meaning users must be authenticated to access any endpoint.
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                // Configures Spring Security to use JWT Bearer Token authentication for OAuth2 resource servers.
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .httpBasic(Customizer.withDefaults()) // Configures Spring Security to support HTTP Basic authentication with default settings.
                .build();
    }

    /**
     * Creates a UserDetailsService providing in memory users for the application.
     *
     * @return a UserDetailsService with the configured users
     */
    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER").build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER", "ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    //region Bearer Token

    /**
     * Returns a JwtEncoder that encodes JSON Web Tokens (JWTs) for authentication.
     *
     * @return a JwtEncoder using the JWT key for encoding
     */
    @Bean
    public JwtEncoder jwtEncoder() {

        return new NimbusJwtEncoder(new ImmutableSecret<>(this.jwtKey.getBytes()));
    }

    /**
     * Returns a JwtDecoder that decodes JSON Web Tokens (JWTs) for authentication.
     *
     * @return a JwtDecoder using the JWT key for decoding
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKeySpec secretKey = new SecretKeySpec(this.jwtKey.getBytes(), 0, this.jwtKey.getBytes().length, "RSA");
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
    }

    //endregion Bearer Token

    /**
     * Returns a BCryptPasswordEncoder object that can be used to encode passwords.
     *
     * @return a BCryptPasswordEncoder object
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
