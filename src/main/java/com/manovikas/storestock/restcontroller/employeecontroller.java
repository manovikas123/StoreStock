

package com.manovikas.storestock.restcontroller;

import com.manovikas.storestock.dto.StockDTO;
import com.manovikas.storestock.entity.Brand;
import com.manovikas.storestock.entity.Item;
import com.manovikas.storestock.entity.PriceQuantity;
import com.manovikas.storestock.service.StockServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/test")
public class employeecontroller {

    @Autowired
    StockServiceImp stockServiceImp;
    @GetMapping("/check")
    public String check()
    {
        return "Hello world";
    }
    @GetMapping("/itemList")
    public List<Item> checkItems()
    {
       return stockServiceImp.getAllItems();

    }
    @GetMapping("/BrandList")
    public List<Brand> listOfBrand()
    {
        return stockServiceImp.getAllBrands();
    }

    @GetMapping("/PriceQuantityList")
    public List<PriceQuantity> listOfPriceQuantity()
    {
        return stockServiceImp.getAllPriceQuantity();
    }


@GetMapping("/findItems")
public List<Item> findItems()
{
    return stockServiceImp.getItems();
}

    @GetMapping("/checkPrice")
    public List<Brand> checkPrice()
    {
           return stockServiceImp.getAllBrandsByItemName("table");
    }

@PostMapping("/postItem")
public void createItem()
{
    stockServiceImp.createItems(new StockDTO());
}


@GetMapping("/checkpq")
public List<Brand> checkpq(StockDTO stockDTO)
{
    stockDTO.setItemName("soaps");

   return  stockServiceImp.getAllBrandsByItemName(stockDTO.getItemName());
}
@PostMapping("/brands")
    public ResponseEntity<?> getBrandsByItemName(@RequestBody Map<String, String> requestData) {
        String itemName = requestData.get("itemsName");

        List<Brand> brands = stockServiceImp.getAllBrandsByItemName(itemName);

        if (brands.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No brands found for item: " + itemName);
        }

        return ResponseEntity.ok(brands);
    }



}
