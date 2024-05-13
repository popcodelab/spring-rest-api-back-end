package com.pop.codelab.chatopbackend.business.rental;

import com.pop.codelab.chatopbackend.business.rental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RentalRepository is an interface that extends the JpaRepository interface, which provides basic CRUD operations
 * for managing Rental entities in the database.
 * <p>
 * The RentalRepository interface specifies the type of entity (Rental) and the type of the primary key (Long)
 * for the repository. It inherits all the methods from the JpaRepository interface, such as save(), findById(),
 * findAll(), delete(), etc.</p>
 * <p>
 * This interface is used to interact with the database and perform CRUD operations on Rental entities.</p>
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 * @see Rental
 * @see JpaRepository
 */
public interface RentalRepository extends JpaRepository<Rental, Long> {
}
