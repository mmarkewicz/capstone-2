package com.company.adminservice.util;

import com.company.adminservice.model.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "level-up-service")
public interface LevelUpClient {

    @GetMapping("/levelups")
    List<LevelUp> getAllLevelUps();

    @GetMapping("/levelup/{id}")
    LevelUp getLevelUp(@PathVariable int id);

    @PostMapping("/levelups")
    LevelUp postLevelUp(@RequestBody LevelUp levelUp);

    @PutMapping("/levelups")
    void putLevelUp(@RequestBody LevelUp levelUp);

    @DeleteMapping("/levelup/{id}")
    void deleteLevelUp(@PathVariable int id);

}
