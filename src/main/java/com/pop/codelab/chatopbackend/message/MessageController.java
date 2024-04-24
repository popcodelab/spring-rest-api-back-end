package com.pop.codelab.chatopbackend.message;

import com.pop.codelab.chatopbackend.controllers.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController extends CrudController<MessageDto> {
    public MessageController(MessageService messageService) {

        super(messageService);
    }


}
