package com.company.adminservice.util;

import com.company.adminservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerServiceClient {

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
    Customer fetchCustomerById(@PathVariable int customerId);

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    List<Customer> fetchAllCustomers();

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    Customer addNewCustomer(@RequestBody Customer customer);

    @RequestMapping(value = "/customers", method = RequestMethod.PUT)
    void updateCustomer(@RequestBody Customer customer);

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE)
    void deleteCustomer(@PathVariable int customerId);

}
