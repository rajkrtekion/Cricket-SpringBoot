package com.example.cricket_project.controller;


import com.example.cricket_project.DTO.PlayerInfo;
import com.example.cricket_project.DTO.PlayerRecord;
import com.example.cricket_project.Documents.Player;
import com.example.cricket_project.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/player")
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @PostMapping("/create")
    public long createPlayer(@RequestBody PlayerInfo playerInfo){
            return playerService.createPlayer(playerInfo);
    }

    @GetMapping("/id/{id}")
    public Player getPlayerDetail(@PathVariable long id){
        return playerService.PlayerDetail(id);
    }

    @GetMapping("/name/{playerName}")
    public Player getPlayerDetailByName(@PathVariable String playerName){
        return playerService.playerDetailByName(playerName);
    }

    @GetMapping("/playerRecord/{playerName}")
    public List<PlayerRecord> getPlayerRecord(@PathVariable String playerName){
        return playerService.PlayerRecord(playerName);
    }
}
