package com.example.cricket_project.controller;

import com.example.cricket_project.DTO.TeamInfo;
import com.example.cricket_project.Documents.Match;
import com.example.cricket_project.Documents.Team;
import com.example.cricket_project.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping("/create")
    public long createTeam(@RequestBody TeamInfo teamInfo){
        return teamService.createTeam(teamInfo);
    }

    @GetMapping("/id/{id}")
    public Team getTeamDetailById(@PathVariable long id){
        return teamService.teamDetailById(id);
    }

    @GetMapping("/name/{teamName}")
    public Team getTeamDetailByName(@PathVariable String teamName){
        return teamService.teamDetailByName(teamName);
    }

    @GetMapping("records/{teamName}")
    public List<Match> getTeamRecord(@PathVariable String teamName){
        return teamService.teamRecord(teamName);
    }
}
