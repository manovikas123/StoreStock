package com.manovikas.storestock.service;

import com.manovikas.storestock.dao.*;
import com.manovikas.storestock.dto.StockDTO;
import com.manovikas.storestock.entity.Brand;
import com.manovikas.storestock.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListServiceImp implements ListService{

    @Autowired
    private StoreDAO storeDAO;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    PriceQuantityRepository priceQuantityRepository;
    @Autowired
    private StockRepository stockRepository;


    @Override
    public List<StockDTO> getStockDetails() {
        return stockRepository.getStockData();
    }

    @Override
    public List<StockDTO> getEmptyStockDetails() {
        return stockRepository.getEmptyStockData();
    }

    @Override
    public List<StockDTO> getStockByItem(StockDTO stockDTO) {
        Optional<Item> i=itemRepository.findByItemName(stockDTO.getItemName());
        return stockRepository.getStockDataByItem(i.get().getId());
    }

    @Override
    public List<StockDTO> getStockByBrand(StockDTO stockDto) {
        Optional<Item> i=itemRepository.findByItemName(stockDto.getItemName());
        int itemid=i.get().getId();
        Optional<Brand> b=brandRepository.findByBrandNameAndItemId(stockDto.getBrandName(),itemid);
        int brandId=b.get().getId();
        return stockRepository.getStockDataByBrand(brandId);
    }
}
