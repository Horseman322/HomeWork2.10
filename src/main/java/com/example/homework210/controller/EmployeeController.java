package controller;

import model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("firsName") String name,
                        @RequestParam("lastName") String secondName) {
        return employeeService.addEmployee(name, secondName);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("firsName") String name,
                           @RequestParam("lastName") String secondName) {
        return employeeService.removeEmployee(name, secondName);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("firsName") String name,
                         @RequestParam("lastName") String secondName) {
        return employeeService.findEmployee(name, secondName);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }
}
