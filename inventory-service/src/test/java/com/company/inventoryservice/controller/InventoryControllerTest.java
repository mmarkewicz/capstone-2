package com.company.inventoryservice.controller;

import com.company.inventoryservice.model.Inventory;
import com.company.inventoryservice.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldReturnListFromGetAllInventories() throws Exception {
        Inventory inventory = new Inventory();
        inventory.setId(1);
        inventory.setProductId(1);
        inventory.setQuantity(100);

        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(inventory);

        when(service.getAllInventories()).thenReturn(inventoryList);

        String json = mapper.writeValueAsString(inventoryList);

        this.mockMvc.perform(get("/inventories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(json)));
    }

    @Test
    public void shouldReturnInventoryFromGetInventoryById() throws Exception {
        Inventory inventory = new Inventory();
        inventory.setId(1);
        inventory.setProductId(1);
        inventory.setQuantity(100);

        when(service.getInventoryById(anyInt())).thenReturn(inventory);

        String json = mapper.writeValueAsString(inventory);

        this.mockMvc.perform(get("/inventory/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(json)));
    }

    @Test
    public void shouldReturnInventoryFromPostInventory() throws Exception {
        Inventory requestInventory = new Inventory();
        requestInventory.setProductId(1);
        requestInventory.setQuantity(100);

        Inventory responseInventory = new Inventory();
        responseInventory.setId(1);
        responseInventory.setProductId(1);
        responseInventory.setQuantity(100);

        when(service.addInventory(any(Inventory.class))).thenReturn(responseInventory);

        String requestJson = mapper.writeValueAsString(requestInventory);
        String responseJson = mapper.writeValueAsString(responseInventory);

        this.mockMvc.perform(post("/inventories")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(responseJson)));
    }
}
