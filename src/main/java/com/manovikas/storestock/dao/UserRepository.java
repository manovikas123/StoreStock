package com.manovikas.storestock.dao;

import com.manovikas.storestock.entity.UsernamePassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UsernamePassword,Integer> {

    Optional<UsernamePassword> findByUsername(String username);



}
