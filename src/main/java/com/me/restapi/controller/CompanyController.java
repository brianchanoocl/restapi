package com.me.restapi.controller;

import com.me.restapi.repository.CompanyRepository;
import com.me.restapi.entity.Company;
import com.me.restapi.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {
    private CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable int id) {
        return companyRepository.findById(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable int id){
        return companyRepository.findEmployeesByCompanyId(id);
    }

    @GetMapping(params = {"page","pageSize"} )
    public List<Company> getCompaniesByPage(@RequestParam Integer page, Integer pageSize){
        return companyRepository.findByPage(page,pageSize);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public Company createCompany(@RequestBody Company company) {
        return companyRepository.create(company);
    }

    @PutMapping("/{id}")
    public Company editCompany(@PathVariable Integer id, @RequestBody Company updatedCompany) {
        Company company = companyRepository.findById(id);

        if(updatedCompany.getName() != null)
            company.setName(updatedCompany.getName());

        return companyRepository.save(id, company);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Company deleteCompany(@PathVariable Integer id) {
        return companyRepository.delete(id);
    }
}
