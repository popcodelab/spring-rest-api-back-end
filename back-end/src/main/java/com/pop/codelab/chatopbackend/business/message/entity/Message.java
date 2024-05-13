package com.pop.codelab.chatopbackend.business.message.entity;


import com.pop.codelab.chatopbackend.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

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
@Data
@Entity
@Table(name = "messages")
public class Message extends BaseEntity {
    /**
     * The message variable represents the content of a message.
     * Messages are entities in the system that can be created, modified, and deleted.
     *
     * @see Message
     * @see BaseEntity
     */
    @Column(length = 3000, nullable = false)
    private String message;

    /**
     * The rental_id variable represents the ID of a rental in the system.
     * It is used to uniquely identify a rental entity and perform operations related to that rental.
     */
//    @Column(nullable = false, name = "rental_id")
//    private Long rentalId;
    @Column(nullable = false)
    private Long rental_id;

    /**
     * The user_id variable represents the ID of a user in the system.
     * It is used to uniquely identify a user entity and perform operations related to that user.
     */
    @Column(nullable = false)
    private Long user_id;

}
