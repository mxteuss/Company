package com.company.employees.domain.model.DTO;

import com.company.employees.domain.model.Enum.Roles;

public record RegisterDTO(String login, String password, Roles role) {
}
