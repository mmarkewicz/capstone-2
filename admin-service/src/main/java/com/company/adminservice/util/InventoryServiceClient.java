package com.company.adminservice.util;

import com.company.adminservice.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryServiceClient {

    @GetMapping("/inventories")
    List<Inventory> getAllInventories();

    @GetMapping("/inventory/{id}")
    Inventory getInventoryById(@PathVariable int id);

    @PostMapping("/inventories")
    Inventory postInventory(@RequestBody Inventory inventory);

    @PutMapping("/inventories")
    void updateInventory(@RequestBody Inventory inventory);

    @DeleteMapping("/inventory/{id}")
    void deleteInventoryById(@PathVariable int id);

}
