package com.example.homework210;

import com.example.homework210.exception.EmployeeNotFoundException;
import com.example.homework210.model.Employee;
import com.example.homework210.service.DepartmentService;
import com.example.homework210.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void beforeEach(){
        List<Employee> employees = List.of(
                new Employee("Ivan", "Ivanov"),
                new Employee("Petr", "Petrov"),
                new Employee("Semen", "Semenov")
        );
        when(employeeService.getAll()).thenReturn(employees);
    }

    @Test
    public void employeesByDepartmentNegativeTest(){
        assertThat(departmentService.findEmployeesFromDepartment(4)).isEmpty();
    }

    @Test
    public void withNoEmployees(){
    when(employeeService.getAll()).thenReturn(Collections.emptyList());
    assertThat(departmentService.findEmployeesFromDepartment(1)).isEmpty();
    verify(employeeService, Mockito.times(2)).getAll();
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryParams")
    public void employeeWithMaxSalaryPositiveTest(int department, Employee expected){
        assertThat(departmentService.findEmployeeWithMaxSalaryFromDepartment(department)).isEmpty(expected);
    }

    @Test
    public void employeeWithMaxSalaryNegativeTest(){
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findEmployeeWithMaxSalaryFromDepartment(3));
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryParams")
    public void employeeWithMinSalaryPositiveTest(int department, Employee expected){
        assertThat(departmentService.findEmployeeWithMinSalaryFromDepartment(department)).isEmpty(expected);
    }

    @Test
    public void employeeWithMinSalaryNegativeTest(){
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findEmployeeWithMinSalaryFromDepartment(3));
    }

}
