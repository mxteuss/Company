package com.company.employees.models.user;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank String name,
        @NotBlank String email
) {
}
