package com.project.projectaquiler.services;

import com.project.projectaquiler.dto.details.BookingDetails;
import com.project.projectaquiler.dto.details.UserEntityDetails;
import com.project.projectaquiler.dto.request.UserRequest;
import com.project.projectaquiler.persistence.entities.*;
import com.project.projectaquiler.persistence.repositories.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  //Metodo para registrar un nuevo usuario, valida los atributos desde una clase dto
  public UserEntity saveUserEntity(UserRequest userRequest) {
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
      .role(RoleEnum.ROLE_USER)
      .permissions(Set.of(READ_PERMISSION, BOOKING_PERMISSION))
      .build();

    UserRequest.validate(userRequest);

    UserEntity userEntity = UserEntity
      .builder()
      .userName(userRequest.userName())
      .name(userRequest.name())
      .password(new BCryptPasswordEncoder().encode(userRequest.password()))
      .email(userRequest.email())
      .dni(userRequest.dni())
      .name(userRequest.name())
      .lastName(userRequest.lastName())
      .phone(userRequest.phone())
      .age(userRequest.age())
      .gender(userRequest.gender())
      .address(userRequest.address())
      .roles(Set.of(USER_ROLE))
      .isEnable(true)
      .accountNoExpired(true)
      .accountNoLocked(true)
      .credentialNoExpired(true)
      .build();
    return userRepository.save(userEntity);
  }

  // metodo que retorna
  public Optional<UserEntity> findUserByUsername(String usermane) {
    return userRepository.findByUserName(usermane);
  }

  // Metodo para listar las reservas de un usuario
  public List<BookingDetails> findBookingsForUser(String username) {
    Optional<UserEntity> user = findUserByUsername(username);
    if (user.isPresent()) {
      List<BookingEntity> bookingEntities = user.get().getBookingEntityList();
      return bookingEntities
        .stream()
        .map(bookingEntity ->
          new BookingDetails(
            bookingEntity.getUser().getId(),
            bookingEntity.getVehicle().getImageUrl(),
            bookingEntity.getVehicle().getPrice(),
            bookingEntity.getVehicle().getBrand(),
            bookingEntity.getStartDate(),
            bookingEntity.getEndDate()
          )
        )
        .toList();
    } else {
      return Collections.emptyList();
    }
  }

  // Este metodo lista todos los usuarios en una clase details
  public List<UserEntityDetails> findAllUsers() {
    return StreamSupport
      .stream(userRepository.findAll().spliterator(), true)
      .map(user ->
        new UserEntityDetails(
          user.getId(),
          user.getUserName(),
          user.getName(),
          user.getLastName(),
          user.getEmail(),
          user.getPassword(),
          user
            .getRoles()
            .stream()
            .map(role -> role.getRole().name())
            .collect(Collectors.toSet()),
          user.getDni(),
          user.getPhone(),
          user.getAddress()
        )
      )
      .toList();
  }
}
