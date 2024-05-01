package com.pop.codelab.chatopbackend.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for configuring the ModelMapper bean.
 * <p></p>
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Configuration
public class ModelMapperConfiguration {
    /**
     * Creates and configures a ModelMapper bean.
     *
     * @return The ModelMapper instance.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
