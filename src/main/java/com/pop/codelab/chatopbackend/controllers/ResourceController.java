package com.pop.codelab.chatopbackend.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This generic class serves as the controller for the resource API.
 * <p></p>
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */

// TODO Remove class
@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET})
@Tag(name = "ResourceController", description = "Provides endpoints for ${app.name} resource")
@RequiredArgsConstructor
public class ResourceController {

    /**
     * Logger instance.
     */
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);


    /**
     * Returns a resource.
     *
     * @return The resource as a string.
     */
    @GetMapping("/welcome")
    public String getResource() {
        logger.info("return a value...");
        return "a value...";
    }

}

