package com.example.homework210.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Employee {
    private static int counter;

    private Integer id;

    @JsonProperty("firsName")
    private String name;
    @JsonProperty("lastName")
    private String secondName;

    private String thirdName;

    private int department;

    private int salary;

    public Employee(String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.department = department;
        this.salary = salary;
        this.id = counter++;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && salary == employee.salary && id.equals(employee.id) && name.equals(employee.name) && secondName.equals(employee.secondName) && thirdName.equals(employee.thirdName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName, thirdName, department, salary);
    }

    @Override
    public String toString() {
        return "Employee" +
                "id=" + id +
                ", name='" + name +
                ", secondName='" + secondName +
                ", thirdName='" + thirdName +
                ", department=" + department +
                ", salary=" + salary;
    }
    public void print(){
        System.out.println(this.toString());
    }
}
