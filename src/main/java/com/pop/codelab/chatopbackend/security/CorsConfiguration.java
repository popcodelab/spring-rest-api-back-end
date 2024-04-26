package com.pop.codelab.chatopbackend.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The {@code CorsConfig} class is a configuration class that implements the
 * {@link WebMvcConfigurer}
 * interface. It provides method for adding CORS (Cross-Origin Resource Sharing)
 * mappings to the provided CORS registry.
 *
 * @author Pignon Pierre-Olivier
 */
@Configuration
@EnableWebSecurity
public class CorsConfiguration implements WebMvcConfigurer {

    /**
     * Add CORS (Cross-Origin Resource Sharing) mappings to the provided CORS registry.
     *
     * @param registry the CORS registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
//                .allowedOrigins("http://localhost:4200") // Front Chatop Url
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("*");
    }
}
