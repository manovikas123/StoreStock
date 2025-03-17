package com.manovikas.storestock.service;

import com.manovikas.storestock.dto.StockDTO;

import java.util.List;

public interface ListService {
    public List<StockDTO> getStockDetails();

    List<StockDTO> getEmptyStockDetails();

    List<StockDTO> getStockByItem(StockDTO stockDTO);
    List<StockDTO> getStockByBrand(StockDTO stockDTO);
}
