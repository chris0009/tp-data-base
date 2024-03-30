package com.isep.testjpa.controller;

import com.isep.testjpa.repository.EmpRepository;
import com.isep.testjpa.model.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SimpleController {

    @Autowired
    private EmpRepository empRepository;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String hello(@Param("name") String name) {
        return "Hello " + name;
    }

    @RequestMapping(value="/employees", method= RequestMethod.GET)
    public List<Emp> getEmployees() {
        return empRepository.findAll();
    }

    @PostMapping(value="/employees")
    public Emp addEmployee(@RequestBody Emp emp) {
        return empRepository.save(emp);
    }

    @Autowired
    private EmpRepository EmpRepository;

    // Get Employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Emp> getEmployeeById(@PathVariable Long id) {
        Optional<Emp> employeeOptional = empRepository.findById(id);
        return employeeOptional.map(employee -> ResponseEntity.ok().body(employee))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get All Employees
    @GetMapping
    public List<Emp> getAllEmployees() {
        return empRepository.findAll();
    }

    // Update Employee by ID
    @PutMapping("/{id}")
    public ResponseEntity<Emp> updateEmployeeById(@PathVariable Long id, @RequestBody Emp updatedEmployee) {
        Optional<Emp> existingEmployeeOptional = empRepository.findById(id);
        if (existingEmployeeOptional.isPresent()) {
            Emp existingEmployee = existingEmployeeOptional.get();
            existingEmployee.setEname(updatedEmployee.getEname());
            existingEmployee.setEfirst(updatedEmployee.getEfirst());
            // Set other fields as needed
            empRepository.save(existingEmployee);
            return ResponseEntity.ok(existingEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        Optional<Emp> employeeOptional = empRepository.findById(id);
        if (employeeOptional.isPresent()) {
            empRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}


