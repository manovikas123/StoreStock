package com.manovikas.storestock.dao;


import com.manovikas.storestock.entity.PriceQuantity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.OptionalInt;
@Repository
public interface PriceQuantityRepository extends JpaRepository<PriceQuantity,Integer> {
    Optional<Integer> findIdByBrandIdAndPriceItem(int brandId, BigDecimal priceItem);
    Optional<PriceQuantity> findByPriceItemAndBrandId(@Param("priceItem") BigDecimal priceItem, @Param("brandId") Integer brandId);
    Optional<PriceQuantity> findByBrandIdAndId(int brandId, int priceQuantityId);

    @Modifying
    @Transactional
    @Query("DELETE FROM PriceQuantity pq WHERE pq.id = :id")
    void deleteByIdCustom(@Param("id") Integer id);
}
