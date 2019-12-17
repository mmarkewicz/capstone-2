package com.company.retailapiservice.feign;

import com.company.retailapiservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerServiceFeign {

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
    Customer fetchCustomerById(@PathVariable int customerId);

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    List<Customer> fetchAllCustomers();

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    Customer addNewCustomer(@RequestBody Customer customer);

    @RequestMapping(value = "/customers", method = RequestMethod.PUT)
    void updateCustomer(@RequestBody Customer customer);

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE)
    void updateCustomer(@PathVariable int customerId);

}
