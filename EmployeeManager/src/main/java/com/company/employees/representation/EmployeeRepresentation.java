package com.company.employees.representation;


import com.company.employees.models.employee.Enum.Tipo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.hateoas.RepresentationModel;



@Data
@EqualsAndHashCode(callSuper=false)
public class EmployeeRepresentation extends RepresentationModel<@NonNull EmployeeRepresentation> {
    private Long id;
    private Tipo tipo;
    private String nome;
    private Integer salario;
    private Integer adicional;

}
