package com.project.projectaquiler.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastName;
    private Integer phone;
    private Integer age;
    private String gender;
    private String address;
}
