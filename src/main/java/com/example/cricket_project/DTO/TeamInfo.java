package com.example.cricket_project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamInfo {
    private String teamName;
    private List<Long> currentPlayers;
}
