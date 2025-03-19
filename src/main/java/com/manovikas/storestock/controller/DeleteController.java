package com.manovikas.storestock.controller;

import com.manovikas.storestock.dao.BrandRepository;
import com.manovikas.storestock.dao.ItemRepository;
import com.manovikas.storestock.dao.PriceQuantityRepository;
import com.manovikas.storestock.dto.StockDTO;
import com.manovikas.storestock.entity.Brand;
import com.manovikas.storestock.entity.Item;
import com.manovikas.storestock.entity.PriceQuantity;
import com.manovikas.storestock.service.DeleteService;
import com.manovikas.storestock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/delete")
public class DeleteController {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    PriceQuantityRepository priceQuantityRepository;
    @Autowired
    StockService stockService;

    @Autowired
    DeleteService deleteService;
    @GetMapping("/item")
    public String deleteItem(Model theModel)
    {
        StockDTO stockDto=new StockDTO();
        List<Item> listofItems=itemRepository.findAll();
        if(listofItems==null){
            theModel.addAttribute("errorMessage","the list is empty");
        }

        stockDto.setItem(listofItems);

        theModel.addAttribute("stockDto",stockDto );
        // System.out.println(theItem.toString());

        return "deletestockdir/deleteitem";
    }
    @PostMapping("/deleteItem")
    public String postDeleteItem(@ModelAttribute StockDTO stockDto, Model model)
    {
       Optional<Item> i=itemRepository.findByItemName(stockDto.getItemName());

        deleteService.deleteItem(i.get());

        return "redirect:/delete/item";
    }






    @GetMapping("/brand")
    public String createPriceAndQuantity(Model model)
    {
        Itemname=null;
        StockDTO stockDto=new StockDTO();

        stockDto.setItem(stockService.getAllItems());
        stockDto.setBrand(new ArrayList<>());

        model.addAttribute("stockDto",stockDto);


        return "deletestockdir/deletebrand";
    }

    String Itemname=null;
    @PostMapping("/deletebrand")
    public String deleteBrand(@ModelAttribute StockDTO stockDto,Model model)
    {

        if (stockDto.getItemName() != null && !stockDto.getItemName().isEmpty()) {
            List<Brand> brands = stockService.getAllBrandsByItemName(stockDto.getItemName());

            if (brands.isEmpty()) {
                System.out.println("No brands found for item: " + stockDto.getItemName());
                model.addAttribute("errorMessage", "No brands found for selected item.");
            }
            stockDto.setBrand(brands);
            Itemname=stockDto.getItemName();
            model.addAttribute("stockDto", stockDto);

            return "deletestockdir/deletebrand";
        }
        stockDto.setItemName(Itemname);
        System.out.println("Item name is"+stockDto.getItemName());
        deleteService.deleteBrand(stockDto);
        Itemname=null;
        return "redirect:/delete/brand";

    }

    @GetMapping("/price")
    public String deleteprice(Model model)
    {
        Iname = null;
        Bname = null;
        Pname = null;
        StockDTO stockDto=new StockDTO();


        stockDto.setItem(stockService.getAllItems());
        stockDto.setBrand(new ArrayList<>());
        stockDto.setPrice(new ArrayList<>());
        model.addAttribute("stockDto",stockDto);
        return "deletestockdir/deleteprice";
    }


    String Iname=null,Bname=null;
    Integer Pname=null;
    @PostMapping("/deleteprice")
    public String deleteprice(@ModelAttribute("stockDto") StockDTO stockDto,Model model) {

        if(Iname==null)
        {
            Iname=stockDto.getItemName();
        }
        else {
            stockDto.setItemName(Iname);
        }

        if (stockDto.getItemName() != null && !stockDto.getItemName().isEmpty()) {
            List<Brand> brands = stockService.getAllBrandsByItemName(stockDto.getItemName());
            System.out.println("its item table");
            if (brands.isEmpty()) {
                System.out.println("No brands found for item: " + stockDto.getItemName());
                model.addAttribute("errorMessage", "No brands found for selected item.");
                model.addAttribute("stockDto", stockDto);
            }
            stockDto.setBrand(brands);
            Iname=stockDto.getItemName();

            System.out.println("Iname is "+Iname);
            model.addAttribute("stockDto", stockDto);


            System.out.println("Brand name is"+stockDto.getBrandName());
            if (stockDto.getBrandName() != null && !stockDto.getBrandName().isEmpty()) {

                System.out.println("its brand table");
                List<PriceQuantity> price = stockService.getAllPQfromBrand(stockDto);

                if (price.isEmpty()) {
                    System.out.println("No price found for brand: " + stockDto.getBrandName());
                    model.addAttribute("errorMessage", "No price found for selected brand so Refresh.");
                }
                stockDto.setPrice(price);
                Bname = stockDto.getBrandName();
                stockDto.setItemName(Iname);
                model.addAttribute("stockDto", stockDto);

                stockDto.setBrandName(Bname);
                stockDto.setItemName(Iname);

                if (stockDto.getPriceItem() != null) {
                    System.out.println("hii");
                    System.out.println("the price is" + stockDto.getPriceItem());
                    deleteService.deletePrice(stockDto);

                    Iname = null;
                    Bname = null;
                    Pname = null;
                    return "redirect:/delete/price";

                }


            }


        }

        return "deletestockdir/deleteprice";

    }


    @RequestMapping("/deletestock")
public String DeletePage()
{
    return "deletestock";
}

}
