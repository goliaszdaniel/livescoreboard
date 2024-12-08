package org.example.board;

import java.util.List;

public class LiveScoreBoard implements Board {

    private List<Game> listOfGames;

    @Override
    public void start(Game game) {

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
