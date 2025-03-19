package com.manovikas.storestock.controller;

import com.manovikas.storestock.dao.BrandRepository;
import com.manovikas.storestock.dao.ItemRepository;
import com.manovikas.storestock.dao.PriceQuantityRepository;
import com.manovikas.storestock.dto.StockDTO;
import com.manovikas.storestock.entity.Brand;
import com.manovikas.storestock.entity.Item;
import com.manovikas.storestock.entity.PriceQuantity;
import com.manovikas.storestock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/updatedelete")
public class UpdateController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    PriceQuantityRepository priceQuantityRepository;

    @Autowired
    StockService stockService;


    @GetMapping("/update")
    public String update(Model model)
    {

        Iname=null;Bname=null;
        Pname=null;

        StockDTO stockDto=new StockDTO();


        stockDto.setItem(stockService.getAllItems());
        stockDto.setBrand(new ArrayList<>());
        stockDto.setPrice(new ArrayList<>());
        model.addAttribute("stockDto",stockDto);
        return "update-delete-stock/updatepage";
    }






    String Iname=null,Bname=null;
   BigDecimal Pname=null;
    @PostMapping("/saveupdate")
    public String saveupdate(@ModelAttribute StockDTO stockDto, Model model) {

        if(Iname==null)
        {
            Iname=stockDto.getItemName();
        }
        else {
            stockDto.setItemName(Iname);
        }
        System.out.println("item name is"+stockDto.getItemName());
        if (stockDto.getItemName() != null && !stockDto.getItemName().isEmpty()) {
            List<Brand> brands = stockService.getAllBrandsByItemName(stockDto.getItemName());
            System.out.println("its item table");
            if (brands.isEmpty()) {
                System.out.println("No brands found for item: " + stockDto.getItemName());
                model.addAttribute("errorMessage", "No brands found for selected item. so Go Back");
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
                    System.out.println("No brands found for item: " + stockDto.getBrandName());
                    model.addAttribute("errorMessage", "No prices found for selected brand so Go back.");
                }
                stockDto.setPrice(price);
                Bname=stockDto.getBrandName();
                stockDto.setItemName(Iname);
                model.addAttribute("stockDto", stockDto);

                System.out.println("priceItem name is"+stockDto.getPriceItem());
                if (stockDto.getPriceItem() != null ) {
                    System.out.println("its price table");
                    Pname=stockDto.getPriceItem();
                    stockDto.setItemName(Iname);
                    stockDto.setBrandName(Bname);
                    stockService.updateQuantity(stockDto);
                    Iname=null;Bname=null;
                    Pname=null;

                    return "redirect:/updatedelete/update";

                }


            }



        }

        return "update-delete-stock/updatepage";

    }



    @GetMapping("/reduce")
    public String reduce(Model model)
    {

        Itname=null;Btname=null;
        Ptname=null;

        StockDTO stockDto=new StockDTO();


        stockDto.setItem(stockService.getAllItems());
        stockDto.setBrand(new ArrayList<>());
        stockDto.setPrice(new ArrayList<>());
        model.addAttribute("stockDto",stockDto);
        return "update-delete-stock/reduceStock";
    }






    String Itname=null,Btname=null;
    BigDecimal Ptname=null;
    @PostMapping("/reducestock")
    public String reduceStock(@ModelAttribute StockDTO stockDto,Model model) {

        if(Itname==null)
        {
            Itname=stockDto.getItemName();
        }
        else {
            stockDto.setItemName(Itname);
        }
        System.out.println("item name is"+stockDto.getItemName());
        if (stockDto.getItemName() != null && !stockDto.getItemName().isEmpty()) {
            List<Brand> brands = stockService.getAllBrandsByItemName(stockDto.getItemName());
            System.out.println("its item table");
            if (brands.isEmpty()) {
                System.out.println("No brands found for item: " + stockDto.getItemName());
                model.addAttribute("errorMessage", "No brands found for selected item. so Go Back");
                model.addAttribute("stockDto", stockDto);
            }
            stockDto.setBrand(brands);
            Itname=stockDto.getItemName();

            System.out.println("Itname is "+Itname);
            model.addAttribute("stockDto", stockDto);


            System.out.println("Brand name is"+stockDto.getBrandName());
            if (stockDto.getBrandName() != null && !stockDto.getBrandName().isEmpty()) {

                System.out.println("its brand table");

                List<PriceQuantity> price = stockService.getAllPQfromBrand(stockDto);

                if (price.isEmpty()) {
                    System.out.println("No brands found for item: " + stockDto.getBrandName());
                    model.addAttribute("errorMessage", "No prices found for selected brand so Go back.");
                }
                stockDto.setPrice(price);
                Btname=stockDto.getBrandName();
                stockDto.setItemName(Itname);
                model.addAttribute("stockDto", stockDto);

                System.out.println("priceItem name is"+stockDto.getPriceItem());



                if (stockDto.getPriceItem() != null ) {
                    Optional<Item> i=itemRepository.findByItemName(stockDto.getItemName());
                    int itemid=i.get().getId();
                    Optional<Brand> b=brandRepository.findByBrandNameAndItemId(stockDto.getBrandName(),itemid);
                    int brandId=b.get().getId();

                    Optional<PriceQuantity> pq=priceQuantityRepository.findByPriceItemAndBrandId(stockDto.getPriceItem(),brandId);


                    if(pq.get().getQuantity()>=stockDto.getQuantity()) {
                        System.out.println("its price table");
                        Ptname = stockDto.getPriceItem();
                        stockDto.setItemName(Itname);
                        stockDto.setBrandName(Btname);
                        stockService.reduceQuantity(pq.get().getQuantity(),stockDto.getQuantity(),pq.get());
                        Itname = null;
                        Btname = null;
                        Ptname = null;

                        return "redirect:/updatedelete/reduce";
                    }
                    else {
                        System.out.println("enterred quantity is more than available: " + stockDto.getBrandName());
                        model.addAttribute("errorMessage", "enterred quantity is more than available:  so Go Back");

                    }
                }


            }



        }

        return "update-delete-stock/reduceStock";

    }





    @GetMapping("/avail")
    public String avail(Model model)
    {

        It1name=null;Bt1name=null;
        Pt1name=null;

        StockDTO stockDto=new StockDTO();


        stockDto.setItem(stockService.getAllItems());
        stockDto.setBrand(new ArrayList<>());
        stockDto.setPrice(new ArrayList<>());
        model.addAttribute("stockDto",stockDto);
        return "update-delete-stock/stockavail";
    }






    String It1name=null,Bt1name=null;
    Integer Pt1name=null;
    @PostMapping("/saveavail")
    public String availStock(@ModelAttribute StockDTO stockDto,Model model) {

        if(It1name==null)
        {
            It1name=stockDto.getItemName();
        }
        else {
            stockDto.setItemName(It1name);
        }
        System.out.println("item name is"+stockDto.getItemName());
        if (stockDto.getItemName() != null && !stockDto.getItemName().isEmpty()) {
            List<Brand> brands = stockService.getAllBrandsByItemName(stockDto.getItemName());
            System.out.println("its item table");
            if (brands.isEmpty()) {
                System.out.println("No brands found for item: " + stockDto.getItemName());
                model.addAttribute("errorMessage", "No brands found for selected item. so Go Back");
                model.addAttribute("stockDto", stockDto);
            }
            stockDto.setBrand(brands);
            It1name=stockDto.getItemName();

            System.out.println("Itname is "+It1name);
            model.addAttribute("stockDto", stockDto);


            System.out.println("Brand name is"+stockDto.getBrandName());
            if (stockDto.getBrandName() != null && !stockDto.getBrandName().isEmpty()) {

                System.out.println("its brand table");

                List<PriceQuantity> price = stockService.getAllPQfromBrand(stockDto);

                if (price.isEmpty()) {
                    System.out.println("No brands found for item: " + stockDto.getBrandName());
                    model.addAttribute("errorMessage", "No prices found for selected brand so Go back.");
                }
                stockDto.setPrice(price);
                Bt1name=stockDto.getBrandName();
                stockDto.setItemName(It1name);
                model.addAttribute("stockDto", stockDto);

                System.out.println("priceItem name is"+stockDto.getPriceItem());



                if (stockDto.getPriceItem() != null ) {
                    Optional<Item> i=itemRepository.findByItemName(stockDto.getItemName());
                    int itemid=i.get().getId();
                    Optional<Brand> b=brandRepository.findByBrandNameAndItemId(stockDto.getBrandName(),itemid);
                    int brandId=b.get().getId();

                    Optional<PriceQuantity> pq=priceQuantityRepository.findByPriceItemAndBrandId(stockDto.getPriceItem(),brandId);


                    stockDto.setQuantity(pq.get().getQuantity());

                        return "update-delete-stock/stockavail";
                    }

                }


            }

        return "update-delete-stock/stockavail";

        }

    @GetMapping("/services")
    public String firstpage(Model model)
    {
        return "services";
    }



    @RequestMapping("/sale")
    public String sales(Model model)
    {
        return "sale";
    }
}
