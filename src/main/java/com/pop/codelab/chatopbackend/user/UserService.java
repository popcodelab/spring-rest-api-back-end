package com.pop.codelab.chatopbackend.user;

import com.pop.codelab.chatopbackend.exception.ResourceNotFoundException;
import com.pop.codelab.chatopbackend.service.CrudService;
import com.pop.codelab.chatopbackend.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The UserService class provides CRUD (Create, Read, Update, Delete) operations for UserDto objects.
 * It implements the CrudService interface and is responsible for interacting with the UserRepository.
 * <p>
 * The UserService class uses the ModelMapper to convert User entities to UserDto objects and vice versa.
 * The ModelMapper is autowired using the @Autowired annotation.
 * <p>
 * The UserService class also uses a Logger to log information and debug messages.
 * The Logger is initialized with the class name and is static final.
 * <p>
 * The UserService class is annotated with the @Service annotation to indicate that it is a service class
 * and should be managed by the Spring framework.
 * <p>
 * This class provides the following methods:
 * <ul>
 *   <li>{@link #findAll()}</li>
 *   <li>{@link #findById(Long)}</li>
 *   <li>{@link #save(UserDto)}</li>
 *   <li>{@link #delete(Long)}</li>
 *   <li>{@link #update(Long, UserDto)}</li>
 * </ul>
 * <p>
 *  @author Pignon Pierre-Olivier
 *  @version 1.0
 *
 * @see CrudService
 * @see UserRepository
 * @see UserDto
 * @see Service
 */
@Service
@RequiredArgsConstructor
public class UserService implements CrudService<UserDto> {

    /**
     * The logger variable is used to log information and debug messages.
     * <p></p>
     * It is a static final variable of type Logger and is initialized using the LoggerFactory.getLogger() method.
     * The LoggerFactory is a utility class for obtaining Logger instances.
     *
     * @see Logger
     * @see LoggerFactory
     * @see UserService
     */
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * The userRepository variable represents a repository that provides CRUD (Create, Read, Update, Delete) operations for User entities.
     * It is of type UserRepository, which extends the JpaRepository interface and specifies the User entity type and the type of the primary key (Long).
     *
     * @see UserRepository
     * @see JpaRepository
     * @see User
     */
    private final UserRepository userRepository;

    /**
     * The modelMapper variable represents an instance of the ModelMapper class.
     * <p></p>
     * The ModelMapper class is a library that simplifies the mapping between different objects.
     * It provides a simple and flexible API for mapping one object to another, based on the defined mappings and rules.
     * ModelMapper uses a combination of reflection, conversion, and convention-based strategies to perform the mapping.
     * <p></p>
     * The modelMapper variable is annotated with @Autowired, indicating that it is automatically injected by Spring dependency injection.
     * <p></p>
     * This variable is defined in the UserService class, which is a service class responsible for managing user-related operations.
     * The UserService class contains methods for CRUD operations on user entities, such as finding all users, finding a user by ID, saving a user, updating a user, and deleting a
     * user.
     * The modelMapper variable is used in these methods to map between User and UserDto objects.
     * <p></p>
     * The ModelMapper class provides various methods for mapping objects, such as map(), mapList(), and mapType().
     * These methods can be used to map between different object types, collections, and generic types.
     *
     * @see com.pop.codelab.chatopbackend.service.UserService
     * @see com.pop.codelab.chatopbackend.dto.UserDto
     * @see com.pop.codelab.chatopbackend.entity.User
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Retrieves all users from the system.
     * <p>
     * This method queries the UserRepository to get a list of all users.
     * If the list is empty, a ResourceNotFoundException is thrown.
     * Otherwise, the list of User objects is converted to a list of UserDto objects using the convertToDto method.
     * <p></p>
     *
     * @return A list of UserDto objects representing all users in the system.
     * @throws ResourceNotFoundException if no user is found.
     */
    @Override
    public List<UserDto> findAll() {
        logger.info("Gathering all users...");
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No user found");
        }
        logger.debug("User(s) count : {}", users.size());
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * Finds a user by its ID.
     *
     * @param id the ID of the user to be retrieved
     * @return an Optional containing the UserDto object if found, or an empty Optional if not found
     * @throws ResourceNotFoundException if no user is found with the given ID
     */
    @Override
    public Optional<UserDto> findById(final Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("No user found with Id : " + id);
        }
        logger.debug("User Dto retrieved : {} ", userOptional);
        return userOptional.map(this::convertToDto);
    }

    /**
     * TODO To implements
     *
     * @param dto the DTO object to be saved
     */
    @Override
    public UserDto save(final UserDto dto) {
        return null;
    }

    /**
     * TODO To implements
     *
     * @param id the ID of the object to be deleted
     */
    @Override
    public void delete(final Long id) {

    }

    /**
     * TODO To implements
     *
     * @param id  the ID of the object to be updated
     * @param dto the updated object
     */
    @Override
    public UserDto update(
            final Long id,
            final UserDto dto) {
        return null;
    }

    /**
     * Converts a User object to a UserDto object using ModelMapper.
     *
     * @param user The User object to be converted.
     * @return The converted UserDto object.
     */
    private UserDto convertToDto(final User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
