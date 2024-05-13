package com.pop.codelab.chatopbackend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * Configuration class for setting up MVC in the application.
 * Implements WebMvcConfigurer interface to configure resource handlers.
 */
@Configuration
public class ResourceHandlerConfiguration implements WebMvcConfigurer {

    /**
     * Adds resource handlers to the resource handler registry for accessing images.
     *
     * @param registry the resource handler registry to add resource handlers to
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("api/images/**") // Define the URL pattern for accessing the images

                .addResourceLocations("classpath:/static/images/") // Define the location of the images in the resources folder
                .setCachePeriod(3600)
                .resourceChain(false)
                .addResolver(new PathResourceResolver());
    }

}
