package com.example.cricket_project.service;
import static com.example.cricket_project.Constants.playersCount;

import com.example.cricket_project.DTO.PlayerInfo;
import com.example.cricket_project.DTO.PlayerRecord;
import com.example.cricket_project.Documents.*;
import com.example.cricket_project.SequenceGenerator.PlayerSequenceGeneratorService;
import com.example.cricket_project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService{
    @Autowired
    private PlayerMatchesInfoRepository playerMatchesInfoRepository;
    @Autowired
    private ScoreCardRepository scoreCardRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerSequenceGeneratorService playerSequenceGeneratorService;
    @Autowired
    private Player player;

    @Autowired
    private PlayerMatchesInfo playerMatchesInfo;

    public long createPlayer(PlayerInfo playerInfo){
        player.setPlayerId(playerSequenceGeneratorService.generateSequence(Player.SEQUENCE_NAME));
        player.setCurrentTeamId(0);
        player.setCurrentTeamName("NULL");
        player.setPlayerName(playerInfo.getPlayerName());
        player.setPlayerType(playerInfo.getPlayerType());
        player.setAge(playerInfo.getAge());
        playerRepository.save(player);

        playerMatchesInfo.setPlayerId(player.getPlayerId());
        playerMatchesInfoRepository.save(playerMatchesInfo);

        return player.getPlayerId();
    }

    public Player PlayerDetail(long id){
        return playerRepository.findById(id).get();
    }

    public Player playerDetailByName(String playerName){
        return playerRepository.findByPlayerName(playerName);
    }

    public List<PlayerRecord> PlayerRecord(String playerName){
        Player player1=playerRepository.findByPlayerName(playerName);
        PlayerMatchesInfo playerMatchesInfo=playerMatchesInfoRepository.findById(player1.getPlayerId()).get();

        List<Long> matchesList=playerMatchesInfo.getMatchesList();
        List<Long> playerTeams=playerMatchesInfo.getPlayerTeam();
        List<Long> opponentTeams=playerMatchesInfo.getOpponentTeam();
        List<Integer> playingPositions=playerMatchesInfo.getPosition();

        List<PlayerRecord> recordList=new ArrayList<>();

        for(int matchNo=0;matchNo<matchesList.size();matchNo++){
            ScoreCard scoreCard=scoreCardRepository.findById(matchesList.get(matchNo)).get();
            Team playerTeam=teamRepository.findById(playerTeams.get(matchNo)).get();
            Team opponentTeam=teamRepository.findById(opponentTeams.get(matchNo)).get();

            int runs;
            int balls;
            int wickets;
            int balls_bowled;

            if(playerTeam.getTeamId()==scoreCard.getTeam1Id()){
                runs=scoreCard.getTeam1PlayersRuns().get(playingPositions.get(matchNo));
                balls=scoreCard.getTeam1PlayerBalls().get(playingPositions.get(matchNo));
            }
            else{
                runs=scoreCard.getTeam2PlayersRuns().get(playingPositions.get(matchNo));
                balls=scoreCard.getTeam2PlayerBalls().get(playingPositions.get(matchNo));
            }

            PlayerRecord playerRecord=new PlayerRecord();
            playerRecord.setMatchId(matchesList.get(matchNo));
            playerRecord.setPlayerId(player1.getPlayerId());
            playerRecord.setPlayerName(player1.getPlayerName());
            playerRecord.setPlayerTeam(playerTeam.getTeamName());
            playerRecord.setOpponentTeam(opponentTeam.getTeamName());
            playerRecord.setRuns(runs);
            playerRecord.setBalls(balls);
            playerRecord.setWickets(0);
            playerRecord.setBalls_bowled(0);

            recordList.add(playerRecord);
        }

        return recordList;

    }

    public void setPlayerStats(ScoreCard scoreCard){
        Team team1=teamRepository.findByTeamName(scoreCard.getTeam1());
        Team team2=teamRepository.findByTeamName(scoreCard.getTeam2());

        List<Integer> team1runs=scoreCard.getTeam1PlayersRuns();
        List<Integer> team2runs=scoreCard.getTeam2PlayersRuns();

        List<Long> team1Players=team1.getCurrentPlayers();
        List<Long> team2Players=team2.getCurrentPlayers();


        //Team-1 Players
        for(int team1PlayerNo=0;team1PlayerNo<playersCount;team1PlayerNo++) {
            Player player=playerRepository.findById(team1Players.get(team1PlayerNo)).get();
            player.matchesIncrease();
            player.runsIncrease(team1runs.get(team1PlayerNo));
            playerRepository.save(player);

            PlayerMatchesInfo playerMatchesInfo1=playerMatchesInfoRepository.findById(player.getPlayerId()).get();
            playerMatchesInfo1.addMatch(scoreCard.getScoreCardId(),team1.getTeamId(),team2.getTeamId(),team1PlayerNo);
            playerMatchesInfoRepository.save(playerMatchesInfo1);

        }

        //Team-2 Players
        for(int team2PlayerNo=0;team2PlayerNo<playersCount;team2PlayerNo++) {
            Player player=playerRepository.findById(team2Players.get(team2PlayerNo)).get();
            player.matchesIncrease();
            player.runsIncrease(team2runs.get(team2PlayerNo));
            playerRepository.save(player);

            PlayerMatchesInfo playerMatchesInfo1=playerMatchesInfoRepository.findById(player.getPlayerId()).get();
            playerMatchesInfo1.addMatch(scoreCard.getScoreCardId(),team2.getTeamId(),team1.getTeamId(),team2PlayerNo);
            playerMatchesInfoRepository.save(playerMatchesInfo1);
        }

    }
}
