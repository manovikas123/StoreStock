package com.manovikas.storestock.service;

import com.manovikas.storestock.dao.EmployeeRepository;
import com.manovikas.storestock.dao.UserRepository;
import com.manovikas.storestock.dto.EmployeeDTO;
import com.manovikas.storestock.entity.Employee;
import com.manovikas.storestock.entity.UsernamePassword;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

@Service
@Primary
public class UserServiceImp implements UserService, UserDetailsService {

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsernamePassword usernamePassword = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        String role = usernamePassword.getEmployee().getRole().replace("ROLE_", "");

        return User.builder()
                .username(usernamePassword.getUsername())
                .password(usernamePassword.getPassword())
                .roles(role)
                .build();
    }

    @Override
    @Transactional
    public void addEmployee(EmployeeDTO employeeDto) {
        Employee emp = new Employee();
        emp.setFirstName(employeeDto.getFirstName());
        emp.setLastName(employeeDto.getLastName());
        emp.setEmail(employeeDto.getEmail());
        emp.setRole(employeeDto.getRole());

        UsernamePassword up = new UsernamePassword();
        up.setUsername(employeeDto.getUsername());
        up.setPassword(passwordEncoder.encode(employeeDto.getPassword()));

        emp.setUsernamePassword(up);
     //   emp.adduser(up)
        employeeRepository.save(emp);
        userRepository.save(up);
    }

    @Override
    @Transactional
    public void deleteEmp(EmployeeDTO employeeDTO) {
        Optional<UsernamePassword> us=userRepository.findByUsername(employeeDTO.getUsername());
        employeeRepository.delete(us.get().getEmployee());
        userRepository.delete(us.get());
    }
}
