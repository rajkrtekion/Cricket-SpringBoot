package com.example.cricket_project.configuration;
import com.example.cricket_project.Documents.Match;
import com.example.cricket_project.Documents.ScoreCard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {

//    @Bean
//    public GameService game(){
//        return new GameService();
//    }

    @Bean
    public Match match(){
        return new Match();
    }

    @Bean
    public ScoreCard scoreCard(){
        return new ScoreCard();
    }
}
