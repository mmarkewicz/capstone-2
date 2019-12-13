package com.company.inventoryservice.service;

import com.company.inventoryservice.dao.InventoryDaoImpl;
import com.company.inventoryservice.model.Inventory;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    InventoryDaoImpl dao;

    @Autowired
    public InventoryService(InventoryDaoImpl dao) {
        this.dao = dao;
    }

    public Inventory addInventory(Inventory inventory) throws Exception {
        try {
            return dao.addInventory(inventory);
        } catch (Exception e) {
            throw new Exception("Couldn't add inventory to database");
        }
    }

    public Inventory getInventoryById(int id) throws Exception {
        try {
            return dao.getInventoryById(id);
        } catch (Exception e) {
            throw new Exception("Couldn't get inventory from database");
        }
    }

    public List<Inventory> getAllInventories() throws Exception {
        try {
            return dao.getAllInventories();
        } catch (Exception e) {
            throw new Exception("Couldn't get all inventories from database");
        }
    }

    public void updateInventory(Inventory inventory) throws Exception {
        try {
            dao.updateInventory(inventory);
        } catch (Exception e) {
            throw new Exception("Couldn't update inventory in database");
        }
    }

    public void deleteInventoryById(int id) throws Exception {
        try {
            dao.deleteInventoryById(id);
        } catch (Exception e) {
            throw new Exception("Couldn't delete inventory from database");
        }
    }

}
