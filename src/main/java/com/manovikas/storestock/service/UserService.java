package com.manovikas.storestock.service;

import com.manovikas.storestock.dto.EmployeeDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public UserDetails loadUserByUsername(String username);

    public void addEmployee(EmployeeDTO employeeDto);
    public void deleteEmp(EmployeeDTO employeeDTO);
}
