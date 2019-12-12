package com.company.levelupservice.controller;

import com.company.levelupservice.model.LevelUp;
import com.company.levelupservice.service.LevelUpServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LevelUpController {

    @Autowired
    LevelUpServiceLayer service;

    @GetMapping("/levelups")
    public List<LevelUp> getAllLevelUps() throws Exception {
        return service.getAllLevelUps();
    }

    @GetMapping("/levelup/{id}")
    public LevelUp getLevelUp(@PathVariable int id) throws Exception {
        return service.getLevelUpById(id);
    }

    @PostMapping("/levelups")
    public LevelUp postLevelUp(@RequestBody LevelUp levelUp) throws Exception {
        return service.addLevelUp(levelUp);
    }

    @PutMapping("/levelups")
    public void putLevelUp(@RequestBody LevelUp levelUp) throws Exception {
        service.updateLevelUp(levelUp);
    }

    @DeleteMapping("/levelup/{id}")
    public void deleteLevelUp(@PathVariable int id) throws Exception {
        service.deleteLevelUp(id);
    }

}
