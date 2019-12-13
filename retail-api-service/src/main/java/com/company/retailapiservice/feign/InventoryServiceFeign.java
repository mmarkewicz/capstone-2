package com.company.retailapiservice.feign;

import com.company.retailapiservice.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryServiceFeign {

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
