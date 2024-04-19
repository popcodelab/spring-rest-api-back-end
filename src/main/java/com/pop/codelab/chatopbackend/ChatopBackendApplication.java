package com.pop.codelab.chatopbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


/**
 * The ChatopBackendApplication class is the entry point for the Châtop
 * Back-end application. It is responsible for starting the application and
 * initializing the Spring Boot framework.
 *
 * The class is annotated with the SpringBootApplication annotation, which
 * not only indicates that this is a Spring Boot application, but also
 * enables auto-configuration and component scanning.
 *
 * This will start the Châtop Back-end application by initializing the Spring
 * application context and starting the embedded web server.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@SpringBootApplication()
public class ChatopBackendApplication {

	/**
	 * The main method is the entry point for the Châtop Back-end application.
	 * It starts the application by initializing the Spring application context and
	 * starting the embedded web server.
	 *
	 * @param args the command line arguments for the main method.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ChatopBackendApplication.class, args);
	}

}
