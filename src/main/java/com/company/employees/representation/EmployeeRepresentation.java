package com.company.employees.representation;

import com.company.employees.domain.employee.Enum.Tipo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class EmployeeRepresentation extends RepresentationModel<EmployeeRepresentation> {
    private Long id;
    private Tipo tipo;
    private String nome;
    private Integer salario;
    private Integer adicional;

}
