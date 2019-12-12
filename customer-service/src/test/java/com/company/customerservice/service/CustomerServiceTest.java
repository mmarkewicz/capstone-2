package com.company.customerservice.service;


import com.company.customerservice.dao.CustomerDao;
import com.company.customerservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @MockBean
    CustomerDao customerDao;

    @Autowired
    CustomerService customerService;

    private Customer customer;

    @Before
    public void setUp(){
        setUpDaoMock();

        customer = new Customer();
        customer.setFirstName("Tchatcho");
        customer.setLastName("Sandjong");
        customer.setCity("Edison");
        customer.setStreet("Freeman St.");
        customer.setZip("07050");
        customer.setEmail("tsandjong@yahoo.fr");
        customer.setPhone("2375702528");
    }

    @Test
    public void shouldCreateAndFindCustomer(){
        customer = customerService.createCustomer(customer);
        assertEquals(customer, customerService.findCustomerById(customer.getCustomerId()));
    }

    @Test
    public void shouldFindAllCustomers(){
        List<Customer> actualList = customerService.findAllCustomers();
        assertEquals(2, actualList.size());
    }


    public void setUpDaoMock(){

        Customer customer1 = new Customer();
        customer1.setFirstName("Tchatcho");
        customer1.setLastName("Sandjong");
        customer1.setCity("Edison");
        customer1.setStreet("Freeman St.");
        customer1.setZip("07050");
        customer1.setEmail("tsandjong@yahoo.fr");
        customer1.setPhone("2375702528");
        customer1.setCustomerId(1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Tchatcho");
        customer2.setLastName("Sandjong");
        customer2.setCity("Edison");
        customer2.setStreet("Freeman St.");
        customer2.setZip("07050");
        customer2.setEmail("tsandjong@yahoo.fr");
        customer2.setPhone("2375702528");

        doReturn(customer1).when(customerDao).addCustomer(customer2);
        doReturn(customer1).when(customerDao).getCustomerById(1);

        Customer customer3 = new Customer();
        customer3.setFirstName("Larissa");
        customer3.setLastName("Sandjong");
        customer3.setCity("Edison");
        customer3.setStreet("Freeman St.");
        customer3.setZip("07050");
        customer3.setEmail("tsandjong@yahoo.fr");
        customer3.setPhone("2375702528");
        customer3.setCustomerId(2);

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer3);

        doReturn(customerList).when(customerDao).getAllCustomers();

    }

}
