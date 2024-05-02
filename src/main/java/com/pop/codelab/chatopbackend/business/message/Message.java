package com.pop.codelab.chatopbackend.business.message;


import com.pop.codelab.chatopbackend.business.rental.Rental;
import com.pop.codelab.chatopbackend.business.user.User;
import com.pop.codelab.chatopbackend.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The Message class represents a message entity in the system.
 * It extends the BaseEntity class, which provides common fields such as id, createdAt, and updatedAt.
 * The Message class is annotated with @Data, @SuperBuilder, @NoArgsConstructor, @AllArgsConstructor, @Entity, and @Table,
 * indicating that it is an entity class with automatic generated getters, setters, builders, and database mappings.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 * @see BaseEntity
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message extends BaseEntity {
    /**
     * The message variable represents the content of a message.
     * Messages are entities in the system that can be created, modified, and deleted.
     * <p>
     * This variable is declared in the Message class, which extends the BaseEntity class.
     * The BaseEntity class provides common fields such as id, createdAt, and updatedAt.
     *
     * @see Message
     * @see BaseEntity
     */
    private String message;

    /**
     * The `user` variable represents the user associated with a message.
     * It is declared in the {@link Message} class and is annotated with {@link ManyToOne},
     * indicating that it represents a many-to-one relationship with the {@link User} entity.
     * The {@link JoinColumn} annotation specifies the name of the foreign key column in the database table,
     * which is "user_id" in this case.
     * <p>
     * Example usage:
     * <pre>{@code
     * User user = message.getUser();
     * }</pre>
     *
     * @see Message
     * @see User
     * @see ManyToOne
     * @see JoinColumn
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The rental variable represents a rental associated with a message.
     * It is declared in the {@link Message} class and is annotated with {@link ManyToOne},
     * indicating that it represents a many-to-one relationship with the {@link Rental} entity.
     * The {@link JoinColumn} annotation specifies the name of the foreign key column in the database table,
     * which is "rental_id" in this case.
     * <p>
     * <p>
     * Example usage:
     * <pre>{@code
     * Rental rental = message.getRental();
     * }</pre>
     *
     * @see Message
     * @see Rental
     * @see ManyToOne
     * @see JoinColumn
     */
    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

}
