package com.pop.codelab.chatopbackend.user;

//import com.pop.codelab.chatopbackend.message.Message;
//import com.pop.codelab.chatopbackend.rental.Rental;

import com.pop.codelab.chatopbackend.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

  private String name;

  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

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
