package com.pop.codelab.chatopbackend.dto.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {
    private String message;
}
