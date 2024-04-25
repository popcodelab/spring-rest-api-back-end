package com.pop.codelab.chatopbackend.message;

import com.pop.codelab.chatopbackend.controllers.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController extends CrudController<MessageDto> {

    @Autowired
    private MessageService messageService;
    public MessageController(MessageService messageService) {

        super(messageService);
    }

}
