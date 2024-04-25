package com.pop.codelab.chatopbackend.rental;

import com.pop.codelab.chatopbackend.entity.BaseEntity;
import com.pop.codelab.chatopbackend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity // JPA entity
@Table(name="rentals")
public class Rental extends BaseEntity {

    private String name;

    @Column(nullable = false)
    private BigDecimal surface;

    @Column(nullable = false)
    private BigDecimal price;

    private String picture;

    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    public User user;
}
