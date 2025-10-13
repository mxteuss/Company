package com.company.employees.domain.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Valid
@NotNull
public record RegisterDTO(@NotNull String login,
                          @NotNull String password,
                          @NotNull @Valid Roles role) {
}
