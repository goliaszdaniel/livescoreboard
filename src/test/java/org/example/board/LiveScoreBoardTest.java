package org.example.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LiveScoreBoardTest {

    private LiveScoreBoard board = new LiveScoreBoard();

    private static final Team POLAND = new Team("Poland");
    private static final Team GERMANY = new Team("Germany");
    private static final Team ENGLAND = new Team("England");
    private static final Team SPAIN = new Team("Spain");
    private static final Team FRANCE = new Team("France");
    private static final Team PORTUGAL = new Team("Portugal");

    @Test
    public void testStart() {

        Game game_A = new Game(POLAND, GERMANY);
        Game game_B = new Game(ENGLAND, SPAIN);

        board.start(game_A);
        board.start(game_B);

        assertEquals(2, board.getScoreBoard().size());
    }

    @Test
    public void testStart_startGameForTeamsThatAreAlreadyPlaying() {

        Game game_A = new Game(POLAND, GERMANY);
        Game game_B = new Game(ENGLAND, SPAIN);
        Game game_C = new Game(ENGLAND, SPAIN);
        Game game_D = new Game(FRANCE, POLAND);

        board.start(game_A);
        board.start(game_B);
        assertThrows(IllegalArgumentException.class, () -> board.start(game_C));
        assertThrows(IllegalArgumentException.class, () -> board.start(game_D));
    }

    @Test
    public void testStart_startGameWithGoalsScored() {

        POLAND.setScore(1);
        Game game_A = new Game(POLAND, GERMANY);

        assertThrows(IllegalArgumentException.class, () -> board.start(game_A));
    }
}
