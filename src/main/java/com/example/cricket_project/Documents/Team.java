package com.example.cricket_project.Documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Document(collection ="Teams")
@Data
@AllArgsConstructor
public class Team {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private long teamId;
    private String teamName;
    private int noOfMatches;
    private int won;
    private int loss;
    private List<Long> currentPlayers;
    private List<Long> matchesList;

    public Team() {
        this.noOfMatches=0;
        this.won=0;
        this.loss=0;
        currentPlayers=new ArrayList<>();
        matchesList=new ArrayList<>();
    }

    public void addMatches(long matchId){
        matchesList.add(matchId);
    }

    public void matchIncrease(){
        noOfMatches++;
    }
    public void wonIncrease(){
        won++;
    }
    public void lossIncrease(){
        loss++;
    }
}
