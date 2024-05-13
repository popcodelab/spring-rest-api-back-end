package com.pop.codelab.chatopbackend.business.user;

import com.pop.codelab.chatopbackend.business.user.dto.responses;
import com.pop.codelab.chatopbackend.exception.ResourceNotFoundException;
import com.pop.codelab.chatopbackend.service.CrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
 *   <li>{@link #save(responses.UserDto)}</li>
 *   <li>{@link #delete(Long)}</li>
 *   <li>{@link #update(Long, responses.UserDto)}</li>
 * </ul>
 * <p>
 *  @author Pignon Pierre-Olivier
 *  @version 1.0
 *
 * @see CrudService
 * @see UserRepository
 * @see responses.UserDto
 * @see Service
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class UserService implements UserService {



    /**
     * The userRepository variable represents a repository that provides CRUD (Create, Read, Update, Delete) operations for User entities.
     * It is of type UserRepository, which extends the JpaRepository interface and specifies the User entity type and the type of the primary key (Long).
     *
     * @see UserRepository
     * @see JpaRepository
     * @see User
     */
    private final UserRepository userRepository;


}
