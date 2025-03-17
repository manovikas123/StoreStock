package com.manovikas.storestock.dao;

import com.manovikas.storestock.entity.Brand;
import com.manovikas.storestock.entity.Item;
import com.manovikas.storestock.entity.PriceQuantity;

import java.util.List;

public interface StoreDAO {

    void createItem(Item item);
    void createBrandWithItem(Brand brand);

    void  createPriceAndQuantityWithBrand(PriceQuantity pq);

    List<Item> ListOfItems();

    Item findItemsById(int id);

    Brand findBrandById(int id);

    void DeletePriceAndQuantity(int id);

    void DeleteBrand(int id);

    void DeleteItem(int id);

}
