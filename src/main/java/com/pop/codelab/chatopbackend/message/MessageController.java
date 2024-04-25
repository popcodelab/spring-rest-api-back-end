package com.pop.codelab.chatopbackend.message;

import com.pop.codelab.chatopbackend.controllers.CrudController;
import com.pop.codelab.chatopbackend.rental.Rental;
import com.pop.codelab.chatopbackend.rental.RentalDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController extends CrudController<MessageDto> {

    @Autowired
    private ModelMapper modelMapper;

    public MessageController(MessageService messageService) {
        super(messageService);
    }

//    @Override
//    @PostMapping("/")
//    public ResponseEntity<?> save(@RequestBody MessageDto message) {
//        ResponseEntity<?> savedEntity = super.save(message);
//        return new ResponseEntity<>(savedEntity.getBody(), HttpStatus.CREATED);
//    }

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






