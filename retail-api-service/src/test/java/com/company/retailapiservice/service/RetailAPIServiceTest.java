package com.company.retailapiservice.service;

import com.company.retailapiservice.feign.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RetailAPIServiceTest {

    RetailAPIService service;

    @MockBean
    RabbitTemplate rabbitTemplate;

    @MockBean
    InvoiceServiceFeign invoiceFeign;

    @MockBean
    ProductServiceFeign productFeign;

    @MockBean
    LevelUpServiceFeign levelUpFeign;

    @MockBean
    InventoryServiceFeign inventoryFeign;

    @MockBean
    CustomerServiceFeign customerFeign;

    @Before
    public void setUp() {
        service = new RetailAPIService(rabbitTemplate, invoiceFeign, productFeign, levelUpFeign, inventoryFeign, customerFeign);
        setUpMocks();
    }

    private void setUpMocks() {

    }
}
