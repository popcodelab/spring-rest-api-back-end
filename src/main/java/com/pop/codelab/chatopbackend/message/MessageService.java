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


@Service
@RequiredArgsConstructor
public class MessageService implements CrudService<MessageDto> {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final MessageRepository messageRepository;

    @Autowired
    private ModelMapper modelMapper;


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

    @Override
    public Optional<MessageDto> findById(Long id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isEmpty()) {
            throw new ResourceNotFoundException("No message found with Id : "+ id);
        }
        logger.debug("MessageDto retrieved : {} ", optionalMessage);
        return optionalMessage.map(this::convertToDto);
    }

    @Override
    public MessageDto save(MessageDto messageDto) {
        Message message = modelMapper.map(messageDto, Message.class);
        Message createdMessage = messageRepository.save(message);
        logger.debug("Message : {} has been saved.", createdMessage);
        return   modelMapper.map(createdMessage, MessageDto.class);
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public MessageDto update(Long id, MessageDto messageDto) {
        Message savedMessage = messageRepository.findById(id).orElseThrow();
        Message messageToUpdate = modelMapper.map(messageDto, Message.class);
        savedMessage.setMessage(messageToUpdate.getMessage());
        var updatedMessage = messageRepository.save(savedMessage);
        return modelMapper.map(updatedMessage, MessageDto.class);
    }

    private MessageDto convertToDto(Message message) {
        return modelMapper.map(message, MessageDto.class);
    }



}
