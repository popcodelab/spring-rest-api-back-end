package com.pop.codelab.chatopbackend.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pop.codelab.chatopbackend.controllers.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {

    private String name;
    private String email;

    @JsonIgnore
    private Role role;

}
