package com.company.invoiceservice.dao;

import com.company.invoiceservice.model.Invoice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InvoiceDaoImplTest {

    @Autowired
    InvoiceDaoImpl dao;

    @Before
    public void setUp() {
        dao.getAllInvoices().forEach(invoice -> dao.deleteInvoice(invoice.getId()));
    }

    @After
    public void tearDown() {
        dao.getAllInvoices().forEach(invoice -> dao.deleteInvoice(invoice.getId()));
    }

    @Test
    public void shouldAddGetDeleteInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019, 1, 1));
        invoice = dao.addInvoice(invoice);

        assertEquals(invoice.toString(), dao.getInvoiceById(invoice.getId()).toString());

        dao.deleteInvoice(invoice.getId());

        assertEquals(dao.getAllInvoices().size(), 0);
    }

    @Test
    public void shouldGetInvoicesByCustomerId() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019, 1, 1));
        invoice = dao.addInvoice(invoice);

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        assertEquals(invoiceList.toString(), dao.getInvoicesByCustomerId(1).toString());
    }

    @Test
    public void shouldUpdateInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019, 1, 1));
        invoice = dao.addInvoice(invoice);

        invoice.setCustomerId(5);

        dao.updateInvoice(invoice);

        assertEquals(dao.getInvoiceById(invoice.getId()).getCustomerId(), 5);
    }

    @Test
    public void shouldGetAllInvoices() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2019, 1, 1));
        dao.addInvoice(invoice);

        Invoice invoice2 = new Invoice();
        invoice2.setCustomerId(1);
        invoice2.setPurchaseDate(LocalDate.of(2019, 1, 1));
        dao.addInvoice(invoice2);

        assertEquals(dao.getAllInvoices().size(), 2);
    }
}
