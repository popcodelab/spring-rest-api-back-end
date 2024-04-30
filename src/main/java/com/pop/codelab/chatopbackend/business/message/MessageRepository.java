package com.pop.codelab.chatopbackend.business.message;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The MessageRepository interface extends the JpaRepository interface,
 * which provides basic CRUD operations for the Message entity.
 * <p></p>
 * The MessageRepository interface does not define any additional methods,
 * as it inherits all the methods from the JpaRepository interface.
 * However, it can be used to define custom queries or methods specific to the Message entity if needed.
 * <p></p>
 * Example usage:
 * <pre>{@code
 * List<Message> messages = messageRepository.findAll();
 * Optional<Message> optionalMessage = messageRepository.findById(id);
 * Message createdMessage = messageRepository.save(message);
 * messageRepository.deleteById(id);
 * }</pre>
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 * @see JpaRepository
 * @see Message
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}
