package com.example.cricket_project.repository;

import com.example.cricket_project.Documents.PlayerMatchesInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerMatchesInfoRepository extends MongoRepository<PlayerMatchesInfo,Long> {
}
