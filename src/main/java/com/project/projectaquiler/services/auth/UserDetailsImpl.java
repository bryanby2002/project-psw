package com.project.projectaquiler.services.auth;

import com.project.projectaquiler.persistence.entities.UserEntity;
import com.project.projectaquiler.persistence.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    UserEntity userEntity = userRepository
      .findByUserName(username)
      .orElseThrow(() ->
        new UsernameNotFoundException(
          "User not found with username: " + username
        )
      );

    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    userEntity
      .getRoles()
      .forEach(role ->
        authorities.add(
          new SimpleGrantedAuthority("ROLE_".concat(role.getRole().name()))
        )
      );

    userEntity
      .getRoles()
      .stream()
      .flatMap(role -> role.getPermissions().stream())
      .forEach(permission ->
        authorities.add(new SimpleGrantedAuthority(permission.getName()))
      );

    return new User(
      userEntity.getUserName(),
      userEntity.getPassword(),
      userEntity.isEnable(),
      userEntity.isAccountNoExpired(),
      userEntity.isCredentialNoExpired(),
      userEntity.isAccountNoLocked(),
      authorities
 );
  }
}
