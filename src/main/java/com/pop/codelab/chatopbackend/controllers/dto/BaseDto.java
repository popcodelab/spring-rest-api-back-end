package com.pop.codelab.chatopbackend.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDto {

    private Long id;

    @JsonProperty("create_at")
    private LocalDate createAt;

    @JsonProperty("update_at")
    private LocalDate updatedAt;

}

