package com.company.inventoryservice.dao;

import com.company.inventoryservice.model.Inventory;

import java.util.List;

public interface InventoryDao {
    Inventory addInventory(Inventory inventory);
    Inventory getInventoryById(int id);
    List<Inventory> getAllInventories();
    void updateInventory(Inventory inventory);
    void deleteInventoryById(int id);
}
