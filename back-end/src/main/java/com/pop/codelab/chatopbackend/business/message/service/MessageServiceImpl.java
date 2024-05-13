package com.pop.codelab.chatopbackend.business.message.service;

import com.pop.codelab.chatopbackend.business.message.MessageRepository;
import com.pop.codelab.chatopbackend.business.message.dto.requests.MessageToSendDto;
import com.pop.codelab.chatopbackend.business.message.entity.Message;
import com.pop.codelab.chatopbackend.common.responses.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The MessageServiceImpl class provides CRUD (Create, Read, Update, Delete) operations for
 * working with MessageRequest objects.
 *
 * @author Pignon Pierre-Olivier
 * @version 2.0
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class MessageServiceImpl implements MessageService {

    /**
     * The messageRepository variable refers to an instance of the MessageRepository interface.
     * It is used in the MessageServiceImpl class to perform CRUD operations on Message objects.
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
     * Creates a new message using the provided MessageToSendDto.
     *
     * @param messageDto The MessageToSendDto object containing the message, user ID, and rental ID.
     * @return The created MessageDto object.
     */
    @Override
    public MessageDto create(final MessageToSendDto messageDto) {
        Message message = this.modelMapper.map(messageDto, Message.class);
        this.messageRepository.save(message);
        log.debug("Message : {} has been sent.", message.getMessage());
        return MessageDto
                .builder()
                .message("Message send with success")
                .build();
    }
}
