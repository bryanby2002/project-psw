package com.project.projectaquiler.services;

import com.project.projectaquiler.dto.UserRequest;
import com.project.projectaquiler.persistence.entities.*;
import com.project.projectaquiler.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserEntity saveUserEntity(UserRequest userRequest) {

        try {
            // PERMISSSION DEFAULT
            final PermissionEntity READ_PERMISSION = PermissionEntity.builder().name("READ").build();
            final PermissionEntity BOOKING_PERMISSION = PermissionEntity.builder().name("BOOKING").build();
            // ROLE DEFAULT
            final RoleEntity USER_ROLE = RoleEntity.builder()
                    .role(RoleEnum.ROLE_USER)
                    .permissions(Set.of(READ_PERMISSION, BOOKING_PERMISSION))
                    .build();

            UserRequest.validate(userRequest);

            UserEntity userEntity = UserEntity.builder()
                    .userName(userRequest.userName())
                    .name(userRequest.name())
                    .password(userRequest.password())
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
        } catch (DataIntegrityViolationException ex){
            log.error("Error saving user or email duplicated" , ex);
            return null;
        }
    }

    public Optional<UserEntity> findUserByUsername(String usermane) {
        return userRepository.findByUserName(usermane);
    }

    public List<BookingEntity> findBookingsForUser(String username){

        return findUserByUsername(username).stream()
                .flatMap(user -> user.getBookingEntityList().stream())
                .toList();
    }
}
