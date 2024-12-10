package org.example.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class LiveScoreBoardTest {

    private final LiveScoreBoard board = new LiveScoreBoard();

    private Team POLAND;
    private Team GERMANY;
    private Team ENGLAND;
    private Team SPAIN;
    private Team FRANCE;
    private Team PORTUGAL;
    private Team ITALY;
    private Team CROATIA;
    private Team BRAZIL;
    private Team ARGENTINA;

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
        BRAZIL = new Team("Brazil");
        ARGENTINA = new Team("Argentina");
    }

    @Test
    public void testStart() {
        Game game_A = new Game(POLAND, GERMANY);
        Game game_B = new Game(ENGLAND, SPAIN);

        board.start(game_A);
        board.start(game_B);

        assertEquals(2, board.getListOfGames().size());
        assertNotNull(game_A.getStartTime());
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

        PORTUGAL.setScore(1);
        board.finish(game_A);
        board.finish(game_B);

        assertEquals(1, board.getListOfGames().size());
    }

    @Test
    public void testFinish_finishNotStartedGame() {
        Game game_A = new Game(POLAND, GERMANY);
        Game game_B = new Game(FRANCE, PORTUGAL);

        board.start(game_A);

        assertThrows(NoSuchElementException.class, () -> board.finish(game_B));
        assertEquals(1, board.getListOfGames().size());
    }

    @Test
    public void testUpdate() {
        Game game_A = new Game(CROATIA, GERMANY);

        board.start(game_A);
        CROATIA.setScore(1);
        board.update(game_A);

        assertEquals(1, board.getListOfGames().size());
        assertEquals(1, board.getListOfGames().getFirst().getHomeTeam().getScore());
        assertEquals(0, board.getListOfGames().getFirst().getAwayTeam().getScore());
    }

    @Test
    public void testUpdate_updateNotExistingGame() {
        Game game_A = new Game(POLAND, GERMANY);
        Game game_B = new Game(FRANCE, PORTUGAL);

        board.start(game_A);

        assertEquals(1, board.getListOfGames().size());
        assertThrows(NoSuchElementException.class, () -> board.update(game_B));
    }

    @Test
    public void testGetSummaryByTotalScore() throws InterruptedException {
        Game game_A = new Game(POLAND, GERMANY);
        Game game_B = new Game(ENGLAND, SPAIN);
        Game game_C = new Game(CROATIA, ITALY);
        Game game_D = new Game(PORTUGAL, FRANCE);
        Game game_E = new Game(BRAZIL, ARGENTINA);

        board.start(game_A);
        board.start(game_B);
        board.start(game_C);
        board.start(game_D);
        board.start(game_E);
        generatePausesBetweenStartTimes(game_A, game_B, game_C, game_D, game_E);

        POLAND.setScore(2);
        GERMANY.setScore(3);
        ENGLAND.setScore(1);
        SPAIN.setScore(5);
        CROATIA.setScore(3);
        ITALY.setScore(2);
        PORTUGAL.setScore(0);
        FRANCE.setScore(1);
        BRAZIL.setScore(5);
        ARGENTINA.setScore(0);

        board.update(game_A);
        board.update(game_B);
        board.update(game_C);
        board.update(game_D);
        board.update(game_E);

        String expectedSummary = """
                1. England 1 - Spain 5
                2. Brazil 5 - Argentina 0
                3. Croatia 3 - Italy 2
                4. Poland 2 - Germany 3
                5. Portugal 0 - France 1""";

        assertEquals(expectedSummary, board.getSummaryByTotalScore());
    }

    private void generatePausesBetweenStartTimes(Game... game) {
        for(int i=0; i<game.length; i++) {
            game[i].setStartTime(game[i].getStartTime().plus((i+1), ChronoUnit.MILLIS));
        }
    }
}