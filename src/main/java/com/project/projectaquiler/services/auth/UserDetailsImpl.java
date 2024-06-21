package com.project.projectaquiler.services.auth;

import com.project.projectaquiler.dto.request.AuthLoginRequest;
import com.project.projectaquiler.dto.request.AuthResponse;
import com.project.projectaquiler.dto.request.UserRequest;
import com.project.projectaquiler.persistence.entities.PermissionEntity;
import com.project.projectaquiler.persistence.entities.RoleEntity;
import com.project.projectaquiler.persistence.entities.RoleEnum;
import com.project.projectaquiler.persistence.entities.UserEntity;
import com.project.projectaquiler.persistence.repositories.UserRepository;
import com.project.projectaquiler.utils.JwtUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsImpl implements UserDetailsService {

  private final UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private JwtUtils jwtUtils;

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

  //create user
  public AuthResponse createUser(UserRequest request) {

    // PERMISSSION DEFAULT
    final PermissionEntity READ_PERMISSION = PermissionEntity
      .builder()
      .name("READ")
      .build();
    final PermissionEntity BOOKING_PERMISSION = PermissionEntity
      .builder()
      .name("BOOKING")
      .build();
    // ROLE DEFAULT
    final RoleEntity USER_ROLE = RoleEntity
      .builder()
      .role(RoleEnum.USER)
      .permissions(Set.of(READ_PERMISSION, BOOKING_PERMISSION))
      .build();
    
    //seteo de atributos al usuario entity
    UserEntity userEntity = UserEntity
      .builder()
      .userName(request.userName())
      .password(passwordEncoder.encode(request.password()))
      .dni(request.dni())
      .email(request.email())
      .name(request.name())
      .lastName(request.lastName())
      .phone(request.phone())
      .age(request.age())
      .gender(request.gender())
      .address(request.address())
      .roles(Set.of(USER_ROLE))
      .isEnable(true)
      .accountNoLocked(true)
      .accountNoExpired(true)
      .credentialNoExpired(true)
      .build();

    UserEntity userSaved = userRepository.save(userEntity);

    ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

    userSaved
      .getRoles()
      .forEach(role ->
        authorities.add(
          new SimpleGrantedAuthority("ROLE_".concat(role.getRole().name()))
        )
      );

    userSaved
      .getRoles()
      .stream()
      .flatMap(role -> role.getPermissions().stream())
      .forEach(permission ->
        authorities.add(new SimpleGrantedAuthority(permission.getName()))
      );
    Authentication authentication = new UsernamePasswordAuthenticationToken(
      userSaved,
      null,
      authorities
    );

    String accessToken = jwtUtils.createToken(authentication);

      return new AuthResponse(
      request.userName(),
      "User created successfully",
      accessToken,
      true
    );
  }

  // user login
  public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
    String username = authLoginRequest.username();
    String password = authLoginRequest.password();

    Authentication authentication = this.authenticate(username, password);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String accessToken = jwtUtils.createToken(authentication);
      return new AuthResponse(
      username,
      "User loged succesfully",
      accessToken,
      true
    );
  }

  // user autheticate
  public Authentication authenticate(String username, String password) {
    UserDetails userDetails = this.loadUserByUsername(username);

    if (userDetails == null) {
      throw new BadCredentialsException(
              "Invalid username or password"
      );
    }

    if (!passwordEncoder.matches(password, userDetails.getPassword())) {
      throw new BadCredentialsException("Incorrect Password");
    }

    return new UsernamePasswordAuthenticationToken(
      username,
      password,
      userDetails.getAuthorities()
    );
  }
}
