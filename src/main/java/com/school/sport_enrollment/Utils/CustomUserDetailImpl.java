package com.school.sport_enrollment.Utils;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.sport_enrollment.Model.User;

public class CustomUserDetailImpl implements UserDetails {

  private Long id;
  private String email;
  @JsonIgnore
  private String password;

  public CustomUserDetailImpl(Long id, String email, String password) {
    this.id = id;
    this.email = email;
    this.password = password;
  }

  public static CustomUserDetailImpl build(User user) {
    // List<GrantedAuthority> authorities = user.getRoles().stream()
    // .map(role -> new SimpleGrantedAuthority(role.getName().name()))
    // .collect(Collectors.toList());

    return new CustomUserDetailImpl(
        user.getId(),
        user.getEmail(),
        user.getPassword());
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // If you don't have roles, just return an empty list
    return Collections.emptyList();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return null;
  }

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
