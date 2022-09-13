package service;

import exception.EmployeeAlreadyAddedException;
import exception.EmployeeNotFoundException;
import exception.EmployeeStorageIsFullException;
import model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {

    private static final int SIZE = 5;
    private final List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>();

    }

    public Employee addEmployee(String name,
                                String secondName) {
        Employee employee = new Employee(name, secondName);
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
        Employee employee = new Employee(name, secondName);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    public Employee findEmployee(String name,
                                 String secondName) {
        Employee employee = new Employee(name, secondName);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }
    public List<Employee> getAll(){
        return Collections.unmodifiableList(employees);
    }
}