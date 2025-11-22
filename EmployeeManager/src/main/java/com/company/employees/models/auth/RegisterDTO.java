package com.company.employees.models.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Valid
@NotNull
public record RegisterDTO(@NotNull String login,
                          @NotNull String password,
                          @NotNull @Valid Roles role) {
}