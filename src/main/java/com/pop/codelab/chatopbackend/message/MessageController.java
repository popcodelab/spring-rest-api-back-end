package com.pop.codelab.chatopbackend.message;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageRepository repository;
    @GetMapping("/")
   // @ApiOperation(value = "List all")
    List<Message> all() {
        return repository.findAll();
    }

}
