package com.manovikas.storestock.controller;


import com.manovikas.storestock.dao.UserRepository;
import com.manovikas.storestock.dto.EmployeeDTO;
import com.manovikas.storestock.dto.StockDTO;
import com.manovikas.storestock.entity.Employee;
import com.manovikas.storestock.entity.Item;
import com.manovikas.storestock.entity.UsernamePassword;
import com.manovikas.storestock.service.EmployeeService;
import com.manovikas.storestock.service.UserService;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/addpage")
    public String loginPage(Model model)
    {
        EmployeeDTO employeeDto=new EmployeeDTO();
        model.addAttribute("employeeDto",employeeDto);

        return "employeehtml/addemployee";
    }
    @PostMapping("/save")
    public String save(EmployeeDTO employeeDto,Model model)
    {
        Optional<UsernamePassword> usernamePassword=userRepository.findByUsername(employeeDto.getUsername());
        if(usernamePassword.isPresent())
        {
            model.addAttribute("errorMessage", "username name already exists!");
            return "employeehtml/addemployee";
        }

        userService.addEmployee(employeeDto);
        return "redirect:/employee/addpage";

    }

    @GetMapping("/list")
    public String Listpage(Model model){
        List<EmployeeDTO> ll=employeeService.getAllEmployees();
        model.addAttribute("employeelist",ll);
        return "employeehtml/emplist";
    }

    @GetMapping("/delete")
    public String deleteemployee(Model model) {
        List<EmployeeDTO> listofEmployee = employeeService.getAllEmployees();
        model.addAttribute("emplist", listofEmployee);
        model.addAttribute("employeeDTO", new EmployeeDTO()); // Add a single employee object for the form
        return "employeehtml/deleteemployee";
    }
    @PostMapping("/deleteemp")
    public String postDeleteItem(@ModelAttribute EmployeeDTO employeeDTO, Model model)
    {
        Optional<UsernamePassword> deleteEmp=userRepository.findByUsername(employeeDTO.getUsername());

        userService.deleteEmp(employeeDTO);


        return "redirect:/employee/delete";
    }

    @GetMapping("/loginpage")
    public String login()
    {
        return "loginpage";
    }
    @RequestMapping("/emppage")
    public String Emppage()
    {
        return "employeepage";
    }

    @GetMapping("/")
    public String redirectToStore() {
        return "redirect:/store/";
    }


}
