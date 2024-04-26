package com.pop.codelab.chatopbackend.rental;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pop.codelab.chatopbackend.controllers.dto.BaseDto;
import com.pop.codelab.chatopbackend.user.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OneRentalResponseDto extends BaseDto {

    private String name;

    private BigDecimal surface;

    private BigDecimal price;

    private String picture;

    private String description;

    public Long owner_id;

}
