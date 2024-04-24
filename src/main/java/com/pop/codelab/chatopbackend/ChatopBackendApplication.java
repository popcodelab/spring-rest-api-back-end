package com.pop.codelab.chatopbackend;

import com.pop.codelab.chatopbackend.auth.AuthenticationService;
import com.pop.codelab.chatopbackend.auth.RegisterRequest;
import com.pop.codelab.chatopbackend.controllers.ResourceController;
import com.pop.codelab.chatopbackend.message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.pop.codelab.chatopbackend.user.Role.*;

/**
 * The ChatopBackendApplication class is the entry point for the Châtop
 * Back-end application. It is responsible for starting the application and
 * initializing the Spring Boot framework.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@SpringBootApplication()
public class ChatopBackendApplication  {

	private static final Logger logger = LoggerFactory.getLogger(ChatopBackendApplication.class);

	/**
	 * The main method is the entry point for the Châtop Back-end application.
	 * It starts the application by initializing the Spring application context and
	 * starting the embedded web server.
	 *
	 * @param args the command line arguments for the main method.
	 */
	public static void main(String[] args) {
		logger.info("Application is starting...");
		SpringApplication.run(ChatopBackendApplication.class, args);
	}



}
