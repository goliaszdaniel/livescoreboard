package org.example.board;

public interface Board {

    void start(Game game);

    void finish(Game game);

    void update(Game game);

    String getSummaryByTotalScore();
}