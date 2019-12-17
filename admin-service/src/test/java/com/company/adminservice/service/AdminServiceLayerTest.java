package com.company.adminservice.service;

import com.company.adminservice.model.*;
import com.company.adminservice.util.CustomerServiceClient;
import com.company.adminservice.util.InventoryServiceClient;
import com.company.adminservice.util.LevelUpClient;
import com.company.adminservice.util.ProductServiceClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceLayerTest {

    // Testing product service methods
    @MockBean
    ProductServiceClient productServiceClient;

    @MockBean
    CustomerServiceClient customerServiceClient;

    @MockBean
    LevelUpClient levelUpClient;

    @MockBean
    InventoryServiceClient inventoryServiceClient;

    @Autowired
    AdminServiceLayer adminService;

    private ProductViewModel product;

    private CustomerViewModel customer;

    private LevelUpViewModel levelUp;

    private InventoryViewModel inventory;

    @Before
    public void setUp(){
        setUpProductDaoMock();
        setUpCustomerDaoMock();
        setUpLevelMock();
        setUpInventoryMock();

        product = new ProductViewModel();
        product.setProductName("Iphone-5");
        product.setProductDescription("Smart Phone");
        product.setListPrice(new BigDecimal(99.99).setScale(2, RoundingMode.HALF_UP));
        product.setUnitCost(new BigDecimal(45.99).setScale(2, RoundingMode.HALF_UP));

        customer = new CustomerViewModel();
        customer.setFirstName("Tchatcho");
        customer.setLastName("Sandjong");
        customer.setCity("Edison");
        customer.setStreet("Freeman St.");
        customer.setZip("07050");
        customer.setEmail("tsandjong@yahoo.fr");
        customer.setPhone("2375702528");


        levelUp = new LevelUpViewModel();
        levelUp.setLevel_up_id(1);
        levelUp.setCustomer_id(1);
        levelUp.setPoints(100);
        levelUp.setMember_date(LocalDate.of(2019, 1, 1));

        inventory = new InventoryViewModel();
        inventory.setProductId(1);
        inventory.setQuantity(10);
    }

    @Test
    public void shouldAddAndFetchProduct(){
        product = adminService.addNewProduct(product);
        assertEquals(product, adminService.fetchProductById(product.getProductId()));
    }

    @Test
    public void shouldFindAllProducts(){
        List<ProductViewModel> actualList = adminService.fetchAllProducts();
        assertEquals(2, actualList.size());
    }


    public void setUpProductDaoMock(){

        Product product1 = new Product();
        product1.setProductName("Iphone-5");
        product1.setProductDescription("Smart Phone");
        product1.setListPrice(new BigDecimal(99.99).setScale(2, RoundingMode.HALF_UP));
        product1.setUnitCost(new BigDecimal(45.99).setScale(2, RoundingMode.HALF_UP));
        product1.setProductId(1);

        Product product2 = new Product();
        product2.setProductName("Iphone-5");
        product2.setProductDescription("Smart Phone");
        product2.setListPrice(new BigDecimal(99.99).setScale(2, RoundingMode.HALF_UP));
        product2.setUnitCost(new BigDecimal(45.99).setScale(2, RoundingMode.HALF_UP));

        doReturn(product1).when(productServiceClient).addNewProduct(product2);
        doReturn(product1).when(productServiceClient).fetchProductById(1);

        Product product3 = new Product();
        product3.setProductName("Iphone-4");
        product3.setProductDescription("Smart Phone");
        product3.setListPrice(new BigDecimal(69.99).setScale(2, RoundingMode.HALF_UP));
        product3.setUnitCost(new BigDecimal(35.99).setScale(2, RoundingMode.HALF_UP));
        product3.setProductId(2);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product3);

        doReturn(productList).when(productServiceClient).fetchAllProducts();

    }


    // Testing Customer Service


    @Test
    public void shouldAddAndFetchCustomer(){
        customer = adminService.addNewCustomer(customer);
        assertEquals(customer, adminService.fetchCustomerById(customer.getCustomerId()));
    }

    @Test
    public void shouldFindAllCustomers(){
        List<CustomerViewModel> actualList = adminService.fetchAllCustomers();
        assertEquals(2, actualList.size());
    }


    public void setUpCustomerDaoMock(){

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

        doReturn(customer1).when(customerServiceClient).addNewCustomer(customer2);
        doReturn(customer1).when(customerServiceClient).fetchCustomerById(customer1.getCustomerId());

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

        doReturn(customerList).when(customerServiceClient).fetchAllCustomers();

    }


    //Level up service

    @Test
    public void shouldAddAndFetchLevelUpById() throws Exception {

        levelUp = adminService.addNewLeveup(levelUp);

        assertEquals(levelUp, adminService.fetchLevelUpById(levelUp.getLevel_up_id()));
    }

    @Test
    public void shouldReturnLevelUpListFromFetchAllLevelUps() throws Exception {

        List<LevelUpViewModel> levelUpList = adminService.fetchAllLevelUps();
        assertEquals(levelUpList.toString(), adminService.fetchAllLevelUps().toString());
    }


    private void setUpLevelMock() {

        LevelUp levelUp = new LevelUp();
        levelUp.setLevel_up_id(1);
        levelUp.setCustomer_id(1);
        levelUp.setPoints(100);
        levelUp.setMember_date(LocalDate.of(2019, 1, 1));

        List<LevelUp> levelUpList = new ArrayList<>();
        levelUpList.add(levelUp);

        doReturn(levelUp).when(levelUpClient).getLevelUp(anyInt());
        doReturn(levelUpList).when(levelUpClient).getAllLevelUps();
        doReturn(levelUp).when(levelUpClient).postLevelUp(any(LevelUp.class));
    }


    //Inventory service

    @Test
    public void shouldAddAndFetchInventoryById() throws Exception {

        inventory = adminService.addNewInventory(inventory);
        assertEquals(inventory, adminService.fetchInventoryById(inventory.getId()));
    }


    @Test
    public void shouldReturnInventoryListFromGetAllInventories() throws Exception {

        List<InventoryViewModel> inventoryList = adminService.fetchAllInventories();
        assertEquals(inventoryList.toString(), adminService.fetchAllInventories().toString());
    }

    private void setUpInventoryMock() {
        Inventory inventory1 = new Inventory();
        inventory1.setId(1);
        inventory1.setProductId(1);
        inventory1.setQuantity(10);

        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(inventory1);

        doReturn(inventory1).when(inventoryServiceClient).getInventoryById(anyInt());
        doReturn(inventory1).when(inventoryServiceClient).postInventory(any(Inventory.class));
        doReturn(inventoryList).when(inventoryServiceClient).getAllInventories();
    }

}
