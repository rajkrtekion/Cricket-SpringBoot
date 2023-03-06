package com.example.cricket_project.Documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Document(collection = "Players")
@Data
@AllArgsConstructor
public class Player {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private long playerId;
    private long currentTeamId;
    private String currentTeamName;
    private String playerName;
    private String playerType;
    private int age;
    private int noOfMatches;
    private int runs;
    private int wickets;


    public Player() {
        this.noOfMatches=0;
        this.runs=0;
        this.wickets=0;
    }

    public void matchesIncrease(){
        noOfMatches++;
    }
//    public void addMatches(long matchId,int team){
//        matchesList.add(matchId);
//    }
    public void runsIncrease(int runs){
        this.runs+=runs;
    }

}
