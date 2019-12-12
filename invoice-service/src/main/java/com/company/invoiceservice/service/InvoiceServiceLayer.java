package com.company.invoiceservice.service;

import com.company.invoiceservice.dao.InvoiceDaoImpl;
import com.company.invoiceservice.dao.InvoiceItemDaoImpl;
import com.company.invoiceservice.model.Invoice;
import com.company.invoiceservice.model.InvoiceItem;
import com.company.invoiceservice.model.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceServiceLayer {

    InvoiceDaoImpl invoiceDao;
    InvoiceItemDaoImpl invoiceItemDao;

    @Autowired
    public InvoiceServiceLayer(InvoiceDaoImpl invoiceDao, InvoiceItemDaoImpl invoiceItemDao) {
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
    }

    public List<InvoiceViewModel> getAllInvoices() {
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();

        List<InvoiceViewModel> invoiceViewModelList =
                invoiceList.stream()
                        .map(invoice -> {
                            List<InvoiceItem> invoiceItems = invoiceItemDao.getInvoiceItemsByInvoiceId(invoice.getId());

                            InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
                            invoiceViewModel.setCustomerId(invoice.getCustomerId());
                            invoiceViewModel.setPurchaseDate(invoice.getPurchaseDate());
                            invoiceViewModel.setInvoiceItems(invoiceItems);
                            return invoiceViewModel;
                        })
                        .collect(Collectors.toList());

        return invoiceViewModelList;
    }

    public InvoiceViewModel getInvoiceById(int id) {
        Invoice invoice = invoiceDao.getInvoiceById(id);
        List<InvoiceItem> invoiceItems = invoiceItemDao.getInvoiceItemsByInvoiceId(invoice.getId());

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setId(invoice.getId());
        invoiceViewModel.setCustomerId(invoice.getCustomerId());
        invoiceViewModel.setPurchaseDate(invoice.getPurchaseDate());
        invoiceViewModel.setInvoiceItems(invoiceItems);

        return invoiceViewModel;
    }

    public List<InvoiceViewModel> getInvoicesByCustomerId(int customerId) {
        List<Invoice> invoiceList = invoiceDao.getInvoicesByCustomerId(customerId);

        List<InvoiceViewModel> invoiceViewModels =  invoiceList.stream()
                .map(invoice -> {
                            List<InvoiceItem> invoiceItems = invoiceItemDao.getInvoiceItemsByInvoiceId(invoice.getId());

                            InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
                            invoiceViewModel.setId(invoice.getId());
                            invoiceViewModel.setCustomerId(invoice.getCustomerId());
                            invoiceViewModel.setPurchaseDate(invoice.getPurchaseDate());
                            invoiceViewModel.setInvoiceItems(invoiceItems);

                            return invoiceViewModel;
                        })
                .collect(Collectors.toList());

        return invoiceViewModels;
    }

    public InvoiceViewModel addInvoice(InvoiceViewModel invoiceViewModel) {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(invoiceViewModel.getCustomerId());
        invoice.setPurchaseDate(invoiceViewModel.getPurchaseDate());
        invoice = invoiceDao.addInvoice(invoice);

        int invoiceId = invoice.getId();
        invoiceViewModel.setId(invoiceId);

        List<InvoiceItem> invoiceItems = invoiceViewModel.getInvoiceItems();
        invoiceItems.forEach(invoiceItem -> {
            invoiceItem.setInvoiceId(invoiceId);
            invoiceItemDao.addInvoiceItem(invoiceItem);
        });

        return invoiceViewModel;
    }
}
