package com.example.cricket_project.repository;

import com.example.cricket_project.Documents.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository extends MongoRepository<Team,Long> {
    Team findByTeamName(String teamName);
}
