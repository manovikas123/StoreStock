package com.manovikas.storestock.dao;

import com.manovikas.storestock.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    Optional<Item> findByItemName(String itemsName);
   // Item findByItemName(String in);
    Optional<List<String>> findBrandNamesByItemName(@Param("itemName") String itemName);
}
