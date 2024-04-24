package com.pop.codelab.chatopbackend.configuration;

import com.pop.codelab.chatopbackend.auth.AuthenticationService;
import com.pop.codelab.chatopbackend.message.MessageDto;
import com.pop.codelab.chatopbackend.message.MessageService;
import com.pop.codelab.chatopbackend.user.UserCreationDto;
import com.pop.codelab.chatopbackend.user.UserDto;
import com.pop.codelab.chatopbackend.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.pop.codelab.chatopbackend.user.Role.*;


@Configuration
public class LoadDatabase {

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    private UserRepository userRepository;

    private UserCreationDto userCreationDto;

    @Autowired
    private ModelMapper modelMapper;

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
        UserCreationDto adminDto = UserCreationDto.builder()
                .name("Admin")
                .email("admin@mail.com")
                .password("password")
                .role(ADMIN)
                .build();
        String token = authenticationService.register(adminDto).getAccessToken();
        logger.debug("Created user {} - token : {}", adminDto.getName(), token);

        UserCreationDto manager = UserCreationDto.builder()
                .name("Manager")
                .email("manager@mail.com")
                .password("password")
                .role(MANAGER)
                .build();
        token = authenticationService.register(manager).getAccessToken();
        logger.debug("Created user {} - token : {}", manager.getName(), token);

        userCreationDto = UserCreationDto.builder()
                .name("User")
                .email("user@mail.com")
                .password("password")
                .role(USER)
                .build();
        token = authenticationService.register(userCreationDto).getAccessToken();
        logger.debug("Created user {} - token : {}", userCreationDto.getName(), token);
        logger.info("Users created.");

    }

    private void createMessages(MessageService messageService) {
        logger.info("Creating messages...");

        var user = userRepository.findByEmail("admin@mail.com").orElseThrow();

        MessageDto messageDto = MessageDto.builder()
                .message("This is a first message from admin")
                .user(modelMapper.map(user, UserDto.class))
                .build();
        var message = messageService.save(messageDto);
        logger.debug("Message added : {}", message);

        messageDto = MessageDto.builder()
                .message("This is a second message from admin")
                .user(modelMapper.map(user, UserDto.class))
                .build();
        message = messageService.save(messageDto);
        logger.debug("Message added : {}", message);

        logger.info("Messages created.");
    }

    // endregion Data creation
}
