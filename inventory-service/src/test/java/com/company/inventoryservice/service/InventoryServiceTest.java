package com.company.inventoryservice.service;

import com.company.inventoryservice.dao.InventoryDaoImpl;
import com.company.inventoryservice.model.Inventory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class InventoryServiceTest {

    InventoryService service;

    @Mock
    InventoryDaoImpl dao;

    @Before
    public void setUp() {
        setUpMocks();
        service = new InventoryService(dao);
    }

    @Test
    public void shouldReturnInventoryFromAddInventory() throws Exception {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(10);

        Inventory inventoryWithId = new Inventory();
        inventoryWithId.setId(1);
        inventoryWithId.setProductId(1);
        inventoryWithId.setQuantity(10);

        assertEquals(service.addInventory(inventory).toString(), inventoryWithId.toString());
    }

    @Test
    public void shouldReturnInventoryFromGetInventoryById() throws Exception {
        Inventory inventoryWithId = new Inventory();
        inventoryWithId.setId(1);
        inventoryWithId.setProductId(1);
        inventoryWithId.setQuantity(10);

        assertEquals(service.getInventoryById(1).toString(), inventoryWithId.toString());
    }

    @Test
    public void shouldReturnInventoryListFromGetAllInventories() throws Exception {
        Inventory inventoryWithId = new Inventory();
        inventoryWithId.setId(1);
        inventoryWithId.setProductId(1);
        inventoryWithId.setQuantity(10);

        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(inventoryWithId);

        assertEquals(service.getAllInventories().toString(), inventoryList.toString());
    }

    private void setUpMocks() {
        Inventory inventory = new Inventory();
        inventory.setId(1);
        inventory.setProductId(1);
        inventory.setQuantity(10);

        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(inventory);

        doReturn(inventory).when(dao).getInventoryById(anyInt());
        doReturn(inventory).when(dao).addInventory(any(Inventory.class));
        doReturn(inventoryList).when(dao).getAllInventories();
    }
}
