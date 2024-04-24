package com.pop.codelab.chatopbackend.message;

import com.pop.codelab.chatopbackend.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.pop.codelab.chatopbackend.message.MessageMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class MessageService implements CrudService<MessageDto> {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final MessageRepository messageRepository;


    @Override
    public List<MessageDto> findAll() {
        logger.info("Gathering all messages...");
        List<MessageDto> messageDTOList = new ArrayList<>();
        messageRepository.findAll().forEach(message -> messageDTOList.add(INSTANCE.messageToDto(message)));
        logger.debug("Message(s) count : {}", messageDTOList.size());
        return messageDTOList;
    }

    @Override
    public Optional<MessageDto> findById(Long id) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        logger.debug("Dto retrieved : {} ", messageOptional.toString());
        return messageOptional.map(INSTANCE::messageToDto);
    }

    @Override
    public MessageDto save(MessageDto messageDto) {
        logger.debug("Save {} dto", messageDto);
        Message message = INSTANCE.dtoToMessage(messageDto);
        return INSTANCE.messageToDto(messageRepository.save(message));
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public MessageDto update(Long id, MessageDto messageDto) {
        Message savedMessage = messageRepository.findById(id).orElseThrow();
        Message messageToUpdate = INSTANCE.dtoToMessage(messageDto);
        savedMessage.setMessage(messageToUpdate.getMessage());
        return INSTANCE.messageToDto(messageRepository.save(savedMessage));
    }

}
