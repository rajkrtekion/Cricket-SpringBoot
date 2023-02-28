package com.example.cricket_project.repository;

import com.example.cricket_project.Documents.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface MatchRepository extends MongoRepository<Match,Long> {

}
