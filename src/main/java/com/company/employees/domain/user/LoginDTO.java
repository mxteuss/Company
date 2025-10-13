package com.company.employees.domain.user;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(@NotBlank String token) {
}
