package com.pop.codelab.chatopbackend.message;

import com.pop.codelab.chatopbackend.controllers.dto.BaseDto;
import com.pop.codelab.chatopbackend.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto extends BaseDto {

    private String message;

    private UserDto user;
}
