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

    public List<LevelUp> getAllLevelUps() throws Exception {
        try {
            return dao.getAllLevelUps();
        } catch (Exception e) {
            throw new Exception("Could not get all level ups");
        }
    }

    public LevelUp getLevelUpById(int id) throws Exception {
        try {
            return dao.getLevelUp(id);
        } catch (Exception e) {
            throw new Exception("Could not get level up by ID");
        }
    }

    public LevelUp addLevelUp(LevelUp levelUp) throws Exception {
        try {
            return dao.addLevelUp(levelUp);
        } catch (Exception e) {
            throw new Exception("Could not add level up");
        }
    }

    public void updateLevelUp(LevelUp levelUp) throws Exception {
        try {
            dao.updateLevelUp(levelUp);
        } catch (Exception e) {
            throw new Exception("Could not update level up");
        }
    }

    public void deleteLevelUp(int id) throws Exception {
        try {
            dao.deleteLevelUp(id);
        } catch (Exception e) {
            throw new Exception("Could not delete level up");
        }
    }

}
