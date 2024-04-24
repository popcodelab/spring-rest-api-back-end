package com.pop.codelab.chatopbackend.message.service.impl;

import com.pop.codelab.chatopbackend.controllers.ResourceController;
import com.pop.codelab.chatopbackend.message.Message;
import com.pop.codelab.chatopbackend.message.MessageRepository;
import com.pop.codelab.chatopbackend.message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    public MessageServiceImpl(MessageRepository rentalRepository) {
        this.messageRepository = rentalRepository;
    }

    @Override
    public List<Message> getAllMessages() {
        logger.info("getAllMessages in....");
        return this.messageRepository.findAll();
    }
}
