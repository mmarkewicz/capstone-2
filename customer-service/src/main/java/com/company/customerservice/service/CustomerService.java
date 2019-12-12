package com.company.customerservice.service;

import com.company.customerservice.dao.CustomerDao;
import com.company.customerservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class CustomerService {

    CustomerDao customerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer findCustomerById(int id){
        return customerDao.getCustomerById(id);
    }

    public List<Customer> findAllCustomers(){
        return customerDao.getAllCustomers();
    }

    public Customer createCustomer(Customer customer){
        if (customer == null) {
            throw new IllegalArgumentException("You must enter a customer");
        }

        return customerDao.addCustomer(customer);
    }

    public void updateCustomer(Customer customer){
        customerDao.updateCustomer(customer);
    }

    public void deleteCustomer(int customerId){
        customerDao.deleteCustomer(customerId);
    }

}
