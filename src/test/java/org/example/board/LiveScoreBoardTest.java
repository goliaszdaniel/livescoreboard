package org.example.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LiveScoreBoardTest {

    private LiveScoreBoard board = new LiveScoreBoard();

    private Team POLAND;
    private Team GERMANY;
    private Team ENGLAND;
    private Team SPAIN;
    private Team FRANCE;
    private Team PORTUGAL;
    private Team ITALY;
    private Team CROATIA;


    @BeforeEach
    public void init() {
        POLAND = new Team("Poland");
        GERMANY = new Team("Germany");
        ENGLAND = new Team("England");
        SPAIN = new Team("Spain");
        FRANCE = new Team("France");
        PORTUGAL = new Team("Portugal");
        ITALY = new Team("Italy");
        CROATIA = new Team("Croatia");
    }


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

        PORTUGAL.setScore(1);
        Game game_A = new Game(POLAND, PORTUGAL);

        assertThrows(IllegalArgumentException.class, () -> board.start(game_A));
    }

    @Test
    public void testFinish() {

        Game game_A = new Game(PORTUGAL, FRANCE);
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

        Game game_A = new Game(POLAND, GERMANY);
        Game game_B = new Game(ENGLAND, ITALY);
        Game game_C = new Game(FRANCE, PORTUGAL);

        board.start(game_A);
        board.start(game_B);

        assertEquals(2, board.getScoreBoard().size());

        game_B.getAwayTeam().setScore(1);
        board.finish(game_B);

        assertThrows(NoSuchElementException.class, () -> board.finish(game_C));
        assertEquals(1, board.getScoreBoard().size());
    }

    @Test
    public void testUpdate() {

        Game game_A = new Game(CROATIA, GERMANY);

        board.start(game_A);

        assertEquals(1, board.getScoreBoard().size());

        game_A.getHomeTeam().setScore(1);

        board.update(game_A);

        assertEquals(1, board.getScoreBoard().size());
        assertTrue(board.getScoreBoard().contains(game_A));
        assertEquals(1, board.getScoreBoard().getFirst().getHomeTeam().getScore());
        assertEquals(0, board.getScoreBoard().getFirst().getAwayTeam().getScore());
    }

    @Test
    public void testUpdate_updateNotExistingGame() {

        Game game_A = new Game(POLAND, GERMANY);
        Game game_B = new Game(FRANCE, PORTUGAL);

        board.start(game_A);

        assertEquals(1, board.getScoreBoard().size());
        assertThrows(NoSuchElementException.class, () -> board.update(game_B));
    }

}
