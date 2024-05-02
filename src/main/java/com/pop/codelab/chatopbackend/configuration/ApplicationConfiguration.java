package com.pop.codelab.chatopbackend.configuration;

import com.pop.codelab.chatopbackend.business.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
     * Instance of the UserRepository.
     * It provides methods for retrieving and managing User entities from the database.
     **/
    private final UserRepository repository;

    /**
     * Retrieves the user details for authentication based on the provided username.
     *
     * @return the {@code UserDetailsService} implementation that retrieves the user details
     * @throws UsernameNotFoundException if the user is not found
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Returns an instance of {@code AuthenticationProvider}.
     * <p>
     * The {@code UserDetailsService} is retrieved from the {@code userDetailsService()} method,
     * which retrieves the user details for authentication based on the provided username.
     *
     * @return an instance of {@code AuthenticationProvider} configured with the necessary dependencies
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * Returns an instance of {@code AuthenticationManager}.
     * The {@code AuthenticationManager} is obtained from the provided {@code AuthenticationConfiguration}.
     *
     * @param config the {@code AuthenticationConfiguration} containing the {@code AuthenticationManager} implementation
     * @return an instance of {@code AuthenticationManager} configured with the necessary dependencies
     * @throws Exception if an error occurs while obtaining the {@code AuthenticationManager}
     */
    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

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
