package com.manovikas.storestock.controller;

import com.manovikas.storestock.dao.ItemRepository;
import com.manovikas.storestock.dto.StockDTO;
import com.manovikas.storestock.entity.Brand;
import com.manovikas.storestock.entity.Item;
import com.manovikas.storestock.service.ListService;
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
@RequestMapping("/list")
public class ListController {

    @Autowired
    ListService listService;
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    StockService stockService;

    @GetMapping("/item")
    public String itemList(Model model)
    {
        StockDTO stockDto=new StockDTO();
        List<Item> listofItems=itemRepository.findAll();
        if(listofItems==null){
            model.addAttribute("errorMessage","the list is empty");
        }

        stockDto.setItem(listofItems);

        model.addAttribute("stockDto",stockDto );
        return "listdirectory/itemlist";

    }

    @PostMapping("/listitem")
    public String getListByItem(@ModelAttribute StockDTO stockDto,Model model)
    {
        List<StockDTO> stocks=listService.getStockByItem(stockDto);

        if(stocks == null || stocks.isEmpty()) {
            model.addAttribute("errorMessage", "No items found please go back");
        }
        model.addAttribute("stocks",stocks);
        model.addAttribute("stockDto",stockDto );
        return "listdirectory/itemlist";
    }




    @GetMapping("/brand")
    public String getListByBrand(Model model)
    {
        Itemname=null;
        StockDTO stockDto=new StockDTO();

        stockDto.setItem(stockService.getAllItems());
        stockDto.setBrand(new ArrayList<>());

        model.addAttribute("stockDto",stockDto);


        return "listdirectory/brandlist";
    }

    String Itemname=null;
    @PostMapping("/listbrand")
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

            return "listdirectory/brandlist";
        }
        stockDto.setItemName(Itemname);


        List<StockDTO> stocks=listService.getStockByBrand(stockDto);

        if(stocks == null || stocks.isEmpty()) {
            model.addAttribute("errorMessage", "No items found please go back");
        }
        model.addAttribute("stocks",stocks);
        model.addAttribute("stockDto",stockDto );

        return "listdirectory/brandlist";


    }














    @GetMapping("/all")
    public String completeList(Model model)
    {

        List<StockDTO> stockList = listService.getStockDetails();
        model.addAttribute("stocks", stockList);

        return "listdirectory/totalList";

    }
    @GetMapping("/empty")
    public String EmptyList(Model model)
    {

        List<StockDTO> stockList = listService.getEmptyStockDetails();
        model.addAttribute("stocks", stockList);

        return "listdirectory/emptystock";

    }
    @RequestMapping ("/listpage")
    public String itemPage(Model model)
    {

        return "list";

    }

}
