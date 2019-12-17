package com.company.invoiceservice.dao;

import com.company.invoiceservice.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {
    InvoiceItem addInvoiceItem(InvoiceItem invoiceItem);
    InvoiceItem getInvoiceItemById(int id);
    List<InvoiceItem> getAllInvoiceItems();
    List<InvoiceItem> getInvoiceItemsByInvoiceId(int invoiceId);
    void updateInvoiceItem(InvoiceItem invoiceItem);
    void deleteInvoiceItem(int id);
}
