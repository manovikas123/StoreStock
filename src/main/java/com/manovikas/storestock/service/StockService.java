package com.manovikas.storestock.service;

import com.manovikas.storestock.dto.StockDTO;
import com.manovikas.storestock.entity.Brand;
import com.manovikas.storestock.entity.Item;
import com.manovikas.storestock.entity.PriceQuantity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StockService {
    void createItems(StockDTO stockDTO);
    void createBrand(StockDTO stockDTO);
    void createPriceAndQuantity(StockDTO stockDTO);

    void updateQuantity(StockDTO stockDTO);






    List<Item> getAllItems();
    List<Brand> getAllBrands();

    List<PriceQuantity> getAllPQfromBrand(StockDTO stockDto);
    //List<>
    List<Item> getItems();
    List<Brand> getAllBrandsByItemName(String s);
    List<PriceQuantity> getAllPriceQuantity();



    void reduceQuantity(int quantity, Integer quantity1, PriceQuantity priceQuantity);
}
