package com.pop.codelab.chatopbackend.user;

import com.pop.codelab.chatopbackend.controllers.dto.BaseDTO;
import com.pop.codelab.chatopbackend.message.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDto extends BaseDTO {
    private String name;
    private String email;
    private String password;
    private Role role;
    private List<Message> messages;
}
