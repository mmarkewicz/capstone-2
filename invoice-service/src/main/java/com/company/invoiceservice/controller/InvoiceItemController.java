package com.company.invoiceservice.controller;

import com.company.invoiceservice.dao.InvoiceItemDao;
import com.company.invoiceservice.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CacheConfig(cacheNames = "invoiceItems")
public class InvoiceItemController {

    @Autowired
    InvoiceItemDao invoiceItemDao;

    @CachePut(key = "#result.getId()")
    @PostMapping("/invoiceItems")
    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceItem postInvoiceItem(@RequestBody InvoiceItem invoiceItem) throws Exception {
        return invoiceItemDao.addInvoiceItem(invoiceItem);
    }

    @GetMapping("/invoiceItems")
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceItem> getAllInvoiceItems() throws Exception {
        return invoiceItemDao.getAllInvoiceItems();
    }

    @Cacheable
    @GetMapping("/invoiceItems/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceItem getInvoiceItemById(@PathVariable int id) throws Exception {
        return invoiceItemDao.getInvoiceItemById(id);
    }

    @CacheEvict(key = "#invoiceItem.getId()")
    @PutMapping("/invoiceItems")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateInvoiceItem(@RequestBody InvoiceItem invoiceItem) throws Exception {
        invoiceItemDao.updateInvoiceItem(invoiceItem);
    }

    @CacheEvict
    @DeleteMapping("/invoiceItems/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateInvoiceItem(@PathVariable int id) throws Exception {
        invoiceItemDao.deleteInvoiceItem(id);
    }


}
