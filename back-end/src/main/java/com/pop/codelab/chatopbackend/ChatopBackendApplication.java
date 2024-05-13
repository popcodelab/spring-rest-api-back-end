package com.pop.codelab.chatopbackend;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The ChatopBackendApplication class is the entry point for the Châtop
 * Back-end application. It is responsible for starting the application and
 * initializing the Spring Boot framework.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@SpringBootApplication()
@Log4j2
public class ChatopBackendApplication {


    /**
     * The main method is the entry point for the Châtop Back-end application.
     * It starts the application by initializing the Spring application context and
     * starting the embedded web server.
     *
     * @param args the command line arguments for the main method.
     */
    public static void main(final String[] args) {
        log.info("Application is starting...");
        SpringApplication.run(ChatopBackendApplication.class, args);
    }
}
