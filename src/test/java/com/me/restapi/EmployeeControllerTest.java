package com.me.restapi;

/*
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", employee.getId()))
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/" + employee.getId()))

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/gender=" + "male"))
        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("gender", "male"))

        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("page", "1").param("pageSize", "3"))
*/

import com.me.restapi.controller.EmployeeRepository;
import com.me.restapi.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MockMvc mockMvc;
    @BeforeEach
    void clearRepository(){
        employeeRepository.clearAll();
    }

    @Test
    void should_return_employees_when_perform_get_given_employees() throws Exception {
        //given
        Employee employee = new Employee(1, "Brian", 18, "male", 9999);
        employeeRepository.create(employee);


        //When
        //then
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Brian"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(9999));

    }

    @Test
    void should_return_employee_when_perform_post_given_employee() throws Exception {
        //given
        String employee = "{\n" +
                "    \"name\":\"NotKoby\",\n" +
                "    \"age\":0,\n" +
                "    \"gender\":\"male\",\n" +
                "    \"salary\":15\n" +
                "}";
        //When
        //then
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON).content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("NotKoby"))
                .andExpect(jsonPath("$.age").value(0))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(15));

    }

    @Test
    void should_return_employee_when_perform_get_given_employees_and_id() throws Exception {
        //given
        Employee employee = new Employee(1, "Brian", 18, "male", 9999);
        employeeRepository.create(employee);


        //When
        //then
        mockMvc.perform(get("/employees/{id}", employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Brian"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(9999));

    }

    @Test
    void should_return_employee_when_perform_get_given_employees_and_gender() throws Exception {
        //given
        Employee employee = new Employee(1, "Brian", 18, "male", 9999);
        employeeRepository.create(employee);


        //When
        //then
        mockMvc.perform(get("/employees?gender=" + employee.getGender()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Brian"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(9999));

    }

    @Test
    void should_return_employees_when_perform_get_given_employees_and_page_and_pageSize() throws Exception {
        //given
        Employee employee = new Employee(1, "Brian", 18, "male", 9999);
        employeeRepository.create(employee);


        //When
        //then
        mockMvc.perform(get("/employees").param("page", "1").param("pageSize", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Brian"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(9999));

    }

    @Test
    void should_remove_employee_when_perform_delete_given_employees_and_id() throws Exception {
        //given
        Employee employee = new Employee(1, "Brian", 18, "male", 9999);
        employeeRepository.create(employee);

        //When
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/"+employee.getId()))
                .andExpect(status().isNoContent());


    }

    @Test
    void should_return_employee_when_perform_put_given_employee_and_id() throws Exception {
        //given
        Employee employee = new Employee(1, "Brian", 18, "male", 9999);
        employeeRepository.create(employee);
        String updatedEmployee = "{\n" +
                "    \"name\":\"NotKoby\",\n" +
                "    \"age\":0,\n" +
                "    \"gender\":\"male\",\n" +
                "    \"salary\":15\n" +
                "}";
        //When
        //then
        mockMvc.perform(put("/employees/{id}",employee.getId())
                .contentType(MediaType.APPLICATION_JSON).content(updatedEmployee))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Brian"))
                .andExpect(jsonPath("$.age").value(0))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(15));

    }
}
