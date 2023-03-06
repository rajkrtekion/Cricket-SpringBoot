package com.example.cricket_project.ServiceComponents;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.example.cricket_project.Constants.playersCount;


@Component
public class PlayGameService {
    private int over;
    private int overs;
    private int ball;
    private int score;
    private int wicket;
    private int target;

    private List<Integer> runs=new ArrayList<>();
    private List<Integer> balls=new ArrayList<>();


    public void setGame(int overs,int target) {
        over=0;
        ball=0;
        score=0;
        wicket=0;
        this.overs=overs;
        this.target=target;

        runs.clear();
        balls.clear();

        for(int i=0;i<playersCount;i++) {
            this.runs.add(0);
            this.balls.add(0);
        }
    }


    private void setOvers() {
        if(ball==6) {ball=0;}
    }

    private int runGenerator() {
        return ((int)(Math.random()*8));
    }

    private void playOver(int target) {

        for(ball=0;ball<6;ball++){
            int run=runGenerator();
            if(run==7) {
                balls.set(wicket,balls.get(wicket)+1);
                wicket++;
                if (wicket == playersCount) {
                    ball++; break;
                }
            }
            else {
                score+=run;
                runs.set(wicket,runs.get(wicket)+run);
                balls.set(wicket,balls.get(wicket)+1);
                if(score>target) {
                    ball++; break;
                }
            }
        }
        if(ball==6) over++;
    }

    public String play(){
        while(over<overs && score<=target && wicket<playersCount) {
            playOver(target);
        }
        setOvers();
        return String.valueOf(score)+"/"+String.valueOf(wicket)+"   "+String.valueOf(over)+"."+String.valueOf(ball);
    }

    public int getScore(){
        return score;
    }
    public List<Integer> getRuns(){
        return runs;
    }
    public List<Integer> getBalls(){
        return balls;
    }

}
