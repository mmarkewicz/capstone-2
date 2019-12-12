package com.company.customerservice.controller;

import com.company.customerservice.model.Customer;
import com.company.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerServiceController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer fetchCustomerById(@PathVariable int customerId){
        return customerService.findCustomerById(customerId);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> fetchAllCustomers(){
        return customerService.findAllCustomers();
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addNewCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCustomer(@RequestBody Customer customer){
        customerService.updateCustomer(customer);
    }

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable int customerId){
        customerService.deleteCustomer(customerId);
    }


}
