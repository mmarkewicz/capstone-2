package com.company.levelupservice.dao;

import com.company.levelupservice.model.LevelUp;

import java.util.List;

public interface LevelUpDao {
    LevelUp addLevelUp(LevelUp levelUp);
    LevelUp getLevelUp(int level_up_id);
    List<LevelUp> getAllLevelUps();
    void updateLevelUp(LevelUp levelUp);
    void deleteLevelUp(int level_up_id);
}
