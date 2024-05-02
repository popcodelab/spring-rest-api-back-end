package com.pop.codelab.chatopbackend.configuration;

import com.pop.codelab.chatopbackend.auth.AuthenticationService;
import com.pop.codelab.chatopbackend.business.message.MessageDto;
import com.pop.codelab.chatopbackend.business.message.MessageService;
import com.pop.codelab.chatopbackend.business.rental.RentalService;
import com.pop.codelab.chatopbackend.business.rental.dto.RentalDto;
import com.pop.codelab.chatopbackend.business.user.UserRepository;
import com.pop.codelab.chatopbackend.business.user.dto.UserCreationDto;
import com.pop.codelab.chatopbackend.business.user.dto.UserDto;
import com.pop.codelab.chatopbackend.service.ImageService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

import static com.pop.codelab.chatopbackend.business.user.Role.*;

/**
 * The LoadDatabase class is responsible for setting up the initial data in the application database.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Configuration
public class LoadDatabase {

    /**
     * The logger variable is an instance of the Logger class from the org.slf4j package.
     **/
    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    /**
     * The userRepository variable is an instance of the UserRepository interface,
     * which extends the JpaRepository interface.
     * It provides methods to perform CRUD operations on User entities in the database.
     * <p>
     * It has the following methods:
     * - findByEmail(String email): Returns an Optional<User> object that represents
     * the user with the given email address, if it exists.
     * <p>
     * Usage examples:
     * - Optional<User> user = userRepository.findByEmail("example@example.com");
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * The userCreationDto variable represents the data needed to create a new user.
     */
    private UserCreationDto userCreationDto;

    /**
     * Mapper used to map Dto and Entity.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * The commandLineRunner method is a bean that implements the CommandLineRunner interface.
     * It is responsible for executing the specified logic when the application starts.
     * <p>
     * Performs setup actions.
     * <p>
     * Once the Spring application context is fully loaded and before it starts running methods to initialize data
     * are called
     *
     * @param authenticationService the AuthenticationService instance used to create users
     * @param messageService        the MessageService instance used to create messages
     * @param rentalService         the RentalService instance used to create rentals
     * @param imageService          the ImageService instance used to save rental pictures
     * @return a CommandLineRunner object that executes the specified logic
     */
    @Bean
    public CommandLineRunner commandLineRunner(
            final AuthenticationService authenticationService,
            final MessageService messageService,
            final RentalService rentalService,
            final ImageService imageService
    ) {
        return args -> {
            createUsers(authenticationService);
            createMessages(messageService);
            createRentals(rentalService, imageService);
        };
    }

    // region Data creation

    /**
     * Creates three users - one ADMIN, one MANAGER, and one USER.
     *
     * @param authenticationService the AuthenticationService instance used for user registration
     */
    private void createUsers(final AuthenticationService authenticationService) {
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

    /**
     * Creates two messages from the admin user.
     *
     * @param messageService The MessageService instance used to save the messages.
     */
    private void createMessages(final MessageService messageService) {
        logger.info("Creating messages...");

        var user = userRepository.findByEmail("admin@mail.com").orElseThrow();

        MessageDto messageDtoToCreate = MessageDto.builder()
                .message("This is a first message from admin")
                .user(modelMapper.map(user, UserDto.class))
                .build();
        MessageDto savedMessageDto = messageService.save(messageDtoToCreate);
        logger.debug("Message added : {}", savedMessageDto);

        messageDtoToCreate = MessageDto.builder()
                .message("This is a second message from admin")
                .user(modelMapper.map(user, UserDto.class))
                .build();

        savedMessageDto = messageService.save(messageDtoToCreate);
        logger.debug("Message added : {}", savedMessageDto);

        logger.info("Messages created.");
    }

    /**
     * Creates rentals in the system.
     *
     * @param rentalService the RentalService instance used to save the rentals
     * @param imageService  the ImageService instance used to save the rental pictures
     */
    private void createRentals(
            final RentalService rentalService,
            final ImageService imageService) {
        logger.info("Creating rentals...");

        var user = userRepository.findByEmail("user@mail.com").orElseThrow();

        RentalDto rentalDtoToCreate = RentalDto.builder()
                .price(BigDecimal.valueOf(1000))
                .description("My First Rental ")
                .name("rental_1")
                .surface(BigDecimal.valueOf(50))
                //.picture()
                .user(modelMapper.map(user, UserDto.class))
                .build();

        RentalDto savedRentalDto = rentalService.save(rentalDtoToCreate);
        logger.debug("Rental added : {}", savedRentalDto);

        rentalDtoToCreate = RentalDto.builder()
                .price(BigDecimal.valueOf(1030))
                .description("My second Rental ")
                .name("rental_2")
                .surface(BigDecimal.valueOf(59))
                //.picture()
                .user(modelMapper.map(user, UserDto.class))
                .build();

        savedRentalDto = rentalService.save(rentalDtoToCreate);
        logger.debug("Rental added : {}", savedRentalDto);

        logger.info("Rentals created.");
    }

    // endregion Data creation
}
