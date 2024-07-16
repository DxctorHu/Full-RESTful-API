package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

//    @Autowired
//    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

//    @GetMapping(value = "/employees")
//    public List<Employee> getEmployees() {
//
//        return employeeRepository.findAll();
//    }
//
//
//    @PostMapping(value = "/save")
//    public String saveEmployee(@RequestBody Employee employee) {
//        employeeRepository.save(employee);
//        return "Saved";
//    }
//
//    @PutMapping(value = "/update/{id}")
//    public String updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
//        Employee updatedEmployee = employeeRepository.findById(id).get();
//        updatedEmployee.setName(employee.getName());
////        updatedEmployee.setId(employee.getId());
//        updatedEmployee.setJob(employee.getJob());
//        updatedEmployee.setSalary(employee.getSalary());
//        employeeRepository.save(updatedEmployee);
//        return "Employee " + id +  " Updated";
//    }
//
//    @DeleteMapping(value = "/delete/{id}")
//    public String deleteEmployee(@PathVariable int id) {
//        employeeRepository.deleteById(id);
//        return "Deleted Employee: " + id;
//    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable int id, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
}