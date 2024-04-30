package com.pop.codelab.chatopbackend.business.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pop.codelab.chatopbackend.business.user.Role;
import com.pop.codelab.chatopbackend.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The UserDto class represents a Data Transfer Object (DTO) for User data.
 * It extends the BaseDto class, which provides common properties such as id, createAt, and updatedAt.
 * The UserDto class includes the user's name, email, and role.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {
    /**
     * The name variable represents the name of a user.
     * <p></p>
     * This variable is defined in the UserDto class, which is a Data Transfer Object (DTO) that extends the BaseDto class.
     * The UserDto class includes additional properties such as email and role.
     * <p></p>
     * The name variable is of type String and is used to store the name of the user.
     */
    private String name;
    /**
     * The email variable represents the email address of a user.
     * <p></p>
     * This variable is defined in the UserDto class, which is a Data Transfer Object (DTO).
     * The UserDto class extends the BaseDto class and includes additional properties such as name and role.
     * <p></p>
     * The email variable is of type String and is used to store the email address of the user.
     */
    private String email;
    /**
     * The role variable represents the role of a user.
     * <p></p>
     * This variable is defined in the UserDto class, which is a Data Transfer Object (DTO) that extends the BaseDto class.
     * The UserDto class includes additional properties such as name and email.
     * <p></p>
     * The role variable is of type Role and is used to store the role of the user.
     * It is marked with the @JsonIgnore annotation, indicating that it should be ignored during serialization and deserialization.
     * <p></p>
     * The Role enum class defines different roles that a user can have, such as USER, ADMIN, and MANAGER.
     * Each role has a set of permissions associated with it.
     * <p></p>
     * The Role class provides a getPermissions() method that returns the set of permissions associated with the role.
     * <p></p>
     * The Permission enum class defines different permissions that can be assigned to a role, such as ADMIN_READ, ADMIN_UPDATE, etc.
     * Each permission has a corresponding string representation.
     * <p></p>
     * The getPrivileges() method in the Role class returns a list of SimpleGrantedAuthority objects based on the role's permissions.
     * These authorities can be used for authorization purposes.
     *
     * @see UserDto
     * @see BaseDto
     * @see Role
     * @see Permission
     */
    @JsonIgnore
    private Role role;

}
