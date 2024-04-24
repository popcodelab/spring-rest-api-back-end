package com.pop.codelab.chatopbackend.message;


import com.pop.codelab.chatopbackend.entity.BaseEntity;
import com.pop.codelab.chatopbackend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message extends BaseEntity {

    private String message;

//    @NotNull
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
