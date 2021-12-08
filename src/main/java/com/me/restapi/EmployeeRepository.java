package com.me.restapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1, "Brian The Boss", 18, "male", 99999999));
        employees.add(new Employee(2, "Anna", 18, "female", 10800));
        employees.add(new Employee(3, "Betty", 18, "female", 8100));
        employees.add(new Employee(4, "Cindy", 18, "female", 9999));
        employees.add(new Employee(5, "Daisy", 18, "female", 18000));
        employees.add(new Employee(6, "Eddy", 18, "male", 100));
        employees.add(new Employee(7, "Felix", 18, "male", 100));
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(Integer id) {
        return employees.stream().filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(NoEmployeeFoundException::new);
    }

    public List<Employee> findByGender(String gender) {
        return employees.stream().filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> findByPage(Integer page, Integer pageSize) {
        return employees.stream()
                .skip((long) (page-1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Employee create(Employee employee) {
        Integer nextId = employees.stream()
                .mapToInt(Employee::getId)
                .max()
                .orElse(0) + 1;
        employee.setId(nextId);
        employees.add(employee);
        return employee;
    }

    public Employee save(Integer id, Employee updatedEmployee) {
        Employee employee = findById(id);
        employees.remove(employee);
        employees.add(updatedEmployee);
        return updatedEmployee;
    }

    public Employee delete(Integer id) {
        Employee employee = findById(id);
        employees.remove(employee);
        return null;
    }

    public void clearAll() {
        employees.clear();
    }
}
