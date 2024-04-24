package com.pop.codelab.chatopbackend.configuration;

import com.pop.codelab.chatopbackend.auth.AuthenticationService;
import com.pop.codelab.chatopbackend.auth.RegisterRequest;
import com.pop.codelab.chatopbackend.message.MessageDto;
import com.pop.codelab.chatopbackend.message.MessageService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.pop.codelab.chatopbackend.user.Role.*;

@Configuration
public class LoadDatabase {

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    /**
     * Performs setup actions.
     * <p>
     * Once the Spring application context is fully loaded and before it starts running methods to initialize data
     * are called
     *
     * @param authenticationService the AuthenticationService instance used for registration
     */
    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService authenticationService,
            MessageService messageService
    ) {
        return args -> {
            createUsers(authenticationService);
            createMessages(messageService);
        };
    }

    // region Data creation

    /**
     * Creates three users - one ADMIN, one MANAGER, and one USER.
     *
     * @param authenticationService the AuthenticationService instance used for user registration
     */
    private void createUsers(AuthenticationService authenticationService) {
        logger.info("Creating users...");
        var admin = RegisterRequest.builder()
                .name("Admin")
                .email("admin@mail.com")
                .password("password")
                .role(ADMIN)
                .build();

        String token = authenticationService.register(admin).getAccessToken();
        logger.debug("Created user {} - token : {}", admin.getName(), token);

        var manager = RegisterRequest.builder()
                .name("Manager")
                .email("manager@mail.com")
                .password("password")
                .role(MANAGER)
                .build();
        token = authenticationService.register(manager).getAccessToken();
        logger.debug("Created user {} - token : {}", manager.getName(), token);

        var user = RegisterRequest.builder()
                .name("User")
                .email("user@mail.com")
                .password("password")
                .role(USER)
                .build();
        token = authenticationService.register(user).getAccessToken();
        logger.debug("Created user {} - token : {}", user.getName(), token);
        logger.info("Users created.");
    }

    private void createMessages(MessageService messageService) {
        logger.info("Creating messages...");
        MessageDto messageDto = MessageDto.builder()
                .message("This is a first message")
                .build();
        var message = messageService.save(messageDto);
        logger.debug("Message added : {}", message);
//

//		var admin = RegisterRequest.builder()
//				.name("Admin")
//				.email("admin@mail.com")
//				.password("password")
//				.role(ADMIN)
//				.build();
//
//
//		logger.debug("Created message {} - token : {}", admin.getName(), token);
        logger.info("Messages created.");
    }

    // endregion Data creation
}
