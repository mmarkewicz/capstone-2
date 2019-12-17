package com.company.adminservice.util;


import com.company.adminservice.model.InvoiceItem;
import com.company.adminservice.model.InvoiceViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "invoice-service")
public interface InvoiceServiceClient {


    @PostMapping("/invoices")
    InvoiceViewModel postInvoice(@RequestBody InvoiceViewModel invoiceViewModel);

    @GetMapping("/invoices")
    List<InvoiceViewModel> getAllInvoices();

    @GetMapping("/invoices/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    InvoiceViewModel getInvoiceById(@PathVariable int id);

    @GetMapping("/invoices/customer/{customerId}")
    List<InvoiceViewModel> getInvoicesByCustomerId(@PathVariable int customerId);



    @PostMapping("/invoiceItems")
    InvoiceItem postInvoiceItem(@RequestBody InvoiceItem invoiceItem);

    @GetMapping("/invoiceItems")
    List<InvoiceItem> getAllInvoiceItems();

    @GetMapping("/invoiceItems/{id}")
    InvoiceItem getInvoiceItemById(@PathVariable int id);

    @PutMapping("/invoiceItems")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    void updateInvoiceItem(@RequestBody InvoiceItem invoiceItem);

    @DeleteMapping("/invoiceItems/{id}")
    void updateInvoiceItem(@PathVariable int id);


}
