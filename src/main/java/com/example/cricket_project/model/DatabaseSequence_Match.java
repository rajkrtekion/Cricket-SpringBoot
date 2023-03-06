package com.example.cricket_project.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "database_sequences_match")
public class DatabaseSequence_Match {

    @Id
    private String id;

    private long seq;

    //getters and setters omitted
}