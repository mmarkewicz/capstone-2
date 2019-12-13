package com.company.retailapiservice.feign;

import com.company.retailapiservice.model.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "level-up-service")
public interface LevelUpServiceFeign {

    @GetMapping("/levelups")
    List<LevelUp> getAllLevelUps();

    @GetMapping("/levelup/{id}")
    LevelUp getLevelUp(@PathVariable int id);

    @PostMapping("/levelups")
    @ResponseStatus(value = HttpStatus.CREATED)
    LevelUp postLevelUp(@RequestBody LevelUp levelUp);

    @PutMapping("/levelups")
    void putLevelUp(@RequestBody LevelUp levelUp);

    @DeleteMapping("/levelup/{id}")
    void deleteLevelUp(@PathVariable int id);
}
