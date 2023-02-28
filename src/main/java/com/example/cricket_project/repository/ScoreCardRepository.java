package com.example.cricket_project.repository;

import com.example.cricket_project.Documents.ScoreCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface ScoreCardRepository extends MongoRepository<ScoreCard,Long> {

}
