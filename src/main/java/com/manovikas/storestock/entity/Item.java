package com.manovikas.storestock.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="item_name")
    private String itemName;


   @OneToMany(mappedBy = "item",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Brand> brand;

    public Item(int id, String itemName) {
        this.id = id;
        this.itemName = itemName;
    }

    public void addBrand(Brand brand)
    {
        if(this.brand ==null)
        {
            this.brand =new ArrayList<>();
        }
        this.brand.add(brand);
        brand.setItem(this);

    }

    public void addBrandAndPQ(Brand brand,PriceQuantity pq)
    {
        if(this.brand ==null)
        {
            this.brand =new ArrayList<>();
        }
        this.brand.add(brand);
        brand.setItem(this);
        brand.addPriceQuantity(pq);

    }






    public Item() {}

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<Brand> getBrand() {
        return brand;
    }

    public void setBrand(List<Brand> brand) {
        this.brand = brand;
    }



}
