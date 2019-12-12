package com.company.customerservice.dao;

import com.company.customerservice.model.Customer;

import java.util.List;

public interface CustomerDao {

    Customer addCustomer(Customer customer);

    Customer getCustomerById(int customerId);

    List<Customer> getAllCustomers();

    void updateCustomer(Customer customer);

    void deleteCustomer(int customerId);

}
