package com.pop.codelab.chatopbackend.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDTO {

    private Integer id;

    private LocalDateTime createAt;

    private LocalDateTime updatedAt;

}

