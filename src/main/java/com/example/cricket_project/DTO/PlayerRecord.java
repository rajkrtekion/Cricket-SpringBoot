package com.example.cricket_project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRecord {
    private long playerId;
    private String playerName;
    private long matchId;
    private String playerTeam;
    private String opponentTeam;
    private int runs;
    private  int balls;
    private  int wickets;
    private  int balls_bowled;
}
