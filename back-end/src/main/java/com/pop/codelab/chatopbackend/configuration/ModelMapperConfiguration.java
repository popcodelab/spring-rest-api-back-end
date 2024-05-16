package com.pop.codelab.chatopbackend.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for configuring the ModelMapper bean.
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
        ModelMapper modelMapper = new ModelMapper();
        // This line has been added to solve the issue with Message entity and DTO
        // The destination property com.pop.codelab.chatopbackend.common.entity.BaseEntity.setId() matches multiple
        // source property hierarchies (userId & RentalId => user_id & rental_id)
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
