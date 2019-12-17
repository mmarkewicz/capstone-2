package com.company.retailapiservice.service;

import com.company.retailapiservice.feign.*;
import com.company.retailapiservice.model.*;
import com.company.retailapiservice.util.message.LevelUpEntry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RetailAPIServiceTest {

    RetailAPIService service;

    @Mock
    RabbitTemplate rabbitTemplate;

    @Mock
    InvoiceServiceFeign invoiceFeign;

    @Mock
    ProductServiceFeign productFeign;

    @Mock
    LevelUpServiceFeign levelUpFeign;

    @Mock
    InventoryServiceFeign inventoryFeign;

    @Mock
    CustomerServiceFeign customerFeign;

    @Before
    public void setUp() {
        service = new RetailAPIService(rabbitTemplate, invoiceFeign, productFeign, levelUpFeign, inventoryFeign, customerFeign);
        setUpMocks();
    }

    @Test
    public void shouldReturnInvoiceViewModelFromAddInvoice() {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setQuantity(10);
        invoiceItem.setUnitPrice(new BigDecimal(10.00).setScale(2, RoundingMode.HALF_EVEN));
        invoiceItem.setInventory_id(1);

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        invoiceItems.add(invoiceItem);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setInvoiceItems(invoiceItems);
        invoiceViewModel.setCustomerId(1);
        invoiceViewModel.setPurchaseDate(LocalDate.of(2019, 1, 1));

        InvoiceViewModel invoiceViewModelWithId = new InvoiceViewModel();
        invoiceViewModel.set
        assertEquals(service.addInvoice(invoic))
    }

    @Test
    public void shouldReturnInvoiceViewModelFromGetInvoiceById() {

    }

    @Test
    public void shouldReturnListInvoiceViewModelsFromGetAllInvoices() {

    }

    @Test
    public void shouldReturnListInvoicesFromGetInvoicesByCustomerId() {

    }

    @Test
    public void shouldReturnListOfProductsFromGetProductsInInventory() {

    }

    @Test
    public void shouldReturnProductFromGetProductById() {

    }

    @Test
    public void shouldReturnListOfProductsFromGetProductsByInvoiceId() {

    }

    private void setUpMocks() {
        Customer customer = new Customer();
        customer.setCustomerId(123);
        customer.setFirstName("Test");
        customer.setLastName("Test");
        customer.setPhone("Test");
        customer.setCity("Test");
        customer.setStreet("Test");
        customer.setZip("Test");
        customer.setEmail("Test");
        doReturn(customer).when(customerFeign).fetchCustomerById(anyInt());

        Inventory inventory = new Inventory();
        inventory.setId(1);
        inventory.setQuantity(100);
        inventory.setProductId(1);
        doReturn(inventory).when(inventoryFeign).getInventoryById(anyInt());
        doNothing().when(inventoryFeign).updateInventory(any(Inventory.class));

        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoice.setPurchaseDate(LocalDate.of(2019, 1, 1));
        invoice.setCustomerId(1);
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);
        doReturn(invoice).when(invoiceFeign).getInvoiceById(anyInt());
        doReturn(invoiceList).when(invoiceFeign).getAllInvoices();
        doReturn(invoice).when(invoiceFeign).getInvoicesByCustomerId(anyInt());
        doReturn(invoice).when(invoiceFeign).postInvoice(any(InvoiceViewModel.class));

        Product product = new Product();
        product.setProductId(1);
        product.setProductDescription("Test");
        product.setProductName("Test");
        product.setListPrice(new BigDecimal(5.99).setScale(2, RoundingMode.HALF_EVEN));
        product.setUnitCost(new BigDecimal(4.99).setScale(2, RoundingMode.HALF_EVEN));
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        doReturn(productList).when(productFeign).fetchAllProducts();
        doReturn(product).when(productFeign).fetchProductById(anyInt());

        LevelUp levelUp = new LevelUp();
        levelUp.setLevel_up_id(1);
        levelUp.setCustomer_id(1);
        levelUp.setPoints(100);
        levelUp.setMember_date(LocalDate.of(2019, 1, 1));
        List<LevelUp> levelUpList = new ArrayList<>();
        levelUpList.add(levelUp);
        doReturn(levelUpList).when(levelUpFeign).getAllLevelUps();

        doNothing().when(rabbitTemplate).convertAndSend(anyString(), anyString(), any(LevelUpEntry.class));
    }
}
