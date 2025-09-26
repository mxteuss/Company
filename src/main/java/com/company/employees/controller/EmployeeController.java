package com.company.employees.controller;

import com.company.employees.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class EmployeeController {

    public EmployeeService employeeService;
}
