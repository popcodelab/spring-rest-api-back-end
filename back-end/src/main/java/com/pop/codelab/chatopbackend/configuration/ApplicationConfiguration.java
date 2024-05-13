package com.pop.codelab.chatopbackend.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The {@code ApplicationConfiguration} class is a configuration class for the application.
 * It provides the necessary beans for user authentication and authorization.
 * This class is annotated with {@code @Configuration} to indicate that it contains bean definitions.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {


    /**
     * Returns a new instance of {@code BCryptPasswordEncoder} that implements the PasswordEncoder interface.
     * The BCryptPasswordEncoder is a password hashing function that uses the Blowfish cipher and is based
     * on the bcrypt algorithm.
     * It is used to encode passwords for secure storage and authentication purposes.
     *
     * @return a new instance of BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
