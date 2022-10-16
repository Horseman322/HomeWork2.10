package com.example.homework210;

import com.example.homework210.exception.EmployeeAlreadyAddedException;
import com.example.homework210.exception.EmployeeNotFoundException;
import com.example.homework210.exception.IncorrectNameException;
import com.example.homework210.exception.IncorrectSecondNameException;
import com.example.homework210.model.Employee;
import com.example.homework210.service.EmployeeService;
import com.example.homework210.service.ValidatorService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService(new ValidatorService());

    @ParameterizedTest
    @MethodSource("params")
    public void addNegativeTest1(String name,
                                 String secondName) {
        Employee expected = new Employee(name, secondName);
        assertThat(employeeService.addEmployee(name, secondName)).isEqualTo(expected);

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.addEmployee(name, secondName));
    }
    @ParameterizedTest
    @MethodSource("params")
    public void addNegativeTest2(String name,
                                 String secondName) {
        List<Employee> employees = generateEmployees(10);
        employees.forEach(employee ->
                assertThat(
                        employeeService.addEmployee(employee.getName(), employee.getSecondName())).isEqualTo(employee)
        );
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.addEmployee(name, secondName));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void addNegativeTest3(){
        assertThatExceptionOfType(IncorrectNameException.class)
                .isThrownBy(() -> employeeService.addEmployee("Иван", "Иванов"));
        assertThatExceptionOfType(IncorrectSecondNameException.class)
                .isThrownBy(() -> employeeService.addEmployee("Иван", "Иванов"));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void removeNegativeTest(String name,
                                 String secondName) {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.removeEmployee("test", "test"));

        Employee expected = new Employee(name, secondName);
        assertThat(employeeService.addEmployee(name, secondName)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.removeEmployee("test", "test"));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void removePositiveTest(String name,
                                 String secondName) {
        Employee expected = new Employee(name, secondName);
        assertThat(employeeService.addEmployee(name, secondName)).isEqualTo(expected);
        assertThat(employeeService.removeEmployee(name, secondName)).isEqualTo(expected);
        }


    @ParameterizedTest
    @MethodSource("params")
    public void findNegativeTest(String name,
                                   String secondName) {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee("test", "test"));

        Employee expected = new Employee(name, secondName);
        assertThat(employeeService.addEmployee(name, secondName)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.removeEmployee("test", "test"));
    }


    private List<Employee> generateEmployees(int size){
        return Stream.iterate(1, i -> i + 1)
                .limit(size)
                .map(i -> new Employee("name" + (char) ((int) 'a' + i), "secondName" + (char) ((int) 'a' + i)))
                .collect(Collectors.toList());
    }

    public static Stream<Arguments> params(){
        return Stream.of(
                Arguments.of("Ivan", "Ivanov"),
                Arguments.of("Petr", "Petrov"),
                Arguments.of("Semen", "Semenov")
        );
    }

}
