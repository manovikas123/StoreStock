package com.manovikas.storestock.dto;

import com.manovikas.storestock.entity.Brand;
import com.manovikas.storestock.entity.Item;
import com.manovikas.storestock.entity.PriceQuantity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StockDTO {
    private String itemName;
    private String brandName;

    private BigDecimal priceItem;
    private Integer quantity;


    public List<Item> item =new ArrayList<>();

    public List<Brand> brand =new ArrayList<>();
    public List<PriceQuantity> price =new ArrayList<>();

    public List<PriceQuantity> getPrice() {
        return price;
    }

    public void setPrice(List<PriceQuantity> price) {
        this.price = price;
    }

    public List<Brand> getBrand() {
        return brand;
    }

    public void setBrand(List<Brand> brand) {
        this.brand = brand;
    }

    public void setItem(List<Item> item1)
    {
        item = item1;
    }
    public StockDTO(){}


    public List<Item> getItem() {

        return item;
    }


    public StockDTO(String itemName, String brandName, BigDecimal priceItem, Integer quantity) {
        this.itemName = itemName;
        this.brandName = brandName;
        this.priceItem = priceItem;
        this.quantity = quantity;

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public BigDecimal getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(BigDecimal priceItem) {
        this.priceItem = priceItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
