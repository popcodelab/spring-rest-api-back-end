package com.pop.codelab.chatopbackend.user;

import com.pop.codelab.chatopbackend.controllers.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDTO {

    private String name;
    private String email;
    private Role role;

}
