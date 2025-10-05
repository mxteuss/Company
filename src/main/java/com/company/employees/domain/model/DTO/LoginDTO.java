package com.company.employees.domain.model.DTO;

import jakarta.validation.constraints.NotNull;

public record LoginDTO(@NotNull String token) {
}
