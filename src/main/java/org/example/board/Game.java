package org.example.board;

import java.time.LocalDateTime;

public class Game {

    private Team homeTeam;
    private Team awayTeam;
    private LocalDateTime startTime;

    public Game(Team homeTeam, Team awayTeam) {

        if(homeTeam.getName().equals(awayTeam.getName())) {
            throw new IllegalArgumentException("Home and away team names must not be the same.");
        }
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startTime = LocalDateTime.now();
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public boolean containsAnyTeamAs(Game game) {
        return this.homeTeam.getName().equals(game.getHomeTeam().getName())
                || this.homeTeam.getName().equals(game.getAwayTeam().getName())
                || this.awayTeam.getName().equals(game.getHomeTeam().getName())
                || this.awayTeam.getName().equals(game.getAwayTeam().getName());
    }

    public boolean containsTheSameTeamsAs(Game game) {
        return this.homeTeam.getName().equals(game.getHomeTeam().getName())
                && this.awayTeam.getName().equals(game.getAwayTeam().getName());
    }

}