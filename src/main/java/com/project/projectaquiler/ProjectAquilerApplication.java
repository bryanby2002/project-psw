package com.project.projectaquiler;

import com.project.projectaquiler.persistence.entities.PermissionEntity;
import com.project.projectaquiler.persistence.entities.RoleEntity;
import com.project.projectaquiler.persistence.entities.RoleEnum;
import com.project.projectaquiler.persistence.entities.UserEntity;
import com.project.projectaquiler.persistence.repositories.UserRepository;
import jakarta.servlet.MultipartConfigElement;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
public class ProjectAquilerApplication {

  // datos del admin
  @Value("${admin.password}")
  private String adminPassword;
  @Value("${admin.username}")
  private String adminUserName;
  @Value("${admin.email}")
  private String adminEmail;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public static void main(String[] args) {
    SpringApplication.run(ProjectAquilerApplication.class, args);
  }

  @Bean
  MultipartConfigElement multipartConfigElement() {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    factory.setMaxFileSize(DataSize.parse("256KB")); // Establece el tamaño máximo del archivo
    factory.setMaxRequestSize(DataSize.parse("512KB")); // Establece el tamaño máximo de la solicitud
    return factory.createMultipartConfig();
  }

  @Bean
  CommandLineRunner init(UserRepository userRepository) {
    return args -> {
      //creacion de permisos
      final PermissionEntity writerPermission = PermissionEntity
        .builder()
        .name("WRITER")
        .build();

      final PermissionEntity reportPermission = PermissionEntity
        .builder()
        .name("REPORT")
        .build();

      final RoleEntity ROLE_ADMIN = RoleEntity
        .builder()
        .role(RoleEnum.ADMIN)
        .permissions(Set.of(writerPermission, reportPermission))
        .build();

      //creacion de user



      UserEntity userAdmin = UserEntity
        .builder()
        .userName(adminUserName)
        .name("bryan")
        .password(passwordEncoder.encode(adminPassword))
        .email(adminEmail)
        .dni(89345289)
        .lastName("cardenas")
        .phone(99347648)
        .age(22)
        .gender("Masculino")
        .address("Av. Rosa Melano")
        .roles(Set.of(ROLE_ADMIN))
        .isEnable(true)
        .accountNoExpired(true)
        .accountNoLocked(true)
        .credentialNoExpired(true)
        .build();
        //userRepository.save(userAdmin);
    };
  }
}
