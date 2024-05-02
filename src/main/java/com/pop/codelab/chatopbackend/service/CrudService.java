package com.pop.codelab.chatopbackend.service;

import com.pop.codelab.chatopbackend.dto.BaseDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * The CrudService interface provides CRUD (Create, Read, Update, Delete) operations for
 * working with DTOs (Data Transfer Objects) that extends the BaseDto class.
 *
 * @param <T> the type of DTO that extends BaseDto
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@SuppressWarnings("checkstyle:LineLength")
public interface CrudService<T extends BaseDto> {

    /**
     * Retrieves all objects.
     *
     * @return a list of objects
     */
    List<?> findAll();

    /**
     * Retrieves an object by its ID.
     *
     * @param id the ID of the object to be retrieved
     * @return an Optional object containing the retrieved object if found, otherwise an empty Optional object
     * @throws IOException if an IO error occurs
     */
    @SuppressWarnings("checkstyle:LineLength")
    Optional<T> findById(Long id) throws IOException;

    /**
     * Saves the given DTO object.
     *
     * @param dto the DTO object to be saved
     * @return the saved DTO object
     */
    T save(T dto);

    /**
     * Deletes an object by its ID.
     *
     * @param id the ID of the object to be deleted
     */
    void delete(Long id);

    /**
     * Updates an object with the specified ID.
     *
     * @param id  the ID of the object to be updated
     * @param dto the updated object
     * @return the updated object
     */
    T update(Long id, T dto);
}
