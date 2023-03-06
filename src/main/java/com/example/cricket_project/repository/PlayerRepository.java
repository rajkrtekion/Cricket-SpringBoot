package com.example.cricket_project.repository;

import com.example.cricket_project.Documents.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player,Long> {
    Player findByPlayerName(String playerName);
}
