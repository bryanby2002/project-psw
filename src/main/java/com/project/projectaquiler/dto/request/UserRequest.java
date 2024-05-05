package com.project.projectaquiler.dto.request;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Valid
public record UserRequest
        (
                @NotNull @NotBlank String userName,
                @NotNull @NotBlank String password,
                @Digits(integer = 8, fraction = 0) Integer dni,
                @NotNull @NotBlank @Email  String email,
                @NotNull @NotBlank String name,
                String lastName,
                @Digits(integer = 9, fraction = 0) Integer phone,
                Integer age,
                String gender,
                @NotNull @NotBlank String address
        )
{
        public static void validate(UserRequest userRequest) {
                Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
                Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

                if (!violations.isEmpty()) {
                        throw new ConstraintViolationException(violations);
                }
        }


}
