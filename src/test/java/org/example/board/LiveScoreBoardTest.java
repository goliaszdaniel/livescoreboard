package org.example.board;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LiveScoreBoardTest {

    private LiveScoreBoard board = new LiveScoreBoard();

    private static final Team POLAND = new Team("Poland");
    private static final Team GERMANY = new Team("Germany");
    private static final Team ENGLAND = new Team("England");
    private static final Team SPAIN = new Team("Spain");
    private static final Team FRANCE = new Team("France");
    private static final Team ITALY = new Team("Italy");


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

        Team portugal = new Team("Portugal", 1);

        Game game_A = new Game(POLAND, portugal);

        assertThrows(IllegalArgumentException.class, () -> board.start(game_A));
    }

    @Test
    public void testFinish() {

        Team portugal = new Team("Portugal", 0);
        Game game_A = new Game(portugal, FRANCE);
        Game game_B = new Game(ENGLAND, SPAIN);
        Game game_C = new Game(POLAND, GERMANY);

        board.start(game_A);
        board.start(game_B);
        board.start(game_C);

        assertEquals(3, board.getScoreBoard().size());

        game_A.getHomeTeam().setScore(1);
        board.finish(game_A);
        board.finish(game_B);

        assertEquals(1, board.getScoreBoard().size());
    }

    @Test
    public void testFinish_finishNotExistingGame() {

        Team italy = new Team("Italy", 0);
        Game game_A = new Game(POLAND, GERMANY);
        Game game_B = new Game(ENGLAND, italy);
        Game game_C = new Game(FRANCE, ITALY);

        board.start(game_A);
        board.start(game_B);

        assertEquals(2, board.getScoreBoard().size());

        game_B.getAwayTeam().setScore(1);
        board.finish(game_B);

        assertThrows(NoSuchElementException.class, () -> board.finish(game_C));
        assertEquals(1, board.getScoreBoard().size());
    }
}
