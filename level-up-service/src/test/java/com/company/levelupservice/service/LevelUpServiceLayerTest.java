package com.company.levelupservice.service;

import com.company.levelupservice.dao.LevelUpDaoImpl;
import com.company.levelupservice.model.LevelUp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LevelUpServiceLayerTest {

    LevelUpServiceLayer service;

    @Mock
    LevelUpDaoImpl dao;

    @Before
    public void setUp() {
        setUpMocks();
        service = new LevelUpServiceLayer(dao);
    }

    @Test
    public void shouldReturnLevelUpFromGetLevelUpById() throws Exception {
        LevelUp levelUp = new LevelUp();
        levelUp.setLevel_up_id(1);
        levelUp.setCustomer_id(1);
        levelUp.setPoints(100);
        levelUp.setMember_date(LocalDate.of(2019, 1, 1));

        assertEquals(service.getLevelUpById(1).toString(), levelUp.toString());
    }

    @Test
    public void shouldReturnLevelUpListFromGetAllLevelUps() throws Exception {
        LevelUp levelUp = new LevelUp();
        levelUp.setLevel_up_id(1);
        levelUp.setCustomer_id(1);
        levelUp.setPoints(100);
        levelUp.setMember_date(LocalDate.of(2019, 1, 1));

        List<LevelUp> levelUpList = new ArrayList<>();
        levelUpList.add(levelUp);

        assertEquals(levelUpList.toString(), service.getAllLevelUps().toString());
    }

    @Test
    public void shouldReturnLevelUpFromAddLevelUp() throws Exception {
        LevelUp levelUp = new LevelUp();
        levelUp.setLevel_up_id(1);
        levelUp.setCustomer_id(1);
        levelUp.setPoints(100);
        levelUp.setMember_date(LocalDate.of(2019, 1, 1));

        LevelUp levelUpWithoutId = new LevelUp();
        levelUpWithoutId.setCustomer_id(1);
        levelUpWithoutId.setPoints(100);
        levelUpWithoutId.setMember_date(LocalDate.of(2019, 1, 1));

        assertEquals(levelUp.toString(), service.addLevelUp(levelUpWithoutId).toString());
    }

    private void setUpMocks() {
        // dao.getAllLevelUps()
        // dao.getLevelUpById(id)
        // dao.addLevelUp(LevelUp)

        LevelUp levelUp = new LevelUp();
        levelUp.setLevel_up_id(1);
        levelUp.setCustomer_id(1);
        levelUp.setPoints(100);
        levelUp.setMember_date(LocalDate.of(2019, 1, 1));

        List<LevelUp> levelUpList = new ArrayList<>();
        levelUpList.add(levelUp);

        doReturn(levelUp).when(dao).getLevelUp(anyInt());
        doReturn(levelUpList).when(dao).getAllLevelUps();
        doReturn(levelUp).when(dao).addLevelUp(any(LevelUp.class));
    }
}
