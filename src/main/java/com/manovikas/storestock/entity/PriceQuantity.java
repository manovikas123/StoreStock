package com.manovikas.storestock.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="price_quantity")
public class PriceQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="price_item")
    private BigDecimal priceItem;

    @Column(name="quantity")
    private int quantity;

    @JsonManagedReference
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="brand_id")
    private Brand brand;

    public PriceQuantity(){}

    public PriceQuantity(int id, BigDecimal priceItem, int quantity) {
        this.id = id;
        this.priceItem = priceItem;
        this.quantity = quantity;
    }

    public PriceQuantity(BigDecimal priceItem, int quantity) {
        this.priceItem = priceItem;
        this.quantity = quantity;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(BigDecimal priceItem) {
        this.priceItem = priceItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "PriceQuantity{" +
                "id=" + id +
                ", priceItem=" + priceItem +
                ", quantity=" + quantity +
                ", brand_id=" + brand +
                '}';
    }
}
