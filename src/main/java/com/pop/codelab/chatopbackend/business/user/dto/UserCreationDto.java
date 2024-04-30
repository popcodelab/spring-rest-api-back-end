package com.pop.codelab.chatopbackend.business.user.dto;

import com.pop.codelab.chatopbackend.business.user.Role;
import com.pop.codelab.chatopbackend.dto.BaseDto;
import com.pop.codelab.chatopbackend.business.message.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The UserCreationDto class represents a data transfer object (DTO) for creating a user.
 * It extends the BaseDto class, which provides common properties such as id, createAt, and updatedAt.
 * The UserCreationDto class is annotated with @Data, @Builder, @NoArgsConstructor, and @AllArgsConstructor,
 * indicating that it is a DTO with automatic generated getters, setters, builders, and constructors.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 * @see BaseDto
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDto extends BaseDto {
    /**
     * The name variable represents the name of a user.
     * <p>
     * It is declared in the UserCreationDto class and is of type String.
     * The name is an attribute of the UserCreationDto class, which is a data transfer object (DTO)
     * used for creating a user.
     *
     * @see UserCreationDto
     */
    private String name;
    /**
     * The email variable represents the email of a user.
     * <p>
     * It is declared in the UserCreationDto class, which is a data transfer object (DTO)
     * used for creating a user.
     * The email is an attribute of the UserCreationDto class and is of type String.
     *
     * @see com.pop.codelab.chatopbackend.user.UserCreationDto
     * @see com.pop.codelab.chatopbackend.user.UserCreationDto#getEmail()
     * @see com.pop.codelab.chatopbackend.user.UserCreationDto#setEmail(String)
     */
    private String email;
    /**
     * The password variable represents the password of a user.
     * <p>
     * It is declared in the UserCreationDto class, which is a data transfer object (DTO) used for creating a user.
     * The password is an attribute of the UserCreationDto class and is of type String.
     *
     * @see UserCreationDto
     */
    private String password;
    /**
     * The role variable represents the role of a user.
     * Roles are used to determine the permissions and privileges of a user within the system.
     * <p>
     * It is declared in the UserCreationDto class, which is a data transfer object (DTO) used for creating a user.
     * The role is an attribute of the UserCreationDto class and is of type Role.
     *
     * @see UserCreationDto
     * @see Role
     */
    private Role role;
    /**
     * The messages variable represents a list of messages.
     * It is an attribute of the UserCreationDto class, which is a data transfer object (DTO) used for creating a user.
     *
     * @see UserCreationDto
     * @see Message
     */
    private List<Message> messages;
}
