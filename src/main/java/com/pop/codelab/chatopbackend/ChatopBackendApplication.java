package com.pop.codelab.chatopbackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ChatopBackendApplication  {
	/**
	 * The logger variable is used for logging messages and events in the ChatopBackendApplication class.
	 * It is an instance of the Logger class from the SLF4J (Simple Logging Facade for Java) framework.
	 * Logging is an essential part of any application as it helps in debugging, monitoring, and troubleshooting.
	 *
	 * The logger is declared as private static final, which means it is a constant and can only be accessed within the ChatopBackendApplication class.
	 * It is recommended to use a constant logger instance for performance reasons.
	 *
	 * The LoggerFactory.getLogger() method is used to obtain a logger instance for the specified class (ChatopBackendApplication.class).
	 * This method is provided by the SLF4J framework and returns a logger implementation based on the underlying logging framework being used (e.g., Logback, Log4j).
	 *
	 * Example usage:
	 *     logger.info("Application is starting...");
	 *     logger.debug("This is a debug message.");
	 *     logger.error("An error occurred during the execution.");
	 *
	 * @see LoggerFactory
	 * @see Logger
	 */
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
