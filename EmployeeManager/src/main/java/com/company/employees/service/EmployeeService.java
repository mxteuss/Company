package com.company.employees.service;

import com.company.employees.models.employee.Enum.Tipo;
import com.company.employees.models.employee.Employee;
import com.company.employees.producer.EmployeeProducer;
import com.company.employees.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeProducer employeeProducer;

    public EmployeeService(EmployeeRepository repository, EmployeeProducer employeeProducer) {
        this.repository = repository;
        this.employeeProducer = employeeProducer;
    }

    @Transactional
    public Employee save(Employee emp) {
        if (emp.getTipo().equals(Tipo.Efetivo)) {
            emp.setAdicional(null);
        }
        employeeProducer.sendEmail(emp);
        return repository.save(emp);
    }

    public void deleteById(Long id) {
         repository.deleteById(id);
    }

    public Employee uptade(Employee emp) {
        Employee novoEmp = new Employee();
        emp = novoEmp;
        if (emp.getTipo().equals(Tipo.Efetivo)) {
            emp.setAdicional(null);
        }
        return repository.save(novoEmp);
    }


    public Page<@NonNull Employee> findAll(int pageNO, int pageSize) {
        Pageable pageable = PageRequest.of(pageNO, pageSize);
        return repository.findAll(pageable);

    }

    public Optional<Employee> findById(Long id) {
        return Optional.of(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
    }
}



