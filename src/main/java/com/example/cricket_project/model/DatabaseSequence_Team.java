package com.example.cricket_project.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "database_sequences_team")
public class DatabaseSequence_Team {

    @Id
    private String id;

    private long seq;

    //getters and setters omitted
}
