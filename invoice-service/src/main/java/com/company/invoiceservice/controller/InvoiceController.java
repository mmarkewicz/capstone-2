package com.company.invoiceservice.controller;

import com.company.invoiceservice.model.InvoiceViewModel;
import com.company.invoiceservice.service.InvoiceServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CacheConfig(cacheNames = "invoices")
public class InvoiceController {

    @Autowired
    InvoiceServiceLayer service;


    @CachePut(key = "#result.getId()")
    @PostMapping("/invoices")
    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceViewModel postInvoice(@RequestBody InvoiceViewModel invoiceViewModel) throws Exception {
        return service.addInvoice(invoiceViewModel);
    }

    @GetMapping("/invoices")
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices() throws Exception {
        return service.getAllInvoices();
    }

    @Cacheable
    @GetMapping("/invoices/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceViewModel getInvoiceById(@PathVariable int id) throws Exception {
        return service.getInvoiceById(id);
    }

    @Cacheable
    @GetMapping("/invoices/customer/{customerId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceViewModel> getInvoicesByCustomerId(@PathVariable int customerId) throws Exception {
        return service.getInvoicesByCustomerId(customerId);
    }

}
