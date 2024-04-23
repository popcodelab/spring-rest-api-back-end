package com.pop.codelab.chatopbackend.user;

//import com.pop.codelab.chatopbackend.message.Message;
//import com.pop.codelab.chatopbackend.rental.Rental;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @Column(unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;

//  private String firstname;
//
//  private String lastname;

  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @CreationTimestamp
  @Column(name= "created_at")
  private LocalDateTime createAt;

  @UpdateTimestamp
  @Column(name= "updated_at")
  private LocalDateTime updatedAt;

//  @OneToMany(mappedBy = "user")
//  private List<Rental> rentals;
//
//  @OneToMany(mappedBy = "user")
//  private List<Message> messages;

//  @OneToMany(mappedBy = "user")
//  private List<Token> tokens;


//  @Override
//  public String getPassword() {
//    return password;
//  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    var test = 0;
    if (role!=null ){
      return role.getPrivileges();
    }
    else return new ArrayList<>();
  }





  @Override
  public String getUsername() {
    return email;
  }

  // These properties indicate if the user is still valid
  // Important: should always return true - common mistake
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
