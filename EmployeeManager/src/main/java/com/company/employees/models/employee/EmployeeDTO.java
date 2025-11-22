package com.company.employees.models.employee;

import com.company.employees.models.employee.Enum.Tipo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record EmployeeDTO(

        @Enumerated(EnumType.STRING)
        Tipo tipo,
        String name,
        Integer salario,
        Integer adicional,
        String email) {
}
