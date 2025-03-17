package com.manovikas.storestock.dao;

import com.manovikas.storestock.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BrandRepository  extends JpaRepository<Brand,Integer> {
    Optional<Brand> findByBrandName(String brandName);

    Optional<Brand> findByBrandNameAndItemId(@Param("brandName") String brandName, @Param("itemId") Integer itemId);

    Optional<Integer> findIdByBrandName(String brandName);
}
