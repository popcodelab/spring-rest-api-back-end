package com.pop.codelab.chatopbackend.message;

import com.pop.codelab.chatopbackend.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import static com.pop.codelab.chatopbackend.message.MessageMapper.INSTANCE;

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
        //List<MessageDto> messageDTOList = new ArrayList<>();
        //messageRepository.findAll().forEach(message -> messageDTOList.add(INSTANCE.messageToDto(message)));
        List<Message> messages = messageRepository.findAll();


        logger.debug("Message(s) count : {}", messages.size());
        return messages.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<MessageDto> findById(Long id) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        logger.debug("Dto retrieved : {} ", messageOptional.toString());
        return messageOptional.map(this::convertToDto);
    }

    @Override
    public MessageDto save(MessageDto messageDto) {
        logger.debug("Save {} dto", messageDto);
        //Message message = INSTANCE.dtoToMessage(messageDto);
        Message message = modelMapper.map(messageDto, Message.class);
        var result = messageRepository.save(message);
        messageDto = modelMapper.map(message, MessageDto.class);
        return messageDto;
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
        MessageDto messageDto = modelMapper.map(message, MessageDto.class);
        return messageDto;
    }



}
