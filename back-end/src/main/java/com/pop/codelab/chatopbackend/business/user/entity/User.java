package com.pop.codelab.chatopbackend.business.user.entity;

import com.pop.codelab.chatopbackend.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The User class represents a user entity in the system.
 *
 * @author Pignon Pierre-Olivier
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    /**
     * The name variable represents the name of a user.
     */
    @Column(nullable = false, length = 64)
    private String name;
    /**
     * The email variable represents the email address of a user.
     */
    @Column(nullable = false, unique = true, length = 248)
    private String email;

    /**
     * The password variable represents the password of a user.
     */
    @Column(nullable = false, length = 64)
    private String password;


}
