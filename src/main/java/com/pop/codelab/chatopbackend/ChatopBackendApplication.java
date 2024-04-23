package com.pop.codelab.chatopbackend;

import com.pop.codelab.chatopbackend.auth.AuthenticationService;
import com.pop.codelab.chatopbackend.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.pop.codelab.chatopbackend.user.Role.ADMIN;
import static com.pop.codelab.chatopbackend.user.Role.MANAGER;


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


	/**
	 * Creates two users : 1 ADMIN & 1 MANAGER
	 *
	 * @param service the AuthenticationService instance used for registration
	 */
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.name("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.name("Manager")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}

}
