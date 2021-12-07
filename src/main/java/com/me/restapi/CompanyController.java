package com.me.restapi;

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




}
