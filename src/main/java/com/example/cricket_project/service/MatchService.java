package com.example.cricket_project.service;

import com.example.cricket_project.DTO.MatchInfo;
import com.example.cricket_project.Documents.Match;
import com.example.cricket_project.Documents.ScoreCard;
import com.example.cricket_project.ServiceComponents.GameService;
import com.example.cricket_project.repository.MatchRepository;
import com.example.cricket_project.repository.ScoreCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private ScoreCardRepository scoreCardRepository;
    @Autowired
    private GameService game;

    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;

    public Match startMatch(MatchInfo matchInfo){
         game.setGame(matchInfo);
         game.playGame();

         matchRepository.save(game.getMatch());
         scoreCardRepository.save(game.getScoreCard());
         teamService.saveMatch(game.getMatch());
         playerService.setPlayerStats(game.getScoreCard());

         return game.getMatch();
    }

    public Match getResultById(long id) {
        return matchRepository.findById(id).get();
    }


    public ScoreCard getScoreCardById(long id) {
        return scoreCardRepository.findById(id).get();
    }
}
