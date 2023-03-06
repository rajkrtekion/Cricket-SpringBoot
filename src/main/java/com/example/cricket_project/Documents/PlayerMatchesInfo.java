package com.example.cricket_project.Documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Document(collection = "PlayerMatchesInfo")
@Data
@AllArgsConstructor
public class PlayerMatchesInfo {
    @Id
    private long playerId;

    private List<Long> matchesList;
    private List<Long> playerTeam;
    private List<Long> opponentTeam;
    private List<Integer> position;

    public PlayerMatchesInfo(){
        this.matchesList=new ArrayList<>();
        this.playerTeam=new ArrayList<>();
        this.opponentTeam=new ArrayList<>();
        this.position=new ArrayList<>();
    }

    public void addMatch(long matchId,long playerTeam,long opponentTeam,int position){
        this.matchesList.add(matchId);
        this.playerTeam.add(playerTeam);
        this.opponentTeam.add(opponentTeam);
        this.position.add(position);
    }
}
