package com.pop.codelab.chatopbackend.controllers;

import com.pop.codelab.chatopbackend.dto.BaseDto;
import com.pop.codelab.chatopbackend.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Optional;

/**
 * The CrudController class is an abstract class that provides basic CRUD (Create, Read, Update, Delete) operations for entities.
 *
 * @param <T> the type of the entity DTO
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
public abstract class CrudController<T extends BaseDto> {

    /**
     * Private variable {@code service} of type {@code CrudService<T>}.
     */
    private CrudService<T> service;

    /**
     * The CrudController class is a generic controller that provides CRUD (Create, Read, Update, Delete) operations
     * for working with DTOs (Data Transfer Objects) that extend the BaseDto class.
     * <p></p>
     *
     * @param <T>         the type of DTO that extends BaseDto
     *                    <p></p>
     * @param crudService an instance of CrudService that provides the CRUD operations for the DTOs
     */
    public CrudController(final CrudService<T> crudService) {
        this.service = crudService;
    }

    /**
     * Retrieves all objects from the server.
     * <p></p>
     *
     * @return a ResponseEntity containing the list of objects and the HTTP status code OK (200)
     */
    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    /**
     * Retrieves an object by its ID.
     * <p></p>
     *
     * @param id the ID of the object to be retrieved
     * @return a ResponseEntity containing the object if found, or null and the HTTP status code NOT_FOUND (404) if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(final @PathVariable Long id) {
        Optional<T> optionalT;
        try {
            optionalT = service.findById(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return optionalT.map(T ->
                        new ResponseEntity<>(T, HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    /**
     * Saves the object to the server.
     * <p></p>
     *
     * @param entity the object to be saved
     * @return a ResponseEntity containing the saved object and the HTTP status code CREATED (201)
     */
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody T entity) {
        return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
    }

    /**
     * Deletes an object with the specified ID.
     * <p></p>
     *
     * @param id the ID of the object to be deleted
     * @return a ResponseEntity with the status code and message indicating the result of the deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(final @PathVariable Long id) {
        Optional<T> optional;
        try {
            optional = service.findById(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        service.delete(id);
        return optional.map(T ->
                        new ResponseEntity<>("Object with the id " + id + " was deleted.", HttpStatus.NO_CONTENT))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND));
    }

    /**
     * Updates an object with the specified ID.
     * <p></p>
     *
     * @param id     the ID of the object to be updated
     * @param entity the updated object
     * @return a ResponseEntity with the HTTP status code and message indicating the result of the update
     * @throws RuntimeException if an error occurs while updating the object
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            final @PathVariable Long id,
            final @RequestBody T entity) {
        Optional<T> optional;
        try {
            optional = service.findById(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        optional.ifPresent(n -> service.update(id, entity));
        return optional.map(n ->
                        new ResponseEntity<>("Object with id " + id + " was updated.", HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND));
    }
}

