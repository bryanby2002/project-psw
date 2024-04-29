package com.project.projectaquiler.services;

import com.project.projectaquiler.dto.UserRequest;
import com.project.projectaquiler.persistence.entities.PermissionEntity;
import com.project.projectaquiler.persistence.entities.RoleEntity;
import com.project.projectaquiler.persistence.entities.RoleEnum;
import com.project.projectaquiler.persistence.entities.UserEntity;
import com.project.projectaquiler.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

            UserEntity userEntity = UserEntity.builder()
                    .name(userRequest.name())
                    .password(new BCryptPasswordEncoder().encode(userRequest.password()))
                    .email(userRequest.email())
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
            userRepository.save(userEntity);
            return userEntity;
        } catch (DataIntegrityViolationException ex){
            log.error("Error saving user or email duplicated" , ex);
            return null;
        }
    }
}
