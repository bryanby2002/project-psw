package com.project.projectaquiler.dto;

public record UserRequest
        (
                String username,
                String password,
                String email,
                String name,
                String lastName,
                Integer phone,
                Integer age,
                String gender,
                String address
        )
{
}
