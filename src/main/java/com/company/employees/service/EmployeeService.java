package com.company.employees.service;


import com.company.employees.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import com.company.employees.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class EmployeeService {

    public EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
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


    public Page<Employee> findAll(int pageNO, int pageSize) {
        Pageable pageable = PageRequest.of(pageNO, pageSize);
        return repository.findAll(pageable);

    }

    public Optional<Employee> findById(Employee emp) {
    return repository.findById(emp.getId());
    }

//    public static List<Integer> report() {
//    Deixado de lado por enquanto
//    }
}




