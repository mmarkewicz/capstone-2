package com.company.invoiceservice.controller;

import com.company.invoiceservice.model.InvoiceItem;
import com.company.invoiceservice.model.InvoiceViewModel;
import com.company.invoiceservice.service.InvoiceServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceServiceLayer service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldReturnListFromGetAllInvoices() throws Exception {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventory_id(1);
        invoiceItem.setQuantity(100);
        invoiceItem.setUnitPrice(new BigDecimal(4.99).setScale(2, RoundingMode.HALF_EVEN));

        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setId(1);
        invoiceViewModel.setCustomerId(1);
        invoiceViewModel.setPurchaseDate(LocalDate.of(2019, 1, 1));
        invoiceViewModel.setInvoiceItems(invoiceItemList);

        List<InvoiceViewModel> invoiceViewModels = new ArrayList<>();
        invoiceViewModels.add(invoiceViewModel);

        when(service.getAllInvoices()).thenReturn(invoiceViewModels);

        String json = mapper.writeValueAsString(invoiceViewModels);

        this.mockMvc.perform(get("/invoices"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(json)));
    }

    @Test
    public void shouldReturnInvoiceViewModelFromPostInvoice() throws Exception {
        InvoiceItem requestInvoiceItem = new InvoiceItem();
        requestInvoiceItem.setInventory_id(1);
        requestInvoiceItem.setQuantity(100);
        requestInvoiceItem.setUnitPrice(new BigDecimal(5.99).setScale(2, RoundingMode.HALF_EVEN));

        List<InvoiceItem> requestInvoiceItems = new ArrayList<>();
        requestInvoiceItems.add(requestInvoiceItem);

        InvoiceViewModel requestInvoiceViewModel = new InvoiceViewModel();
        requestInvoiceViewModel.setCustomerId(1);
        requestInvoiceViewModel.setPurchaseDate(LocalDate.of(2019, 1, 1));
        requestInvoiceViewModel.setInvoiceItems(requestInvoiceItems);

        InvoiceItem responseInvoiceItem = new InvoiceItem();
        responseInvoiceItem.setId(1);
        responseInvoiceItem.setInvoiceId(1);
        responseInvoiceItem.setInventory_id(1);
        responseInvoiceItem.setQuantity(100);
        responseInvoiceItem.setUnitPrice(new BigDecimal(5.99).setScale(2, RoundingMode.HALF_EVEN));

        List<InvoiceItem> responseInvoiceItems = new ArrayList<>();
        responseInvoiceItems.add(responseInvoiceItem);

        InvoiceViewModel responseInvoiceViewModel = new InvoiceViewModel();
        responseInvoiceViewModel.setId(1);
        responseInvoiceViewModel.setCustomerId(1);
        responseInvoiceViewModel.setPurchaseDate(LocalDate.of(2019, 1, 1));
        responseInvoiceViewModel.setInvoiceItems(responseInvoiceItems);

        when(service.addInvoice(any(InvoiceViewModel.class))).thenReturn(responseInvoiceViewModel);

        String requestJson = mapper.writeValueAsString(requestInvoiceViewModel);
        String responseJson = mapper.writeValueAsString(responseInvoiceViewModel);

        this.mockMvc.perform(post("/invoices")
        .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(responseJson)));
    }

    @Test
    public void shouldReturnInvoiceViewModelFromGetInvoiceById() throws Exception {

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventory_id(1);
        invoiceItem.setQuantity(100);
        invoiceItem.setUnitPrice(new BigDecimal(4.99).setScale(2, RoundingMode.HALF_EVEN));

        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setId(1);
        invoiceViewModel.setCustomerId(1);
        invoiceViewModel.setPurchaseDate(LocalDate.of(2019, 1, 1));
        invoiceViewModel.setInvoiceItems(invoiceItemList);

        when(service.getInvoiceById(anyInt())).thenReturn(invoiceViewModel);

        String json = mapper.writeValueAsString(invoiceViewModel);

        this.mockMvc.perform(get("/invoices/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(json)));
    }

    @Test
    public void shouldReturnListInvoiceViewModelsFromGetInvoiceByCustomerId() throws Exception {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventory_id(1);
        invoiceItem.setQuantity(100);
        invoiceItem.setUnitPrice(new BigDecimal(4.99).setScale(2, RoundingMode.HALF_EVEN));

        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setId(1);
        invoiceViewModel.setCustomerId(1);
        invoiceViewModel.setPurchaseDate(LocalDate.of(2019, 1, 1));
        invoiceViewModel.setInvoiceItems(invoiceItemList);

        List<InvoiceViewModel> invoiceViewModelList = new ArrayList<>();
        invoiceViewModelList.add(invoiceViewModel);

        when(service.getInvoicesByCustomerId(anyInt())).thenReturn(invoiceViewModelList);

        String json = mapper.writeValueAsString(invoiceViewModelList);

        this.mockMvc.perform(get("/invoices/customer/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(json)));
    }
    // get /invoices/customer/{customerId}
}
