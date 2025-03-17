package com.manovikas.storestock.service;

import com.manovikas.storestock.dao.BrandRepository;
import com.manovikas.storestock.dao.ItemRepository;
import com.manovikas.storestock.dao.PriceQuantityRepository;
import com.manovikas.storestock.dao.StoreDAO;

import com.manovikas.storestock.dto.StockDTO;
import com.manovikas.storestock.entity.Brand;
import com.manovikas.storestock.entity.Item;
import com.manovikas.storestock.entity.PriceQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImp implements StockService{

    @Autowired
    private StoreDAO storeDAO;



    @Autowired
    public ItemRepository itemRepository;

    @Autowired
    public BrandRepository brandRepository;

    @Autowired
    public PriceQuantityRepository priceQuantityRepository;



   @Override
    public void createItems(StockDTO stockDTO) {

        Item item=new Item();
        item.setItemName(stockDTO.getItemName());



        Brand brand=new Brand();
        brand.setBrandName(stockDTO.getBrandName());

        PriceQuantity pq=new PriceQuantity();
        pq.setPriceItem(stockDTO.getPriceItem());
        pq.setQuantity(stockDTO.getQuantity());

        item.addBrandAndPQ(brand,pq);
       itemRepository.save(item);
    }


    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }


    @Override
    public void createBrand(StockDTO stockDTO) {

      Item item=itemRepository.findByItemName(stockDTO.getItemName()).orElseThrow(()-> new RuntimeException("ItemNotFound"));


        Brand brand=new Brand();
        brand.setBrandName(stockDTO.getBrandName());

        PriceQuantity pq=new PriceQuantity();
        pq.setPriceItem(stockDTO.getPriceItem());
        pq.setQuantity(stockDTO.getQuantity());

        item.addBrandAndPQ(brand,pq);
        itemRepository.save(item);
    }




    @Override
    public void createPriceAndQuantity(StockDTO stockDTO) {

       Optional<Item> i=itemRepository.findByItemName(stockDTO.getItemName());
       int itemId=i.get().getId();
        Optional<Brand> brand=brandRepository.findByBrandNameAndItemId(stockDTO.getBrandName(),itemId);

        PriceQuantity pq=new PriceQuantity();
        pq.setPriceItem(stockDTO.getPriceItem());
        pq.setQuantity(stockDTO.getQuantity());

        brand.get().addPriceQuantity(pq);
       brandRepository.save(brand.get());


    }


    @Override
    public List<Brand> getAllBrandsByItemName(String s) {

       Item item=itemRepository.findByItemName(s).orElseThrow(()-> new RuntimeException("ItemNotFound"));

//
        return item.getBrand();
    }

    @Override
    public List<Item> getAllItems() {

       return itemRepository.findAll();


    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();


    }

    @Override
    public List<PriceQuantity> getAllPQfromBrand(StockDTO stockDto) {

        Optional<Item> i=itemRepository.findByItemName(stockDto.getItemName());
        int itemid=i.get().getId();
        Optional<Brand> b=brandRepository.findByBrandNameAndItemId(stockDto.getBrandName(),itemid);
        int brandId=b.get().getId();

        List<PriceQuantity> pq=b.get().getPriceQuantities();
        return pq;
    }

    @Override
    public void updateQuantity(StockDTO stockDtO) {
       Item i=itemRepository.findByItemName(stockDtO.getItemName()).orElseThrow(()-> new RuntimeException("ItemNotFound"));
        System.out.println("the item is"+i.toString());
       int id=i.getId();
        Brand brand = brandRepository.findByBrandNameAndItemId(stockDtO.getBrandName(), id).orElseThrow(() -> new RuntimeException("Brand not found for given itemId"));

        int bid=brand.getId();
        System.out.println("the brand is "+brand.toString());
        PriceQuantity pq=priceQuantityRepository.findByPriceItemAndBrandId(
                stockDtO.getPriceItem(),
                bid
        ).orElseThrow(() -> new RuntimeException("PriceQuantity not found for given priceItem and brandId"));
        System.out.println("the price is"+pq.toString());
        int quantity=pq.getQuantity();
        int stockQuantity=stockDtO.getQuantity();
        pq.setQuantity(quantity+stockQuantity);
        priceQuantityRepository.save(pq);



    }
    @Override
    public void reduceQuantity(int quantity, Integer quantity1, PriceQuantity priceQuantity) {
        priceQuantity.setQuantity(quantity-quantity1);
        priceQuantityRepository.save(priceQuantity);

    }

    @Override
    public List<PriceQuantity> getAllPriceQuantity() {
        return priceQuantityRepository.findAll();

    }


}
