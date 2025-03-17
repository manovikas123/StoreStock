package com.manovikas.storestock.dao;

import com.manovikas.storestock.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import com.manovikas.storestock.dto.StockDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Item, Integer> {

    @Query("SELECT new com.manovikas.storestock.dto.StockDTO(i.itemName, b.brandName, p.priceItem, p.quantity) " +
            "FROM Item i " +
            "JOIN Brand b ON i.id = b.item.id " +
            "JOIN PriceQuantity p ON b.id = p.brand.id")
    List<StockDTO> getStockData();

    @Query("SELECT new com.manovikas.storestock.dto.StockDTO(i.itemName, b.brandName, p.priceItem, p.quantity) " +
            "FROM Item i " +
            "JOIN Brand b ON i.id = b.item.id " +
            "JOIN PriceQuantity p ON b.id = p.brand.id"+" where p.quantity=0")
    List<StockDTO> getEmptyStockData();

    @Query("SELECT new com.manovikas.storestock.dto.StockDTO(i.itemName, b.brandName, p.priceItem, p.quantity) " +
            "FROM Item i " +
            "JOIN Brand b ON i.id = b.item.id " +
            "JOIN PriceQuantity p ON b.id = p.brand.id"+" where i.id= :id")
    List<StockDTO> getStockDataByItem(@Param("id") Integer id);

    @Query("SELECT new com.manovikas.storestock.dto.StockDTO(i.itemName, b.brandName, p.priceItem, p.quantity) " +
            "FROM Item i " +
            "JOIN Brand b ON i.id = b.item.id " +
            "JOIN PriceQuantity p ON b.id = p.brand.id"+" where b.id= :id")
    List<StockDTO> getStockDataByBrand(@Param("id") Integer id);



}
