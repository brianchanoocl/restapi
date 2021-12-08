package com.me.restapi.controller;

import com.me.restapi.entity.Employee;
import com.me.restapi.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id){
        return employeeService.findById(id);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeeById(@RequestParam String gender){
        return employeeService.findByGender(gender);
    }

    @GetMapping(params = {"page","pageSize"} )
    public List<Employee> getEmployeesByPage(@RequestParam Integer page, Integer pageSize){
        return employeeService.findByPage(page,pageSize);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/{id}")
    public Employee editEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) {
        Employee employee = employeeService.findById(id);

        if(updatedEmployee.getAge() != null)
            employee.setAge(updatedEmployee.getAge());

        if(updatedEmployee.getSalary() != null)
            employee.setSalary(updatedEmployee.getSalary());

        return employeeService.save(id, employee);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Employee deleteEmployee(@PathVariable Integer id) {
        return employeeService.delete(id);
    }
}
