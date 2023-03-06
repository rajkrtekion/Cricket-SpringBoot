package com.example.cricket_project.ServiceComponents;

import com.example.cricket_project.DTO.MatchInfo;
import com.example.cricket_project.Documents.Match;
import com.example.cricket_project.Documents.ScoreCard;
import com.example.cricket_project.SequenceGenerator.MatchSequenceGeneratorService;
import com.example.cricket_project.ServiceComponents.PlayGameService;
import com.example.cricket_project.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private Match match;
    @Autowired
    private ScoreCard scoreCard;
    @Autowired
    private MatchSequenceGeneratorService sequenceGeneratorService;


    private PlayGameService playgame1=new PlayGameService();

    private PlayGameService playgame2=new PlayGameService();

    public long  setGame(MatchInfo matchInfo){
        match.setMatchId(sequenceGeneratorService.generateSequence(Match.SEQUENCE_NAME));

        match.setTeam1Id(teamRepository.findByTeamName(matchInfo.getTeam1Name()).getTeamId());
        match.setTeam2Id(teamRepository.findByTeamName(matchInfo.getTeam2Name()).getTeamId());
        match.setTeam1Name(matchInfo.getTeam1Name());
        match.setTeam2Name(matchInfo.getTeam2Name());
        match.setOvers(matchInfo.getOvers());
//      match.setNoOfPlayers(matchInfo.getNoOfPlayers());

        return match.getMatchId();
    }

    public void playGame(){

        int tossVal=(int)(Math.random()*2);
        int team1Score;
        int team2Score;

        if(tossVal==0){
            match.setTossWinner(match.getTeam1Name());

            //Team-1 Playing
            playgame1.setGame(match.getOvers(), Integer.MAX_VALUE);
            match.setTeam1Score(playgame1.play());
            team1Score=playgame1.getScore();

            //Team-2 Playing
            playgame2.setGame(match.getOvers(),team1Score);
            match.setTeam2Score(playgame2.play());
            team2Score=playgame2.getScore();
        }
        else {
            match.setTossWinner(match.getTeam2Name());

            //Team-2 Playing
            playgame2.setGame(match.getOvers(),Integer.MAX_VALUE);
            match.setTeam2Score(playgame2.play());
            team2Score = playgame2.getScore();

            //Team-1 Playing
            playgame1.setGame(match.getOvers(),team2Score);
            match.setTeam1Score(playgame1.play());
            team1Score = playgame1.getScore();
        }


        //Setting Winner
        if(team1Score>team2Score) match.setWinner(match.getTeam1Name());
        else if(team1Score<team2Score) match.setWinner(match.getTeam2Name());
        else match.setWinner("Tied");

        //Setting ScoreCard
        scoreCard.setScoreCardId(match.getMatchId());
        scoreCard.setTeam1Id(match.getTeam1Id());
        scoreCard.setTeam2Id(match.getTeam2Id());
        scoreCard.setTeam1(match.getTeam1Name());
        scoreCard.setTeam2(match.getTeam2Name());
        scoreCard.setTossWinner(match.getTossWinner());
        scoreCard.setTeam1Score(match.getTeam1Score());
        scoreCard.setTeam2Score(match.getTeam2Score());
        scoreCard.setTeam1PlayersRuns(playgame1.getRuns());
        scoreCard.setTeam1PlayerBalls(playgame1.getBalls());
        scoreCard.setTeam2PlayersRuns(playgame2.getRuns());
        scoreCard.setTeam2PlayerBalls(playgame2.getBalls());
        scoreCard.setWinner(match.getWinner());

    }

    public Match getMatch(){
        return match;
    }
    public ScoreCard getScoreCard(){
        return scoreCard;
    }

}
