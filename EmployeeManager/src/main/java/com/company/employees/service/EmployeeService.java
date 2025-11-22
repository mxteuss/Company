package com.company.employees.service;


import com.company.employees.models.employee.Enum.Tipo;
import com.company.employees.models.employee.Employee;
import com.company.employees.producer.EmployeeProducer;
import com.company.employees.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class EmployeeService {

    @Value("${spring.mail.username}")
    private String emailFrom;


    private EmployeeRepository repository;
    private EmployeeProducer employeeProducer;
    private JavaMailSender mailSender;

    public EmployeeService(EmployeeRepository repository, EmployeeProducer employeeProducer, JavaMailSender mailSender) {
        this.repository = repository;
        this.employeeProducer = employeeProducer;
        this.mailSender = mailSender;
    }

    @Transactional
    public Employee save(Employee emp) {
        if(emp.getTipo().equals(Tipo.Efetivo)){
            emp.setAdicional(null);
        }
        employeeProducer.sendEmail(emp);
        return repository.save(emp);
    }

    public String deleteById(Long id) {
        repository.deleteById(id);
        return "Usuário: " + id + "deletado";
    }

    public Employee uptade(Employee emp) {
        Employee novoEmp = new Employee();
        emp = novoEmp;
        if (emp.getTipo().equals(Tipo.Efetivo)){
            emp.setAdicional(null);
        }
        return repository.save(novoEmp);
    }


    public Page<Employee> findAll(int pageNO, int pageSize) {
        Pageable pageable = PageRequest.of(pageNO, pageSize);
        return repository.findAll(pageable);

    }

    public Optional<Employee> findById(Long id) {
    return Optional.ofNullable(repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
    }


    public String report() {
        List<Employee> employees = repository.findAll();

    double sum = 0;
    for (Employee e : employees) {
        if (e.getAdicional() == null) {
            sum += e.getSalario();
        } else {
            sum += (e.getSalario() + e.getAdicional());
        }
    }
    double media = sum / employees.size();
    return "Total: " + sum
            + "\nMédia: " + media;
    }
}





