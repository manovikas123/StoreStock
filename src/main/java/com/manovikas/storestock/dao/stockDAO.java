package com.manovikas.storestock.dao;

import com.manovikas.storestock.entity.Brand;
import com.manovikas.storestock.entity.Item;
import com.manovikas.storestock.entity.PriceQuantity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class stockDAO implements StoreDAO{

    @Autowired
    public EntityManager entityManager;




    @Override
    @Transactional
    public void createItem(Item item) {

        entityManager.persist(item);

    }

    @Override
    @Transactional
    public void createBrandWithItem(Brand brand) {
        entityManager.merge(brand);

    }

    @Override
    @Transactional
    public void createPriceAndQuantityWithBrand(PriceQuantity pq) {
        entityManager.merge(pq);
    }

    @Override
    public List<Item> ListOfItems() {
        TypedQuery<Item> query=entityManager.createQuery("select i from items i", Item.class);

        return query.getResultList();
    }


    @Override
    public Item findItemsById(int id) {
        Item i=entityManager.find(Item.class,id);


        return i;
    }

    @Override
    public Brand findBrandById(int id) {
        Brand brand=entityManager.find(Brand.class,id);
        return brand;
    }

    @Override
    @Transactional
    public void DeletePriceAndQuantity(int id) {
        PriceQuantity pq=entityManager.find(PriceQuantity.class,id);
        entityManager.remove(pq);

    }

    @Override
    @Transactional
    public void DeleteBrand(int id) {
        Brand b=entityManager.find(Brand.class,id);
        entityManager.remove(b);

    }

    @Override
    @Transactional
    public void DeleteItem(int id) {
        Item i=entityManager.find(Item.class,id);
        entityManager.remove(i);
    }
}
