package com.company.employees.domain.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(
        @NotNull @Valid String login,
        @NotNull @Valid String password) {
}
