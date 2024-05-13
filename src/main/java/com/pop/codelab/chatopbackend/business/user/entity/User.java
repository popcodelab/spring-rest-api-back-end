package com.pop.codelab.chatopbackend.business.user;

import com.pop.codelab.chatopbackend.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The User class represents a user entity in the system.
 *
 * @author Pignon Pierre-Olivier
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User extends BaseEntity  {



    /**
     * The name variable represents the name of a user.
     */
    private String name;
    /**
     * The email variable represents the email address of a user.
     */
    private String email;

    /**
     * The password variable represents the password of a user.
     */
    private String password;



}
