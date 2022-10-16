package com.example.homework210.service;

import com.example.homework210.exception.*;
import com.example.homework210.model.Employee;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private static final int SIZE = 5;
    private final List<Employee> employees = new ArrayList<>();
    private ValidatorService validatorService;

    public EmployeeService(ValidatorService validatorService){
        this.validatorService = validatorService;
    }


    public Employee addEmployee(String name,
                                String secondName) throws IncorrectNameException, IncorrectSecondNameException {
        Employee employee = new Employee(
                validatorService.validateName(name),
                validatorService.validateSecondName(secondName));
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < SIZE) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee removeEmployee(String name,
                                   String secondName) {
        Employee employee = employees.stream()
                .filter(employee1 -> employee1.getName().equals(name) && employee1.getSecondName().equals(secondName))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        employees.remove(employee);
            return employee;
    }


    public Employee findEmployee(String name,
                                 String secondName) {
        Employee employee = employees.stream()
                .filter(employee1 -> employee1.getName().equals(name) && employee1.getSecondName().equals(secondName))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        employees.remove(employee);
        return employee;
    }
    public List<Employee> getAll(){
        return new ArrayList<>(employees);
    }
}