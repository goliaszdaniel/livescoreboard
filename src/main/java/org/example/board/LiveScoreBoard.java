package org.example.board;

import java.util.ArrayList;
import java.util.List;

public class LiveScoreBoard implements Board {

    private List<Game> listOfGames = new ArrayList<>();

    @Override
    public void start(Game game) {

        if(game.getHomeTeam().getScore() != 0 || game.getAwayTeam().getScore() != 0) {
            throw new IllegalArgumentException("Starting score has to be 0-0");
        }
        for(Game existingGame : listOfGames) {
            if(existingGame.containsAnyTeamAs(game)) {
                throw new IllegalArgumentException("Cannot start a game for the team that is already playing");
            }
        }

        listOfGames.add(game);
    }

    @Override
    public void finish(Game game) {

    }

    @Override
    public void update(Game game) {

    }

    @Override
    public String getSummaryByTotalScore() {
        return null;
    }

    public List<Game> getScoreBoard() {
        return listOfGames;
    }
}
