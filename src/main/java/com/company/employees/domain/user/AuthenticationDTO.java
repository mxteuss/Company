package com.company.employees.domain.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank @Valid String login,
        @NotBlank @Valid String password) {
}
