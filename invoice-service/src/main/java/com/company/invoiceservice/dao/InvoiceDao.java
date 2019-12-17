package com.company.invoiceservice.dao;

import com.company.invoiceservice.model.Invoice;

import java.util.List;

public interface InvoiceDao {
    Invoice addInvoice(Invoice invoice);
    Invoice getInvoiceById(int id);
    List<Invoice> getAllInvoices();
    List<Invoice> getInvoicesByCustomerId(int customerId);
    void updateInvoice(Invoice invoice);
    void deleteInvoice(int id);
}
