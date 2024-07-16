//package com.example.demo.service;
//
//import com.example.demo.dto.EmployeeDTO;
//import com.example.demo.mapstruct.EmployeeMapper;
//import com.example.demo.model.Employee;
//import com.example.demo.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class EmployeeService {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private EmployeeMapper employeeMapper;
//
//    public EmployeeDTO getEmployeeById(int id) {
//        Employee employee = employeeRepository.findById(id).get();
//        return employeeMapper.employeeToEmployeeDTO(employee);
//    }
//
//    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
//        Employee employee = EmployeeMapper.INSTANCE.employeeDTOToEmployee(employeeDTO);
//        Employee savedEmployee = employeeRepository.save(employee);
//        return employeeMapper.employeeToEmployeeDTO(savedEmployee);
//    }
//
//    public EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO) {
//        Employee existingemployee = employeeRepository.findById(id).get();
//        existingemployee.setName(employeeDTO.getName());
//        existingemployee.setId(employeeDTO.getId());
//        Employee updatedEmployee = employeeRepository.save(existingemployee);
//        return employeeMapper.employeeToEmployeeDTO(updatedEmployee);
//    }
//
//    public void deleteEmployee(int id) {
//        employeeRepository.deleteById(id);
//    }
//
//    public List<EmployeeDTO> getAllEmployees() {
//        return employeeRepository.findAll()
//                .stream()
//                .map(EmployeeMapper.INSTANCE::employeeToEmployeeDTO)
//                .collect(Collectors.toList());
//    }
//}
//
package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapstruct.EmployeeMapper;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeDTO getEmployeeById(int id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        try {
            Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
            return employeeMapper.employeeToEmployeeDTO(employee);
        } catch (DataAccessException e) {
            throw new RuntimeException("Database error occurred", e); // handle database errors
//        } catch (Exception e) {
//            throw new RuntimeException("Unexpected error occurred", e); // handle other unexpected errors
        }
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        try {
            Employee employee = employeeMapper.employeeDTOToEmployee(employeeDTO);
            Employee savedEmployee = employeeRepository.save(employee);
            return employeeMapper.employeeToEmployeeDTO(savedEmployee);
        } catch (DataAccessException e) {
            throw new RuntimeException("Database error occurred", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred", e);
        }
    }

    public EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO) {
        try {
            Employee existingEmployee = employeeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
            existingEmployee.setName(employeeDTO.getName());
            existingEmployee.setId(employeeDTO.getId());
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return employeeMapper.employeeToEmployeeDTO(updatedEmployee);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new RuntimeException("Database error occurred", e);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
    }

    public void deleteEmployee(int id) {
        try {
            Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
            employeeRepository.delete(employee);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new RuntimeException("Database error occurred", e);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
    }

    public List<EmployeeDTO> getAllEmployees() {
        try {
            return employeeRepository.findAll()
                    .stream()
                    .map(employeeMapper::employeeToEmployeeDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new RuntimeException("Database error occurred", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred", e);
        }
    }
}