package com.shubham.psych.game.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.psych.game.model.Employee;
import com.shubham.psych.game.repository.EmployeeRepository;


@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long id) throws Exception {
        return employeeRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") Long id, @Valid @RequestBody Employee employee) throws Exception {
    	Employee e = employeeRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        e.setName(employee.getName());
        return employeeRepository.save(e);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long id) throws Exception {
    	Employee e = employeeRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        employeeRepository.delete(e);
        return ResponseEntity.ok().build();
    }


}
