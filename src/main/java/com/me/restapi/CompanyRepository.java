package com.me.restapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    public CompanyRepository() {
        List<Employee> employeesInCompany1 = new ArrayList<>();
        employeesInCompany1.add(new Employee(1, "Brian The Boss", 18, "male", 99999999));
        employeesInCompany1.add(new Employee(2, "Anna", 18, "female", 10800));
        employeesInCompany1.add(new Employee(3, "Betty", 18, "female", 8100));

        List<Employee> employeesInCompany2 = new ArrayList<>();
        employeesInCompany2.add(new Employee(1, "Cindy", 18, "female", 9999));
        employeesInCompany2.add(new Employee(2, "Daisy", 18, "female", 18000));
        List<Employee> employeesInCompany3 = new ArrayList<>();
        employeesInCompany3.add(new Employee(1, "Eddy", 18, "male", 100));
        employeesInCompany3.add(new Employee(2, "Felix", 18, "male", 100));

        companies.add(new Company(1, "Brian Dream Company",  employeesInCompany1));
        companies.add(new Company(2, "Company A", employeesInCompany2));
        companies.add(new Company(3, "Company B", employeesInCompany3));
        companies.add(new Company(4, "Poor Company", new ArrayList<>()));
        companies.add(new Company(5, "Empty Company", null));
    }

    public List<Company> findAll() {
        return companies;
    }

    public Company findById(int id) {
        return companies.stream().filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseThrow(NoCompanyFoundException::new);
    }

    public List<Employee> findEmployeesByCompanyId(int id) {
        return companies.stream().filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseThrow(NoCompanyFoundException::new)
                .getEmployees();
    }

    public List<Company> findByPage(Integer page, Integer pageSize) {
        return null;
    }
}
