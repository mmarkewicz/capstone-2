package com.company.levelupservice.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
