package com.project.projectaquiler.dto.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityDetails {

    private String id;
    private String userName;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Set<String> role;
    private Integer dni;
    private Integer phone;
    private String address;

}
