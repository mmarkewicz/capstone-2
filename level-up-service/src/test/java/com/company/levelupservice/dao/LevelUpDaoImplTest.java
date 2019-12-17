package com.company.levelupservice.dao;

import com.company.levelupservice.model.LevelUp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LevelUpDaoImplTest {

    @Autowired
    LevelUpDaoImpl dao;

    @Before
    public void setUp() {
        dao.getAllLevelUps().forEach(levelUp -> dao.deleteLevelUp(levelUp.getLevel_up_id()));
    }

    @After
    public void tearDown() {
        dao.getAllLevelUps().forEach(levelUp -> dao.deleteLevelUp(levelUp.getLevel_up_id()));
    }

    @Test
    public void shouldAddGetDeleteFromDb() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomer_id(1);
        levelUp.setMember_date(LocalDate.of(2019, 1, 1));
        levelUp.setPoints(100);
        levelUp = dao.addLevelUp(levelUp);

        assertEquals(levelUp.toString(), dao.getLevelUp(levelUp.getLevel_up_id()).toString());

        dao.deleteLevelUp(levelUp.getLevel_up_id());

        assertEquals(dao.getAllLevelUps().size(), 0);
    }

    @Test
    public void shouldUpdateDb() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomer_id(1);
        levelUp.setMember_date(LocalDate.of(2019, 1, 1));
        levelUp.setPoints(100);
        levelUp = dao.addLevelUp(levelUp);

        levelUp.setPoints(1000);

        dao.updateLevelUp(levelUp);

        assertEquals(dao.getLevelUp(levelUp.getLevel_up_id()).getPoints(), 1000);
    }

    @Test
    public void shouldGetAllLevelUpsFromDb() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomer_id(1);
        levelUp.setMember_date(LocalDate.of(2019, 1, 1));
        levelUp.setPoints(100);
        levelUp = dao.addLevelUp(levelUp);

        LevelUp levelUp2 = new LevelUp();
        levelUp2.setCustomer_id(2);
        levelUp2.setMember_date(LocalDate.of(2019, 2, 1));
        levelUp2.setPoints(300);
        levelUp2 = dao.addLevelUp(levelUp2);

        assertEquals(dao.getAllLevelUps().size(), 2);
    }
}
