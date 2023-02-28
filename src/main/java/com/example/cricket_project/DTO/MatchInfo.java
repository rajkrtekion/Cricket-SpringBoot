package com.example.cricket_project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchInfo {
    private String team1Name;
    private String team2Name;
    private int overs;
    private int noOfPlayers;
}
