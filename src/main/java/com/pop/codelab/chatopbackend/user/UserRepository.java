package com.pop.codelab.chatopbackend.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The UserRepository interface represents a repository interface that provides
 * CRUD operations for User entities in the system.
 * <p>
 * The interface extends the JpaRepository interface, which provides basic
 * CRUD operations for entities, and specifies the User entity type and the type of
 * the primary key (Long).
 * <p>
 * The interface also includes a custom method findByEmail to find a User entity
 * by its email address.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by email.
     *
     * @param email The email address of the user to find.
     * @return An Optional object containing the User if found, or an empty Optional if not found.
     */
    Optional<User> findByEmail(String email);


}
