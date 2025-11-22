package com.company.employees.models.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(@NotBlank String token) {
}