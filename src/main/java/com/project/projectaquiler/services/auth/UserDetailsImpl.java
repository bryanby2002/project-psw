package com.project.projectaquiler.services.auth;

import com.project.projectaquiler.dto.request.AuthResponse;
import com.project.projectaquiler.dto.request.UserRequest;
import com.project.projectaquiler.persistence.entities.RoleEntity;
import com.project.projectaquiler.persistence.entities.UserEntity;
import com.project.projectaquiler.persistence.repositories.RoleRepository;
import com.project.projectaquiler.persistence.repositories.UserRepository;
import com.project.projectaquiler.utils.JwtUtils;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
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
  private final RoleRepository roleRepository;
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
  public AuthResponse createUser(UserRequest createRoleRequest) {
    
    String username = createRoleRequest.userName();
    String password = createRoleRequest.password();
    Integer dni = createRoleRequest.dni();
    String email = createRoleRequest.email();
    String name = createRoleRequest.name();
    String lastName = createRoleRequest.lastName();
    Integer phone = createRoleRequest.phone();
    Integer age = createRoleRequest.age();
    String gender = createRoleRequest.gender();
    String address = createRoleRequest.address();
    List<String> rolesRequest = createRoleRequest.roleRequest().roleListName();

    Set<RoleEntity> roleEntityList = roleRepository
      .findRoleEntitiesByRoleEnumIn(rolesRequest)
      .stream()
      .collect(Collectors.toSet());

    if (roleEntityList.isEmpty()) {
      throw new IllegalArgumentException("The roles specified does not exist.");
    }

    UserEntity userEntity = UserEntity
      .builder()
      .userName(username)
      .password(passwordEncoder.encode(password))
      .dni(dni)
      .email(email)
      .name(name)
      .lastName(lastName)
      .phone(phone)
      .age(age)
      .gender(gender)
      .address(address)
      .roles(roleEntityList)
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

    AuthResponse authResponse = new AuthResponse(
      username,
      "User created successfully",
      accessToken,
      true
    );
    return authResponse;
  }

  
}
