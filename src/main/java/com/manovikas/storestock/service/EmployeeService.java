package com.manovikas.storestock.service;

import com.manovikas.storestock.dao.EmployeeRepository;
import com.manovikas.storestock.dto.EmployeeDTO;
import com.manovikas.storestock.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAllEmployeeDTOs();
    }
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
}
