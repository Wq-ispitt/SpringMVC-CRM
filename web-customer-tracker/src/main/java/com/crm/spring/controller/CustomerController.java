package com.crm.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crm.spring.dao.CustomerDAO;
import com.crm.spring.entity.Customer;
import com.crm.spring.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //inject customer service
	@Autowired
	private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel) {

        //get customers from the service, delegate calls to service
        List<Customer> theCustomers = customerService.getCustomers();

        //add customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "list-customer";
    }
    
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
    	//create model attribute to bind form data
    	Customer theCus = new Customer();
    	theModel.addAttribute("customer", theCus);
    	
    	return "customer-form";
    }
    
    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCust) {
    	//save the customer using service
    	customerService.saveCustomer(theCust);
    	
		return "redirect:/customer/list";
    	
    }
    
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
    	//get the cust from serv
    	Customer theCust = customerService.getCustomer(theId);
    	//set cust as a model attr to pre-populate the form
    	theModel.addAttribute("customer", theCust);
    	//send to our form
    	return "customer-form";
    }
    
    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {
    	//delete the cust
    	customerService.deleteCustomer(theId);
    	
    	return "redirect:/customer/list";
    }
    
    @GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearNm, Model theModel) {
    	//search from serv
    	List<Customer> theCusts = customerService.searchCustomers(theSearNm);
    	
    	theModel.addAttribute("customers", theCusts);
    	
		return "list-customer";
    	
    }

}