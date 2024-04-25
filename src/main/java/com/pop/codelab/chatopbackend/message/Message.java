package com.pop.codelab.chatopbackend.message;


import com.pop.codelab.chatopbackend.entity.BaseEntity;
import com.pop.codelab.chatopbackend.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
