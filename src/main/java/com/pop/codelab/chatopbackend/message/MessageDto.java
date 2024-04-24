package com.pop.codelab.chatopbackend.message;

import com.pop.codelab.chatopbackend.controllers.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto extends BaseDTO {

    private String message;
}
