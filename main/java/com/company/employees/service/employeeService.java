package com.company.employees.service;


import com.company.employees.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import com.company.employees.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class employeeService {

    public EmployeeRepository repository;

    public employeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee salvar(Employee emp) {
        return repository.save(emp);
    }

    public String delete(Employee emp) {
        repository.delete(emp);
        return "Usuário: " + emp.getId() + "deletado";
    }

    public Employee uptade(Employee emp) {
        Employee novoEmp = new Employee();
        emp = novoEmp;
        if (emp.getTipo().getDesc().equalsIgnoreCase("Efetivo")){
            emp.setAdicional(null);
        }
        return repository.save(novoEmp);
    }


    public List<Employee> findAll() {
    return repository.findAll();
    }

    public Optional<Employee> findById(Employee emp) {
    return repository.findById(emp.getId());
    }

//    public static List<Integer> report() {
//    Deixado de lado por enquanto
//    }
}




