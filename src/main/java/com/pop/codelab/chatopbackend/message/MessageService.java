package com.pop.codelab.chatopbackend.message;

import com.pop.codelab.chatopbackend.exception.ResourceNotFoundException;
import com.pop.codelab.chatopbackend.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The MessageService class provides CRUD (Create, Read, Update, Delete) operations for
 * working with MessageDto objects.
 * <p></p>
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class MessageService implements CrudService<MessageDto> {

    /**
     * The logger variable is used for logging messages and events in the MessageService class.
     * It is an instance of the Logger class from the SLF4J (Simple Logging Facade for Java) framework,
     * initialized with the logger name of the MessageService class.
     * <p></p>
     *
     * @see Logger
     * @see LoggerFactory
     */
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    /**
     * The messageRepository variable refers to an instance of the MessageRepository interface.
     * It is used in the MessageService class to perform CRUD operations on Message objects.
     * <p></p>
     * The MessageRepository interface extends the JpaRepository interface, which provides basic CRUD operations for the Message entity.
     * <p></p>
     *
     * @see JpaRepository
     * @see Message
     */
    private final MessageRepository messageRepository;

    /**
     * The modelMapper variable is an instance of the ModelMapper class.
     * It is used for mapping objects between different models.
     * Here between Dto and Entity and vice versa
     * <p></p>
     *
     * @see ModelMapper
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Retrieves all messages.
     *
     * @return a list of MessageDto objects representing all the messages
     * @throws ResourceNotFoundException if no messages are found
     */
    @Override
    public List<MessageDto> findAll() {
        logger.info("Gathering all messages...");
        List<Message> messages = messageRepository.findAll();
        if (messages.isEmpty()) {
            throw new ResourceNotFoundException("No message found");
        }
        logger.debug("Message(s) count : {}", messages.size());
        return messages.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * Retrieves a message by its ID.
     *
     * @param id the ID of the message to be retrieved
     * @return an Optional object containing the retrieved MessageDto if found, otherwise an empty Optional object
     * @throws ResourceNotFoundException if no message is found with the given ID
     */
    @Override
    public Optional<MessageDto> findById(final Long id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isEmpty()) {
            throw new ResourceNotFoundException("No message found with Id : " + id);
        }
        logger.debug("MessageDto retrieved : {} ", optionalMessage);
        return optionalMessage.map(this::convertToDto);
    }

    /**
     * Saves a message DTO object.
     *
     * @param messageDto the DTO object to be saved
     * @return the saved message DTO object
     */
    @Override
    public MessageDto save(final MessageDto messageDto) {
        Message message = modelMapper.map(messageDto, Message.class);
        Message createdMessage = messageRepository.save(message);
        logger.debug("Message : {} has been saved.", createdMessage);
        return modelMapper.map(createdMessage, MessageDto.class);
    }

    /**
     * Deletes a message by its ID.
     *
     * @param id the ID of the message to be deleted
     */
    @Override
    public void delete(final Long id) {
        messageRepository.deleteById(id);
    }

    /**
     * Updates a message with the specified ID.
     *
     * @param id         the ID of the message to be updated
     * @param messageDto the updated message object
     * @return the updated message DTO object
     */
    @Override
    public MessageDto update(
            final Long id,
            final MessageDto messageDto) {
        Message savedMessage = messageRepository.findById(id).orElseThrow();
        Message messageToUpdate = modelMapper.map(messageDto, Message.class);
        savedMessage.setMessage(messageToUpdate.getMessage());
        var updatedMessage = messageRepository.save(savedMessage);
        return modelMapper.map(updatedMessage, MessageDto.class);
    }

    /**
     * Converts a Message entity to a MessageDto object.
     *
     * @param message the Message entity to be converted
     * @return a MessageDto object representing the converted entity
     */
    private MessageDto convertToDto(Message message) {
        return modelMapper.map(message, MessageDto.class);
    }


}
