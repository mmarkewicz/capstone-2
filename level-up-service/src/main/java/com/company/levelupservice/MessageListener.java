package com.company.levelupservice;

import com.company.levelupservice.model.LevelUp;
import com.company.levelupservice.service.LevelUpServiceLayer;
import com.company.levelupservice.util.message.LevelUpEntry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageListener {

    @Autowired
    LevelUpServiceLayer service;

    @RabbitListener(queues = LevelUpServiceApplication.QUEUE_NAME)
    public void receiveMessage(LevelUpEntry msg) throws Exception {
        List<LevelUp> levelUpList = service.getAllLevelUps().stream().filter(levelUp -> levelUp.getCustomer_id() == msg.getCustomerId()).collect(Collectors.toList());
        LevelUp levelUp = levelUpList.get(0);

        levelUp.setPoints(levelUp.getPoints() + msg.getPoints());
        service.updateLevelUp(levelUp);
    }
}