package com.example.cricket_project.SequenceGenerator;

import com.example.cricket_project.model.DatabaseSequence_Match;
import com.example.cricket_project.model.DatabaseSequence_Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class TeamSequenceGeneratorService {


    private MongoOperations mongoOperations;

    @Autowired
    public TeamSequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generateSequence(String seqName) {
        DatabaseSequence_Team counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence_Team.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
