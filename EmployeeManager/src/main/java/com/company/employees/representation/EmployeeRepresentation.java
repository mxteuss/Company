package com.company.employees.representation;

import com.company.employees.models.employee.Enum.Tipo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Getter
@Setter
public class EmployeeRepresentation extends RepresentationModel<EmployeeRepresentation> {
    private Long id;
    private Tipo tipo;
    private String nome;
    private Integer salario;
    private Integer adicional;

}
