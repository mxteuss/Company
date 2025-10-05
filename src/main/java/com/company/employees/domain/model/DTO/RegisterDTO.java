package com.company.employees.domain.model.DTO;

import com.company.employees.domain.model.Enum.Roles;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Valid
@NotNull
public record RegisterDTO(@NotNull String login,
                          @NotNull String password,
                          @NotNull @Valid Roles role) {
}
