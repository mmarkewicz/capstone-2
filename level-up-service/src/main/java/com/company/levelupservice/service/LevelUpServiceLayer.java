package com.company.levelupservice.service;

import com.company.levelupservice.dao.LevelUpDaoImpl;
import com.company.levelupservice.model.LevelUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelUpServiceLayer {

    @Autowired
    LevelUpDaoImpl dao;

    public List<LevelUp> getAllLevelUps() {
        return dao.getAllLevelUps();
    }

    public LevelUp getLevelUpById(int id) {
        return dao.getLevelUp(id);
    }

    public LevelUp addLevelUp(LevelUp levelUp) {
        return dao.addLevelUp(levelUp);
    }

    public void updateLevelUp(LevelUp levelUp) {
        dao.updateLevelUp(levelUp);
    }

    public void deleteLevelUp(int id) {
        dao.deleteLevelUp(id);
    }

}
