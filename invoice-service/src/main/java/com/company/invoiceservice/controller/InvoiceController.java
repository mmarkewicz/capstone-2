package com.company.invoiceservice.controller;

import com.company.invoiceservice.model.InvoiceViewModel;
import com.company.invoiceservice.service.InvoiceServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceServiceLayer service;

    @PostMapping("/invoices")
    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceViewModel postInvoice(@RequestBody InvoiceViewModel invoiceViewModel) {
        return service.addInvoice(invoiceViewModel);
    }

    @GetMapping("/invoices")
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices() {
        return service.getAllInvoices();
    }

    @GetMapping("/invoices/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public InvoiceViewModel getInvoiceById(@PathVariable int id) {
        return service.getInvoiceById(id);
    }

    @GetMapping("/invoices/customer/{customerId}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<InvoiceViewModel> getInvoicesByCustomerId(@PathVariable int customerId) {
        return service.getInvoicesByCustomerId(customerId);
    }

}
