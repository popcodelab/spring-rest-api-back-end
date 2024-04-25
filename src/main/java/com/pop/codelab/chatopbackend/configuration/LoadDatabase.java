package com.pop.codelab.chatopbackend.configuration;

import com.pop.codelab.chatopbackend.auth.AuthenticationService;
import com.pop.codelab.chatopbackend.message.MessageDto;
import com.pop.codelab.chatopbackend.message.MessageService;
import com.pop.codelab.chatopbackend.rental.Rental;
import com.pop.codelab.chatopbackend.rental.RentalDto;
import com.pop.codelab.chatopbackend.rental.RentalService;
import com.pop.codelab.chatopbackend.service.ImageService;
import com.pop.codelab.chatopbackend.user.UserCreationDto;
import com.pop.codelab.chatopbackend.user.UserDto;
import com.pop.codelab.chatopbackend.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.nio.file.Path;

import static com.pop.codelab.chatopbackend.user.Role.*;


@Configuration
public class LoadDatabase {

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    private UserRepository userRepository;

    private UserCreationDto userCreationDto;

    @Value("${application.local-storage.upload-directory}")
    private String uploadDirectory;

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
            MessageService messageService,
            RentalService rentalService,
            ImageService imageService
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

    private void createRentals(RentalService rentalService, ImageService imageService){
        logger.info("Creating rentals...");

        var user = userRepository.findByEmail("user@mail.com").orElseThrow();

        //File file = new File("C:\\Users\\pierr\\OneDrive\\Images\\fucked up unicorn.png");


        RentalDto rentalDtoToCreate = RentalDto.builder()
                .price(BigDecimal.valueOf(1000))
                .description("My First Rental ")
                .name("rental_1")
                .surface(BigDecimal.valueOf(50))
                //.picture()
                .user(modelMapper.map(user, UserDto.class))
                .build();

        RentalDto savedRentalDto = rentalService.save(rentalDtoToCreate);
        logger.debug("Message added : {}", savedRentalDto);
    }

    // endregion Data creation
}
