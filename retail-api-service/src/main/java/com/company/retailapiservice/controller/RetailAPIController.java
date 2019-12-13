package com.company.retailapiservice.controller;

import com.company.retailapiservice.model.InvoiceViewModel;
import com.company.retailapiservice.model.Product;
import com.company.retailapiservice.service.RetailAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RetailAPIController {

    @Autowired
    RetailAPIService service;

    @PostMapping("/invoices")
    public InvoiceViewModel submitInvoice(@RequestBody InvoiceViewModel invoiceViewModel) {
        return service.addInvoice(invoiceViewModel);
    }

    @GetMapping("/invoices/{id}")
    public InvoiceViewModel getInvoiceById(@PathVariable int id) {
        return service.getInvoiceById(id);
    }

    @GetMapping(value = "/invoices")
    public List<InvoiceViewModel> getAllInvoices() {
        return service.getAllInvoices();
    }

    @GetMapping(value = "/invoices/customer/{id}")
    public List<InvoiceViewModel> getInvoicesByCustomerId(@PathVariable int id) {
        return service.getInvoicesByCustomerId(id);
    }

    @GetMapping(value = "/products/inventory")
    public List<Product> getProductsInInventory() {
        return service.getProductsInInventory();
    }

    @GetMapping(value = "/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

    @GetMapping(value = "/products/invoice/{id}")
    public List<Product> getProductByInvoiceId(@PathVariable int id) {
        return service.getProductsByInvoiceId(id);
    }

    @GetMapping(value = "/levelup/customer/{id}")
    public int getLevelUpPointsByCustomerId(int id) {
        return getLevelUpPointsByCustomerId(id);
    }

}
