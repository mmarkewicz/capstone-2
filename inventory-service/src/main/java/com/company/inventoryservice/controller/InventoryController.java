package com.company.inventoryservice.controller;

import com.company.inventoryservice.model.Inventory;
import com.company.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@CacheConfig(cacheNames = "inventories")
public class InventoryController {

    @Autowired
    InventoryService service;

    @GetMapping("/inventories")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Inventory> getAllInventories() throws Exception {
        return service.getAllInventories();
    }


    @Cacheable
    @GetMapping("/inventory/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Inventory getInventoryById(@PathVariable int id) throws Exception {
        return service.getInventoryById(id);
    }

    @CachePut(key = "#result.getId()")
    @PostMapping("/inventories")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Inventory postInventory(@RequestBody Inventory inventory) throws Exception {
        return service.addInventory(inventory);
    }

    @CacheEvict(key = "inventory.getId()")
    @PutMapping("/inventories")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateInventory(@RequestBody Inventory inventory) throws Exception {
        service.updateInventory(inventory);
    }

    @CacheEvict
    @DeleteMapping("/inventory/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteInventoryById(@PathVariable int id) throws Exception {
        service.deleteInventoryById(id);
    }

}
