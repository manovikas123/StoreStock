package com.manovikas.storestock.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="brand_name")
    private String brandName;


    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="item_id")
    private Item item;

    @JsonIgnore
    @OneToMany(mappedBy = "brand",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private List<PriceQuantity> priceQuantities;



    public Brand(){

    }

    public Brand(String brandName) {
        this.brandName = brandName;
    }


    public Brand(String brandName, Item item) {
        this.brandName = brandName;
        this.item = item;
    }

    public List<PriceQuantity> getPriceQuantities() {
        return priceQuantities;
    }

    public void setPriceQuantities(List<PriceQuantity> priceQuantities) {
        this.priceQuantities = priceQuantities;
    }

    public Brand(int id, String brandName) {
        this.id = id;
        this.brandName = brandName;
    }

    public void addPriceQuantity(PriceQuantity pq)
    {
        if(priceQuantities==null)
        {
            priceQuantities=new ArrayList<>();
        }
        priceQuantities.add(pq);
        pq.setBrand(this);

    }









    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", item_id=" + item +
                '}';
    }
}
