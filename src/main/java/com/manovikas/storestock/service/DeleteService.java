package com.manovikas.storestock.service;

import com.manovikas.storestock.dto.StockDTO;
import com.manovikas.storestock.entity.Item;
import jakarta.transaction.Transactional;

public interface DeleteService {
    public void deleteItem(Item item);
    public void deleteBrand(StockDTO stockDto);

    public void deletePrice(StockDTO stockDto);
}
