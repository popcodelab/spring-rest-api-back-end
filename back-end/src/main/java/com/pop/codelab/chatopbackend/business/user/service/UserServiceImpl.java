package com.pop.codelab.chatopbackend.business.user.service;

import com.pop.codelab.chatopbackend.auth.dto.responses.UserDto;
import com.pop.codelab.chatopbackend.business.user.repository.UserRepository;
import com.pop.codelab.chatopbackend.business.user.entity.User;
import com.pop.codelab.chatopbackend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * The UserServiceImpl class provides CRUD (Create, Read, Update, Delete) operations for UserDto objects.
 *
 * @author Pignon Pierre-Olivier
 * @version 2.0
 * @see UserRepository
 * @see Service
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    /**
     * The userRepository variable represents an instance of the UserRepository interface
     * that provides CRUD operations for User entities in the system.
     *
     * @see UserRepository
     * @see UserServiceImpl
     */
    private final UserRepository userRepository;

    /**
     * The ModelMapper variable represents an instance of the ModelMapper class used for mapping objects between different classes.
     * It is a private final variable, indicating that it cannot be changed once initialized.
     */
    private final ModelMapper modelMapper;

    /**
     * Finds a user by their ID.
     *
     * @param id The ID of the user to find.
     * @return The UserDto object of the user with the given ID.
     * @throws ResourceNotFoundException if no user with the given ID is found.
     */
    @Override
    public UserDto findUserById(final Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No user found with Id : " + id));
        log.debug("User id : {} - name : {} has been found.", user.getId(), user.getName());
        return this.modelMapper.map(user, UserDto.class);
    }
}
