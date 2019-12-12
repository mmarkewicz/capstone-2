package com.company.invoiceservice.dao;

import com.company.invoiceservice.model.Invoice;
import com.company.invoiceservice.model.InvoiceItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InvoiceItemDaoImplTest {

    @Autowired
    InvoiceItemDaoImpl itemDao;

    @Autowired
    InvoiceDaoImpl invoiceDao;

    @Before
    public void setUp() {
        itemDao.getAllInvoiceItems().forEach(invoiceItem -> itemDao.deleteInvoiceItem(invoiceItem.getId()));
        invoiceDao.getAllInvoices().forEach(invoice -> invoiceDao.deleteInvoice(invoice.getId()));
    }

    @After
    public void tearDown() {
        itemDao.getAllInvoiceItems().forEach(invoiceItem -> itemDao.deleteInvoiceItem(invoiceItem.getId()));
        invoiceDao.getAllInvoices().forEach(invoice -> invoiceDao.deleteInvoice(invoice.getId()));
    }

    @Test
    public void shouldAddGetDeleteInvoiceItems() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(5);
        invoice.setPurchaseDate(LocalDate.of(2019, 1, 1));
        invoice = invoiceDao.addInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setUnitPrice(new BigDecimal(4.99).setScale(2, RoundingMode.HALF_EVEN));
        invoiceItem.setQuantity(10);
        invoiceItem.setInvoiceId(invoice.getId());
        invoiceItem.setInventory_id(1);
        invoiceItem = itemDao.addInvoiceItem(invoiceItem);

        assertEquals(invoiceItem.toString(), itemDao.getInvoiceItemById(invoiceItem.getId()).toString());

        itemDao.deleteInvoiceItem(invoiceItem.getId());

        assertEquals(itemDao.getAllInvoiceItems().size(), 0);
    }

    @Test
    public void shouldReturnListInvoiceItemsByInvoiceId() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(5);
        invoice.setPurchaseDate(LocalDate.of(2019, 1, 1));
        invoice = invoiceDao.addInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setUnitPrice(new BigDecimal(4.99).setScale(2, RoundingMode.HALF_EVEN));
        invoiceItem.setQuantity(10);
        invoiceItem.setInvoiceId(invoice.getId());
        invoiceItem.setInventory_id(1);
        invoiceItem = itemDao.addInvoiceItem(invoiceItem);

        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);

        assertEquals(itemDao.getInvoiceItemsByInvoiceId(invoiceItem.getInvoiceId()).toString(), invoiceItemList.toString());
    }

    @Test
    public void shouldUpdateInvoiceItem() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(5);
        invoice.setPurchaseDate(LocalDate.of(2019, 1, 1));
        invoice = invoiceDao.addInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setUnitPrice(new BigDecimal(4.99).setScale(2, RoundingMode.HALF_EVEN));
        invoiceItem.setQuantity(10);
        invoiceItem.setInvoiceId(invoice.getId());
        invoiceItem.setInventory_id(1);
        invoiceItem = itemDao.addInvoiceItem(invoiceItem);

        invoiceItem.setQuantity(1000);
        itemDao.updateInvoiceItem(invoiceItem);

        assertEquals(1000, itemDao.getInvoiceItemById(invoiceItem.getId()).getQuantity());
    }

    @Test
    public void shouldGetAllInvoiceItems() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(5);
        invoice.setPurchaseDate(LocalDate.of(2019, 1, 1));
        invoice = invoiceDao.addInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setUnitPrice(new BigDecimal(4.99).setScale(2, RoundingMode.HALF_EVEN));
        invoiceItem.setQuantity(10);
        invoiceItem.setInvoiceId(invoice.getId());
        invoiceItem.setInventory_id(1);
        itemDao.addInvoiceItem(invoiceItem);

        assertEquals(1, itemDao.getAllInvoiceItems().size());
    }

}
