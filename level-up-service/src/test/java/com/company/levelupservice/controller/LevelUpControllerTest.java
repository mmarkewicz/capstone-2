package com.company.levelupservice.controller;

import com.company.levelupservice.model.LevelUp;
import com.company.levelupservice.service.LevelUpServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
@WebMvcTest(LevelUpController.class)
public class LevelUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LevelUpServiceLayer service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldReturnListFromGetAllLevelUps() throws Exception {
        LevelUp levelUp = new LevelUp();
        levelUp.setLevel_up_id(1);
        levelUp.setCustomer_id(1);
        levelUp.setPoints(100);
        levelUp.setMember_date(LocalDate.of(2019, 1, 1));

        List<LevelUp> levelUpList = new ArrayList<>();
        levelUpList.add(levelUp);

        when(service.getAllLevelUps()).thenReturn(levelUpList);

        String json = mapper.writeValueAsString(levelUpList);

        this.mockMvc.perform(get("/levelups"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(json)));
    }

    @Test
    public void shouldReturnLevelUpFromGetLevelUpById() throws Exception {
        LevelUp levelUp = new LevelUp();
        levelUp.setLevel_up_id(1);
        levelUp.setCustomer_id(1);
        levelUp.setPoints(100);
        levelUp.setMember_date(LocalDate.of(2019, 1, 1));

        when(service.getLevelUpById(anyInt())).thenReturn(levelUp);

        String json = mapper.writeValueAsString(levelUp);

        this.mockMvc.perform(get("/levelup/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(json)));
    }

    @Test
    public void shouldReturnLevelUpFromPostLevelUp() throws Exception {
        LevelUp levelUpWithoutId = new LevelUp();
        levelUpWithoutId.setCustomer_id(1);
        levelUpWithoutId.setPoints(100);
        levelUpWithoutId.setMember_date(LocalDate.of(2019, 1, 1));

        LevelUp levelUp = new LevelUp();
        levelUp.setLevel_up_id(1);
        levelUp.setCustomer_id(1);
        levelUp.setPoints(100);
        levelUp.setMember_date(LocalDate.of(2019, 1, 1));

        when(service.addLevelUp(any(LevelUp.class))).thenReturn(levelUp);

        String requestJson = mapper.writeValueAsString(levelUpWithoutId);
        String responseJson = mapper.writeValueAsString(levelUp);

        this.mockMvc.perform(post("/levelups")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(responseJson)));

    }
}
