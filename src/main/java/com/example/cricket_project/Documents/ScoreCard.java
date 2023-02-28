package com.example.cricket_project.Documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Document(collection = "ScoreCard")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreCard {
    @Id
    private long scoreCardId;
    private String team1;
    private String team2;
    private String tossWinner;
    private String team1Score;
    private String team2Score;
    private List<Integer> team1PlayersRuns;
    private List<Integer> team1PlayerBalls;
    private List<Integer> team2PlayersRuns;
    private List<Integer> team2PlayerBalls;
    private String winner;

    @Override
    public String toString() {
        return "ScoreCard{" +
                "scoreCardId=" + scoreCardId +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", tossWinner='" + tossWinner + '\'' +
                ", team1Score='" + team1Score + '\'' +
                ", team2Score='" + team2Score + '\'' +
                ", team1PlayersRuns=" + team1PlayersRuns +
                ", team1PlayerBalls=" + team1PlayerBalls +
                ", team2PlayersRuns=" + team2PlayersRuns +
                ", team2PlayerBalls=" + team2PlayerBalls +
                ", winner='" + winner + '\'' +
                '}';
    }
}
