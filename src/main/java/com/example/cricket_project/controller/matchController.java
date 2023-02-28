package com.example.cricket_project.controller;

import com.example.cricket_project.Documents.Match;
import com.example.cricket_project.Documents.ScoreCard;
import com.example.cricket_project.DTO.MatchInfo;
import com.example.cricket_project.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/match")
public class matchController{
    @Autowired
    private MatchService matchService;

    @PostMapping
    public Match startMatch(@RequestBody MatchInfo matchInfo) {
        return matchService.startMatch(matchInfo);
    }

    @GetMapping("/result/{id}")
    public Match getMatchResult(@PathVariable long id) {
        return matchService.getResultById(id);
    }

    @GetMapping("/scorecard/{id}")
    public ScoreCard getScoreCard(@PathVariable long id){
        return matchService.getScoreCardById(id);
    }
}
