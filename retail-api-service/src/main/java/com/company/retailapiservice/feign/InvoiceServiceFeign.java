package com.company.retailapiservice.feign;

import com.company.retailapiservice.model.InvoiceViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "invoice-service")
public interface InvoiceServiceFeign {

    @PostMapping("/invoices")
    InvoiceViewModel postInvoice(@RequestBody InvoiceViewModel invoiceViewModel);

    @GetMapping("/invoices")
    List<InvoiceViewModel> getAllInvoices();

    @GetMapping("/invoices/{id}")
    InvoiceViewModel getInvoiceById(@PathVariable int id);

    @GetMapping("/invoices/customer/{customerId}")
    List<InvoiceViewModel> getInvoicesByCustomerId(@PathVariable int customerId);
}
