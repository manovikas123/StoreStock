package com.manovikas.storestock.dao;

import com.manovikas.storestock.dto.EmployeeDTO;
import com.manovikas.storestock.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT new com.manovikas.storestock.dto.EmployeeDTO(i.firstName, i.lastName, i.email, b.username, i.role) " +
            "FROM Employee i JOIN i.usernamePassword b")
    List<EmployeeDTO> findAllEmployeeDTOs();

}
