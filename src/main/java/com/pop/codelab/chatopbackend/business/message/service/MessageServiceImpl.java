package com.pop.codelab.chatopbackend.business.message;

import com.pop.codelab.chatopbackend.business.rental.Rental;
import com.pop.codelab.chatopbackend.business.rental.RentalService;
import com.pop.codelab.chatopbackend.business.rental.dto.requests.RentalCreationDto;
import com.pop.codelab.chatopbackend.business.user.User;
import com.pop.codelab.chatopbackend.business.user.service.UserServiceImpl;
import com.pop.codelab.chatopbackend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
 * working with MessageRequest objects.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class MessageService {



    private final UserServiceImpl userService;
    private final RentalService rentalService;

    /**
     * The messageRepository variable refers to an instance of the MessageRepository interface.
     * It is used in the MessageService class to perform CRUD operations on Message objects.
     * <p>
     * The MessageRepository interface extends the JpaRepository interface, which provides basic CRUD operations for the Message entity.
     *
     * @see JpaRepository
     * @see Message
     */
    private final MessageRepository messageRepository;

    /**
     * The modelMapper variable is an instance of the ModelMapper class.
     * It is used for mapping objects between different models.
     * Here between Dto and Entity and vice versa
     *
     * @see ModelMapper
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Retrieves all messages.
     *
     * @return a list of MessageRequest objects representing all the messages
     * @throws ResourceNotFoundException if no messages are found
     */
    @Override
    public List<MessageRequest> findAll() {
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
     * @return an Optional object containing the retrieved MessageRequest if found, otherwise an empty Optional object
     * @throws ResourceNotFoundException if no message is found with the given ID
     */
    @Override
    public Optional<MessageRequest> findById(final Long id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isEmpty()) {
            throw new ResourceNotFoundException("No message found with Id : " + id);
        }
        logger.debug("MessageRequest retrieved : {} ", optionalMessage);
        return optionalMessage.map(this::convertToDto);
    }

    /**
     * Saves a message DTO object.
     *
     * @param messageDto the DTO object to be saved
     * @return the saved message DTO object
     */
    @Override
    public MessageRequest save(final MessageRequest messageDto) {
        Optional<responses.UserDto> senderDto = userService.findById(messageDto.getUser_id());
        if (senderDto.isEmpty()) {
            String errorMessage = "No sender found with Id : " + messageDto.getUser_id();
            logger.warn(errorMessage);
            throw new ResourceNotFoundException(errorMessage);
        }
        Optional<RentalCreationDto> rentalDto = null; //rentalService.findById(messageDto.getRental_id());
        if (rentalDto.isEmpty()) {
            String errorMessage = "No rental found with Id : " + messageDto.getRental_id();
            logger.warn(errorMessage);
            throw new ResourceNotFoundException(errorMessage);
        }
        Message message = modelMapper.map(messageDto, Message.class);
        message.setUser(modelMapper.map(senderDto.get(), User.class));
        message.setRental(modelMapper.map(rentalDto.get(), Rental.class));

        Message createdMessage = messageRepository.save(message);
        logger.debug("Message : {} has been saved.", createdMessage);
        return modelMapper.map(createdMessage, MessageRequest.class);
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
    public MessageRequest update(
            final Long id,
            final MessageRequest messageDto) {
        Message savedMessage = messageRepository.findById(id).orElseThrow();
        Message messageToUpdate = modelMapper.map(messageDto, Message.class);
        savedMessage.setMessage(messageToUpdate.getMessage());
        var updatedMessage = messageRepository.save(savedMessage);
        return modelMapper.map(updatedMessage, MessageRequest.class);
    }

    /**
     * Converts a Message entity to a MessageRequest object.
     *
     * @param message the Message entity to be converted
     * @return a MessageRequest object representing the converted entity
     */
    private MessageRequest convertToDto(Message message) {
        return modelMapper.map(message, MessageRequest.class);
    }


}
