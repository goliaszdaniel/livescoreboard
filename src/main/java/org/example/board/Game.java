package org.example.board;

import java.time.LocalDateTime;

public class Game {

    private final Team homeTeam;
    private final Team awayTeam;
    private LocalDateTime startTime;

    public Game(Team homeTeam, Team awayTeam) {
        if(homeTeam.getName().equals(awayTeam.getName())) {
            throw new IllegalArgumentException("Home and away team names must not be the same.");
        }
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public LocalDateTime getStartTime() {
        if(startTime == null) {
            throw new RuntimeException("Start time has not been initialized.");
        }
        return startTime;
    }

    void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    void initStartTime() {
        startTime = LocalDateTime.now();
    }

    boolean containsAnyTeamNameAs(Game game) {
        return this.homeTeam.getName().equals(game.getHomeTeam().getName())
                || this.homeTeam.getName().equals(game.getAwayTeam().getName())
                || this.awayTeam.getName().equals(game.getHomeTeam().getName())
                || this.awayTeam.getName().equals(game.getAwayTeam().getName());
    }

    boolean containsTheSameTeamNamesAs(Game game) {
        return this.homeTeam.getName().equals(game.getHomeTeam().getName())
                && this.awayTeam.getName().equals(game.getAwayTeam().getName());
    }

    int getTotalNumberOfGoals() {
        return homeTeam.getScore() + awayTeam.getScore();
    }

    @Override
    public String toString() {
        return homeTeam.toString() + " - " + awayTeam.toString();
    }
}