package com.pop.codelab.chatopbackend.rental;

import com.pop.codelab.chatopbackend.controllers.dto.BaseDTO;
import com.pop.codelab.chatopbackend.user.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
public class RentalDto extends BaseDTO {
    private String name;

    private BigDecimal surface;

    private BigDecimal price;

    private MultipartFile picture;

    private String description;

    public UserDto user;
}
