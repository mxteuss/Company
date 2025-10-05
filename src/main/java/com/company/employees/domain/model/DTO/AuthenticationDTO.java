package com.company.employees.domain.model.DTO;

import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(
        @NotNull String login,
        @NotNull String password) {
}
