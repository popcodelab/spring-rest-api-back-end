package com.pop.codelab.chatopbackend.message;

import com.pop.codelab.chatopbackend.controllers.CrudController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController extends CrudController<MessageDto> {

    public MessageController(MessageService messageService) {
        super(messageService);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody MessageDto messageDto) {
        ResponseEntity<?> savedResponseEntity = super.save(messageDto);
        MessageDto savedMessageDto = (MessageDto) savedResponseEntity.getBody();
        MessageCreationResponse messageCreationDto = MessageCreationResponse.builder()
                .message(savedMessageDto.getMessage())
                .build();
        return new ResponseEntity<>(messageCreationDto, HttpStatus.CREATED);
    }
}






