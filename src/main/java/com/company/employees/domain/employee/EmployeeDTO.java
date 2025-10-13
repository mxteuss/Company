package com.company.employees.domain.employee;

import com.company.employees.domain.employee.Enum.Tipo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record EmployeeDTO(

        @Enumerated(EnumType.STRING)
        Tipo tipo,
        String name,
        Integer salario,
        Integer adicional) {
}
