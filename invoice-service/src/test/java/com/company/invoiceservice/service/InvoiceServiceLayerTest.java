package com.company.invoiceservice.service;

import com.company.invoiceservice.dao.InvoiceDaoImpl;
import com.company.invoiceservice.dao.InvoiceItemDaoImpl;
import com.company.invoiceservice.model.Invoice;
import com.company.invoiceservice.model.InvoiceItem;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceLayerTest {

    InvoiceServiceLayer service;

    @Mock
    InvoiceDaoImpl invoiceDao;

    @Mock
    InvoiceItemDaoImpl invoiceItemDao;

    @Before
    public void setUp() {
        setUpMocks();
        service = new InvoiceServiceLayer(invoiceDao, invoiceItemDao);
    }

    private void setUpMocks() {
        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019, 1, 1));

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(1);
        invoiceItem.setId(1);
        invoiceItem.setQuantity(10);
        invoiceItem.setInventory_id(1);
        invoiceItem.setUnitPrice(new BigDecimal(9.99).setScale(2, RoundingMode.HALF_EVEN));

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        invoiceItems.add(invoiceItem);

        doReturn(invoice).when(invoiceDao).getInvoiceById(anyInt());
        doReturn(invoiceList).when(invoiceDao).getAllInvoices();
        doReturn(invoiceList).when(invoiceDao).getInvoicesByCustomerId(anyInt());
        doReturn(invoice).when(invoiceDao).addInvoice(any(Invoice.class));
    }

    // invoiceItemDao.getInvoiceItemsByInvoiceId(invoice.getId())
    // invoiceItemDao.addInvoiceItem(invoiceItem)

}
