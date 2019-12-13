package com.company.retailapiservice.service;

import com.company.retailapiservice.feign.InventoryServiceFeign;
import com.company.retailapiservice.feign.InvoiceServiceFeign;
import com.company.retailapiservice.feign.LevelUpServiceFeign;
import com.company.retailapiservice.feign.ProductServiceFeign;
import com.company.retailapiservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetailAPIService {

    @Autowired
    InvoiceServiceFeign invoiceFeign;

    @Autowired
    ProductServiceFeign productFeign;

    @Autowired
    LevelUpServiceFeign levelUpFeign;

    @Autowired
    InventoryServiceFeign inventoryFeign;

    public InvoiceViewModel addInvoice(InvoiceViewModel invoiceViewModel) {
        return invoiceFeign.postInvoice(invoiceViewModel);
    }

    public InvoiceViewModel getInvoiceById(int id) {
        return invoiceFeign.getInvoiceById(id);
    }

    public List<InvoiceViewModel> getAllInvoices() {
        return invoiceFeign.getAllInvoices();
    }

    public List<InvoiceViewModel> getInvoicesByCustomerId(int id) {
        return invoiceFeign.getInvoicesByCustomerId(id);
    }

    public List<Product> getProductsInInventory() {
        return productFeign.fetchAllProducts();
    }

    public Product getProductById(int id) {
        return productFeign.fetchProductById(id);
    }

    public List<Product> getProductsByInvoiceId(int id) {

        // get all invoice items
        List<InvoiceItem> invoiceItems = invoiceFeign.getInvoiceById(id).getInvoiceItems();

        // get all inventory ids from invoice items
        List<Integer> inventoryIds = invoiceItems.stream()
                .map(invoiceItem -> invoiceItem.getInventory_id())
                .collect(Collectors.toList());

        // get all inventories
        List<Inventory> inventoryList = inventoryIds.stream()
                .map(integer -> inventoryFeign.getInventoryById(integer)).collect(Collectors.toList());

        // use product id from inventories to get all products
        List<Product> productList = inventoryList.stream()
                .map(inventory -> productFeign.fetchProductById(inventory.getProductId()))
                .collect(Collectors.toList());

        return productList;
    }

    public int getLevelUpPointsByCustomerId(int id) {
        List<LevelUp> levelUps = levelUpFeign.getAllLevelUps().stream().filter(levelUp -> levelUp.getCustomer_id() == id).collect(Collectors.toList());
        return levelUps.get(0).getPoints();
    }
}
