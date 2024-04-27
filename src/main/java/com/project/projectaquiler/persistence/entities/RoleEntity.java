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
@Table(name = "roles")
@Builder
public class RoleEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private RoleEnum role;
}
