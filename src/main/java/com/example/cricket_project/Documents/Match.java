package com.example.cricket_project.Documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.PrivateKey;
import java.util.PrimitiveIterator;

@Document(collection = "Match")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long matchId;
    private long team1Id;
    private long team2Id;
    private String team1Name;
    private String team2Name;
//  private int noOfPlayers;
    private int overs;
    private String tossWinner;
    private String team1Score;
    private String team2Score;
    private String winner;

}
