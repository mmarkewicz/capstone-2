package com.company.inventoryservice.dao;

import com.company.inventoryservice.model.Inventory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InventoryDaoImplTest {

    @Autowired
    InventoryDaoImpl dao;

    @Before
    public void setUp() {
        dao.getAllInventories().forEach(inventory -> dao.deleteInventoryById(inventory.getId()));
    }

    @After
    public void tearDown() {
        dao.getAllInventories().forEach(inventory -> dao.deleteInventoryById(inventory.getId()));
    }

    @Test
    public void shouldAddGetDeleteInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(10);
        inventory = dao.addInventory(inventory);

        assertEquals(inventory.toString(), dao.getInventoryById(inventory.getId()).toString());

        dao.deleteInventoryById(inventory.getId());

        assertEquals(dao.getAllInventories().size(), 0);
    }

    @Test
    public void shouldGetAllInventories() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(10);
        inventory = dao.addInventory(inventory);

        assertEquals(dao.getAllInventories().size(), 1);
        assertEquals(dao.getAllInventories().get(0).toString(), inventory.toString());
    }


    @Test
    public void shouldUpdateInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(10);
        inventory = dao.addInventory(inventory);

        inventory.setQuantity(1000);
        dao.updateInventory(inventory);

        assertEquals(1000, dao.getInventoryById(inventory.getId()).getQuantity());
    }

}
