package com.manovikas.storestock.service;

import com.manovikas.storestock.dao.BrandRepository;
import com.manovikas.storestock.dao.ItemRepository;
import com.manovikas.storestock.dao.PriceQuantityRepository;
import com.manovikas.storestock.dao.StoreDAO;
import com.manovikas.storestock.dto.StockDTO;
import com.manovikas.storestock.entity.Brand;
import com.manovikas.storestock.entity.Item;
import com.manovikas.storestock.entity.PriceQuantity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteServiceImp implements DeleteService{

    @Autowired
    private StoreDAO storeDAO;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    PriceQuantityRepository priceQuantityRepository;
    @Override
    public void deleteItem(Item item) {

        itemRepository.delete(item);

    }



    @Override
    public void deletePrice(StockDTO stockDto) {
        Optional<Item> i=itemRepository.findByItemName(stockDto.getItemName());
        int itemid=i.get().getId();
        Optional<Brand> b=brandRepository.findByBrandNameAndItemId(stockDto.getBrandName(),itemid);
        int brandId=b.get().getId();
        Optional<PriceQuantity> pq=priceQuantityRepository.findByPriceItemAndBrandId(stockDto.getPriceItem(), brandId);
        System.out.println("the pq is"+pq.get());
        priceQuantityRepository.deleteByIdCustom(pq.get().getId());



    }

    @Override
    public void deleteBrand(StockDTO stockDto) {
        Optional<Item> i=itemRepository.findByItemName(stockDto.getItemName());
        int itemid=i.get().getId();
        Optional<Brand> b=brandRepository.findByBrandNameAndItemId(stockDto.getBrandName(),itemid);
        brandRepository.delete(b.get());

    }
    /*



    @Override
    public void deletePrice(StockDTO stockDto) {
        Optional<Item> i=itemRepository.findByItemName(stockDto.getItemName());
        int itemid=i.get().getId();
        Optional<Brand> b=brandRepository.findByBrandNameAndItemId(stockDto.getBrandName(),itemid);
        int brandid=b.get().getId();
        Optional<PriceQuantity> pq=priceQuantityRepository.findByPriceItemAndBrandId(stockDto.getPriceItem(),brandid);

        if (pq.isPresent()) {
            System.out.println("Found: " + pq.get());
            priceQuantityRepository.delete(pq.get());
            System.out.println("Deleted Successfully!");
        } else {
            System.out.println("PriceQuantity not found for deletion!");
        }

    }

     */
}
