package com.pop.codelab.chatopbackend.user;

import com.pop.codelab.chatopbackend.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The User class represents a user entity in the system.
 * <p>
 * User is an entity that implements the UserDetails interface,
 * which provides the required methods for user authentication and authorization.
 * <p>
 * The User class inherits from the BaseEntity class, which provides common fields such as id, createdAt, and updatedAt.
 * <p>
 * The User class is mapped to the "users" table in the database, using the @Entity and @Table annotations.
 * The class also includes annotations such as @Data, @Builder, @NoArgsConstructor, and @AllArgsConstructor
 * for automatic generation of getters, setters, builders, and constructors.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    /**
     * The name variable represents the name of a user.
     * <p>
     * This variable is a private field of the User class, which represents a user entity in the system.
     * It is of type String and is used to store the name of the user.
     * <p>
     * Example usage:
     * <pre>{@code
     * String name = user.getName();
     * }</pre>
     */
    private String name;
    /**
     * The email variable represents the email address of a user.
     * <p>
     * This variable is a private field of the User class, which represents a user entity in the system.
     * It is of type String and is used to store the email address of the user.
     * <p>
     * Example usage:
     * <pre>{@code
     * String email = user.getEmail();
     * }</pre>
     */
    private String email;

    /**
     * The password variable represents the password of a user.
     * <p>
     * This variable is a private field of the User class, which represents a user entity in the system.
     * It is of type String and is used to store the password of the user.
     * <p>
     * Example usage:
     * <pre>{@code
     * String password = user.getPassword();
     * }</pre>
     */
    private String password;

    /**
     * The role variable represents the role assigned to a user.
     * <p>
     * This variable is a private field of the User class, which represents a user entity in the system.
     * It is of type Role and is used to store the role assigned to the user.
     * <p>
     * Example usage:
     * <pre>{@code
     * Role role = user.getRole();
     * }</pre>
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Retrieves the authorities associated with the user.
     *
     * @return The collection of authorities as GrantedAuthority objects.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (null != role) {
            return role.getPrivileges();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves the username associated with the user.
     *
     * @return The username as a String.
     */
    @Override
    public String getUsername() {
        return email;
    }

    // These following properties indicate if the user is still valid
    // Important: should always return true - common mistake

    /**
     * Determines whether the user account is non-expired.
     *
     * @return {@code true} if the user account is non-expired, {@code false} otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Determines whether the user account is locked.
     *
     * @return {@code true} if the user account is not locked, {@code false} otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Determines whether the user's credentials are non-expired.
     *
     * @return {@code true} if the user's credentials are non-expired, {@code false} otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Checks if the user account is enabled.
     *
     * @return {@code true} if the user account is enabled, {@code false} otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
