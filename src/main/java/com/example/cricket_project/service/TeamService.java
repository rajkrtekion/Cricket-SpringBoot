package com.example.cricket_project.service;

import com.example.cricket_project.DTO.TeamInfo;
import com.example.cricket_project.Documents.Match;
import com.example.cricket_project.Documents.Player;
import com.example.cricket_project.Documents.Team;
import com.example.cricket_project.SequenceGenerator.TeamSequenceGeneratorService;
import com.example.cricket_project.repository.MatchRepository;
import com.example.cricket_project.repository.PlayerRepository;
import com.example.cricket_project.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.cricket_project.Constants.playersCount;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private TeamSequenceGeneratorService teamSequenceGeneratorService;
    @Autowired
    private Team team;

    public long createTeam(TeamInfo teamInfo){
        team.setTeamId(teamSequenceGeneratorService.generateSequence(Team.SEQUENCE_NAME));
        team.setTeamName(teamInfo.getTeamName());
        team.setCurrentPlayers(teamInfo.getCurrentPlayers());

        for(int playerNo=0;playerNo<playersCount;playerNo++){
            Player player=playerRepository.findById(team.getCurrentPlayers().get(playerNo)).get();
            player.setCurrentTeamId(team.getTeamId());
            player.setCurrentTeamName(team.getTeamName());
            playerRepository.save(player);
        }
        teamRepository.save(team);

        return team.getTeamId();
    }

    public Team teamDetailById(long id){
        return teamRepository.findById(id).get();
    }
    public Team teamDetailByName(String teamName){
        return teamRepository.findByTeamName(teamName);
    }

    public List<Match> teamRecord(String teamName){
        Team team=teamRepository.findByTeamName(teamName);
        List<Long> matches=team.getMatchesList();
        List<Match> matchList=new ArrayList<>();

        for(int matchNo=0;matchNo<matches.size();matchNo++){
            Match match=matchRepository.findById(matches.get(matchNo)).get();
            matchList.add(match);
        }
        return matchList;
    }

    public void saveMatch(Match match){
        long matchId=match.getMatchId();
        String teamName1= match.getTeam1Name();
        String teamName2= match.getTeam2Name();

        Team team1=teamRepository.findByTeamName(teamName1);
        Team team2=teamRepository.findByTeamName(teamName2);

        team1.matchIncrease();
        team2.matchIncrease();

        String winner= match.getWinner();

        if(winner.equals(teamName1)){
            team1.wonIncrease(); team2.lossIncrease();
        }
        else{
            team2.wonIncrease(); team1.lossIncrease();
        }

        team1.addMatches(matchId);
        team2.addMatches(matchId);

        teamRepository.save(team1);
        teamRepository.save(team2);

    }

}
