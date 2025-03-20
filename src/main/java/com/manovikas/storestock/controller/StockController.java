

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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/store")
public class StockController {

    StockService stockService;

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    PriceQuantityRepository priceQuantityRepository;
    @Autowired
    public StockController(StockService stockService)
    {
        this.stockService=stockService;
    }
   //
   // @PostMapping("/createItem")
    @GetMapping("/createitem")
    public String createItem(Model theModel)
    {

        theModel.addAttribute("stockDTO",new StockDTO());

       // System.out.println(theItem.toString());

        return "stock/createitem";
    }
    @PostMapping("/saveItem")
    public String saveItem12(@ModelAttribute StockDTO stockDTO,Model model)
    {
        Optional<Item> existingItem = itemRepository.findByItemName(stockDTO.getItemName());
        if (existingItem.isPresent()) {
            model.addAttribute("errorMessage", "Item name already exists!");
            return "stock/createitem"; // Return to the same form page with the error message
        }
        stockService.createItems(stockDTO);

        return "redirect:/store/createitem";
    }






    @GetMapping("/createBrandAndPQ")
    public String createBrandAndPQ(Model theModel)
    {
       StockDTO stockDto=new StockDTO();

       stockDto.setItem(stockService.getAllItems());
        System.out.println("the Items are"+stockDto.getItem());
        theModel.addAttribute("stockDto",stockDto);


        return "stock/createBrandAndPQ";
    }
    @PostMapping("/saveBrand")
    public String saveBrand(@ModelAttribute StockDTO stockDto,Model model)
    {
        if (stockDto == null) {
            System.out.println("ERROR: stockDto is NULL!");
        } else {
            System.out.println("Brand Name: " + stockDto.getBrandName());
        }

        System.out.println("the item name is "+stockDto.getItemName());
        Optional<Item> existingItem = itemRepository.findByItemName(stockDto.getItemName());

        if (existingItem.isPresent()) {
            Item i=existingItem.get();
            int itemId=i.getId();
            System.out.println("the brand name is "+stockDto.getBrandName());
            Optional<Brand> extistingBrand=brandRepository.findByBrandNameAndItemId(stockDto.getBrandName(),itemId);

            if(extistingBrand.isPresent())
            {
                System.out.println("in");
                model.addAttribute("errorMessage", "Brand name already exists! So Refresh ");
                model.addAttribute("stockDto", stockDto);
                return "stock/createBrandAndPQ"; // Return to the same form page with the error message
            }
            // Return to the same form page with the error message
        }

        System.out.println("savebrand ");
        stockService.createBrand(stockDto);
        return "redirect:/store/createBrandAndPQ";
    }











    @GetMapping("createPriceAndQuantity")
    public String createPriceAndQuantity(Model model)
    {
        Itemname=null;
        StockDTO stockDto=new StockDTO();

        stockDto.setItem(stockService.getAllItems());
        stockDto.setBrand(new ArrayList<>());

        model.addAttribute("stockDto",stockDto);


        return "stock/createPriceAndQuantity";
    }


String Itemname=null;
    @PostMapping("/savePrice")
    public String savePrice(@ModelAttribute("stockDto") StockDTO stockDto,Model model) {

        if (stockDto.getItemName() != null && !stockDto.getItemName().isEmpty()) {
            List<Brand> brands = stockService.getAllBrandsByItemName(stockDto.getItemName());

            if (brands.isEmpty()) {
                System.out.println("No brands found for item: " + stockDto.getItemName());
                model.addAttribute("brandError", "No brands found for selected item.");
            }
            stockDto.setBrand(brands);
            Itemname=stockDto.getItemName();
            model.addAttribute("stockDto", stockDto);

            return "stock/createPriceAndQuantity";
            // return "createPriceAndQuantity";
        }
       stockDto.setItemName(Itemname);
     //   System.out.println(Itemname);
        Optional<Item> i=itemRepository.findByItemName(Itemname);
        int itemId=i.get().getId();

        Optional<Brand> b=brandRepository.findByBrandNameAndItemId(stockDto.getBrandName(),itemId);
        int brandId=b.get().getId();
        Optional<PriceQuantity> p=priceQuantityRepository.findByPriceItemAndBrandId(stockDto.getPriceItem(),brandId);
        if(p.isPresent())
        {
            model.addAttribute("errorMessage", "Brand with that price already exists! So Refresh ");
            model.addAttribute("stockDto", stockDto);
            return "stock/createPriceAndQuantity";
        }

        stockService.createPriceAndQuantity(stockDto);

        Itemname=null;
        return "redirect:/store/createPriceAndQuantity";

    }









    @GetMapping("/home")
    public String home123(Model model)
    {
        return "home";
    }


    @GetMapping("/")
    public String firstpage(Model model)
    {
        return "first_page";
    }
    @GetMapping("/")
    public String redirectToStore() {
        return "redirect:/store/"; // ✅ Redirect root URL to /store/
    }
    @GetMapping("/createstock1")
    public String createstock1(Model model)
    {
        return "createstock1";
    }

   /*
    String Iname,Bname;
            Iname = stockDto.getItemName();
    System.out.println("The selected item name is: " + stockDto.getItemName());
    if (stockDto.getItemName() != null && !stockDto.getItemName().isEmpty()) {
        List<Brand> brands = stockService.getAllBrandsByItemName(stockDto.getItemName());

        if (brands.isEmpty()) {
            System.out.println("No brands found for item: " + stockDto.getItemName());
            model.addAttribute("brandError", "No brands found for selected item.");
        }
        stockDto.setBrand(brands);
        System.out.println(stockDto.getBrand());
        stockDto.setItemName(Iname);
        model.addAttribute("stockDto", stockDto);
        System.out.println(stockDto.getBrand());
        //    stockDto.setItemName(stockDto.);
        //  stockDto.setItemName(sk);
        System.out.println("returing from if");
        System.out.println("1");

        return "updatepage";
        // return "createPriceAndQuantity";
    }
    else {
         Bname = stockDto.getBrandName();
        System.out.println("The selected brand name is: " + stockDto.getBrandName());
        if (stockDto.getBrandName() != null && !stockDto.getBrandName().isEmpty()) {
            List<PriceQuantity> price = stockService.getAllPQfromBrand(stockDto.getBrandName());

            if (price.isEmpty()) {
                System.out.println("No brands found for item: " + stockDto.getBrandName());
                model.addAttribute("brandError", "No brands found for selected item.");
            }
            stockDto.setPrice(price);
            System.out.println(stockDto.price);

            stockDto.setItemName(Iname);
            stockDto.setBrandName(Bname);
            model.addAttribute("stockDto", stockDto);
            System.out.println(stockDto.getPrice());
            //    stockDto.setItemName(stockDto.);
            //  stockDto.setItemName(sk);
            System.out.println("returing from if");
            System.out.println("1");

            return "updatepage";

        }
        else {
            System.out.println("3");
            System.out.println("submiting");
            System.out.println("after brands" + stockDto.getPriceItem());
            // System.out.println("the dto is " + stockDto.getItemName() + stockDto.getPriceItem() + stockDto.getQuantity());

            stockService.updateQuantity(stockDto);
            return "redirect:/store/update";

        }
    }
*/
  /*
        String sk = stockDto.getItemName();
        System.out.println("The selected item name is: " + stockDto.getItemName());
        if (stockDto.getItemName() != null && !stockDto.getItemName().isEmpty()) {
            List<Brand> brands = stockService.getAllBrandsByItemName(stockDto.getItemName());

            if (brands.isEmpty()) {
                System.out.println("No brands found for item: " + stockDto.getItemName());
                model.addAttribute("brandError", "No brands found for selected item.");
            }
            StockDTO stockDto123=new StockDTO();
            stockDto123.setBrand(brands);

            System.out.println(stockDto.getBrand());

            System.out.println(stockDto123.getBrand());
            //    stockDto.setItemName(stockDto.);
            //  stockDto.setItemName(sk);
            System.out.println("returing from if");
            System.out.println("1");
            model.addAttribute("stockDto123", stockDto123);
            // return "createPriceAndQuantity";
        }
       return "createPriceAndQuantity";
    }
         */
       /*
        System.out.println("the item name is "+stockDto.getItemName());

        stockDto.setBrand(stockService.getAllBrandsByItemName(stockDto.getItemName()));

        if (stockDto.getBrand().isEmpty()) {
            System.out.println("No brands found for item: " + stockDto.getItemName());
            throw new RuntimeException("BrandNotFound");
            // model.addAttribute("brandError", "No brands found for selected item.");
        }
        stockDto.setItem(stockService.getAllItems());
        System.out.println("before");

        model.addAttribute("stockDto", stockDto);
        System.out.println("the brands are"+stockDto.getBrand());
        stockService.createPriceAndQuantity(stockDto);

        return "/createPriceAndQuantity";

         */






}
