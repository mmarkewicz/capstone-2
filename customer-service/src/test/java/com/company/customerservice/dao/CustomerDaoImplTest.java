package com.company.customerservice.dao;

import com.company.customerservice.dao.CustomerDao;
import com.company.customerservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoImplTest {

    @Autowired
    CustomerDao customerDao;

    private Customer customer1;

    @Before
    public void setup() throws Exception {
        clearDatabase();
        customer1 = setupTestObject();
    }

    public void clearDatabase() {
        List<Customer> customerList = customerDao.getAllCustomers();

        for(Customer c : customerList){
            customerDao.deleteCustomer(c.getCustomerId());
        }
    }

    public Customer setupTestObject() {

        Customer customer1 = new Customer();
        customer1.setFirstName("Tchatcho");
        customer1.setLastName("Sandjong");
        customer1.setCity("Edison");
        customer1.setStreet("Freeman St.");
        customer1.setZip("07050");
        customer1.setEmail("tsandjong@yahoo.fr");
        customer1.setPhone("2375702528");

        return customer1;
    }

    @Test
    public void shouldAddGetDeleteCustomer(){

        customer1 = customerDao.addCustomer(customer1); // Add customer to database

        Customer customer2 = customerDao.getCustomerById(customer1.getCustomerId()); // Get customer from datatbase

        assertEquals(customer2, customer1); // Test add and get methods

        customerDao.deleteCustomer(customer1.getCustomerId()); // Delete customer from database

        assertNull(customerDao.getCustomerById(customer1.getCustomerId())); // Test delete method

    }

    @Test
    public void shouldGetAllCustomers(){

        customer1 = customerDao.addCustomer(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Larissa");
        customer2.setLastName("Sandjong");
        customer2.setCity("Edison");
        customer2.setStreet("Freeman St.");
        customer2.setZip("07050");
        customer2.setEmail("tsandjong@yahoo.fr");
        customer2.setPhone("2375702528");

        customer2 = customerDao.addCustomer(customer2);

        List<Customer> customerList = customerDao.getAllCustomers();

        assertEquals(2, customerList.size());

    }

    @Test
    public void shouldUpdateConsole(){

        customer1 = customerDao.addCustomer(customer1);

        customer1.setFirstName("Freddy");
        customer1.setLastName("Sandjong");
        customer1.setCity("Edison");
        customer1.setStreet("Freeman St.");
        customer1.setZip("07050");
        customer1.setEmail("tsandjong@yahoo.fr");
        customer1.setPhone("2375702528");

        customerDao.updateCustomer(customer1);

        Customer customer2 = customerDao.getCustomerById(customer1.getCustomerId());

        assertEquals(customer2, customer1);
    }

}
