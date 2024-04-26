package com.pop.codelab.chatopbackend.rental.dto;

import com.pop.codelab.chatopbackend.controllers.dto.BaseDto;
import com.pop.codelab.chatopbackend.user.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
public class RentalDto extends BaseDto {
    private String name;

    private BigDecimal surface;

    private BigDecimal price;

    private MultipartFile picture;

    private String description;

    public UserDto user;
}
