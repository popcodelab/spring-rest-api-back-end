package com.pop.codelab.chatopbackend.business.user.service;

import com.pop.codelab.chatopbackend.auth.dto.responses.UserDto;

/**
 * The UserService interface provides operations to find user by ID.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
public interface UserService {

    /**
     * Retrieves a UserDto object representing the user with the specified ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The UserDto object of the user with the given ID.
     */
    UserDto findUserById(Long id);
}
