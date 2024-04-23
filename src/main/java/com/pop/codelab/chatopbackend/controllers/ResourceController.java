package com.pop.codelab.chatopbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST})
@RequiredArgsConstructor
public class ResourceController {

    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @GetMapping("/welcome")
    public String getResource() {
        logger.info("return a value...");
        return "a value...";
    }

}

