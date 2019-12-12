package com.company.levelupservice.controller;

import com.company.levelupservice.model.LevelUp;
import com.company.levelupservice.service.LevelUpServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LevelUpController {

    @Autowired
    LevelUpServiceLayer service;

    @GetMapping("/levelups")
    @ResponseStatus(value = HttpStatus.OK)
    public List<LevelUp> getAllLevelUps() throws Exception {
        return service.getAllLevelUps();
    }

    @GetMapping("/levelup/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public LevelUp getLevelUp(@PathVariable int id) throws Exception {
        return service.getLevelUpById(id);
    }

    @PostMapping("/levelups")
    @ResponseStatus(value = HttpStatus.CREATED)
    public LevelUp postLevelUp(@RequestBody LevelUp levelUp) throws Exception {
        return service.addLevelUp(levelUp);
    }

    @PutMapping("/levelups")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void putLevelUp(@RequestBody LevelUp levelUp) throws Exception {
        service.updateLevelUp(levelUp);
    }

    @DeleteMapping("/levelup/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteLevelUp(@PathVariable int id) throws Exception {
        service.deleteLevelUp(id);
    }

}
