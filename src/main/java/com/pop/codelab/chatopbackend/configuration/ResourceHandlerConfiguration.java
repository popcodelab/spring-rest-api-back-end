package com.pop.codelab.chatopbackend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("api/images/**") // Define the URL pattern for accessing the images

                .addResourceLocations("classpath:/static/images/") // Define the location of the images in the resources folder
                .setCachePeriod(3600)
                .resourceChain(false)
                .addResolver(new PathResourceResolver());
    }

}
