package com.company.invoiceservice.service;

import com.company.invoiceservice.dao.InvoiceDaoImpl;
import com.company.invoiceservice.dao.InvoiceItemDaoImpl;
import com.company.invoiceservice.model.Invoice;
import com.company.invoiceservice.model.InvoiceItem;
import com.company.invoiceservice.model.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void shouldReturnListOfInvoiceViewModelsFromGetAllInvoices() {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(1);
        invoiceItem.setId(1);
        invoiceItem.setQuantity(10);
        invoiceItem.setInventory_id(1);
        invoiceItem.setUnitPrice(new BigDecimal(9.99).setScale(2, RoundingMode.HALF_EVEN));

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        invoiceItems.add(invoiceItem);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setId(1);
        invoiceViewModel.setCustomerId(1);
        invoiceViewModel.setPurchaseDate(LocalDate.of(2019, 1, 1));
        invoiceViewModel.setInvoiceItems(invoiceItems);

        List<InvoiceViewModel> invoiceViewModels = new ArrayList<>();
        invoiceViewModels.add(invoiceViewModel);

        assertEquals(service.getAllInvoices().toString(), invoiceViewModels.toString());
    }

    @Test
    public void shouldReturnInvoiceViewModelFromGetInvoiceById() {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(1);
        invoiceItem.setId(1);
        invoiceItem.setQuantity(10);
        invoiceItem.setInventory_id(1);
        invoiceItem.setUnitPrice(new BigDecimal(9.99).setScale(2, RoundingMode.HALF_EVEN));

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        invoiceItems.add(invoiceItem);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setId(1);
        invoiceViewModel.setCustomerId(1);
        invoiceViewModel.setPurchaseDate(LocalDate.of(2019, 1, 1));
        invoiceViewModel.setInvoiceItems(invoiceItems);

        assertEquals(invoiceViewModel.toString(), service.getInvoiceById(1).toString());
    }

    @Test
    public void shouldReturnInvoiceViewModelFromAddInvoice() {

        // with invoice ID ------------------

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setQuantity(10);
        invoiceItem.setInventory_id(1);
        invoiceItem.setUnitPrice(new BigDecimal(9.99).setScale(2, RoundingMode.HALF_EVEN));

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        invoiceItems.add(invoiceItem);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setId(1);
        invoiceViewModel.setCustomerId(1);
        invoiceViewModel.setPurchaseDate(LocalDate.of(2019, 1, 1));
        invoiceViewModel.setInvoiceItems(invoiceItems);

        // ------------------------------------

        // without invoice ID -----------------
        InvoiceItem invoiceItemWithoutId = new InvoiceItem();
        invoiceItemWithoutId.setQuantity(10);
        invoiceItemWithoutId.setInventory_id(1);
        invoiceItemWithoutId.setUnitPrice(new BigDecimal(9.99).setScale(2, RoundingMode.HALF_EVEN));

        List<InvoiceItem> invoiceItemsWithoutId = new ArrayList<>();
        invoiceItemsWithoutId.add(invoiceItemWithoutId);

        InvoiceViewModel invoiceViewModelWithoutId = new InvoiceViewModel();
        invoiceViewModelWithoutId.setCustomerId(1);
        invoiceViewModelWithoutId.setPurchaseDate(LocalDate.of(2019, 1, 1));
        invoiceViewModelWithoutId.setInvoiceItems(invoiceItemsWithoutId);
        // ------------------------------------

        assertEquals(invoiceViewModel.toString(), service.addInvoice(invoiceViewModelWithoutId).toString());
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
        doReturn(invoiceItems).when(invoiceItemDao).getInvoiceItemsByInvoiceId(anyInt());
        doReturn(invoiceItem).when(invoiceItemDao).addInvoiceItem(any(InvoiceItem.class));
    }
}
