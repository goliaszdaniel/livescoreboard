package org.example.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void testConstructor() {
        Team team_A = new Team("Poland");
        Team team_B = new Team("England");

        Game game = new Game(team_A, team_B);

        assertEquals("Poland", game.getHomeTeam().getName());
        assertEquals("England", game.getAwayTeam().getName());
    }

    @Test
    public void testConstructor_twoEqualTeamNames() {
        Team team_A = new Team("Poland");
        Team team_B = new Team("Poland");

        assertThrows(IllegalArgumentException.class, () -> new Game(team_A, team_B));
    }

    @Test
    public void testContainsAnyTeamAs() {
        Team team_A = new Team("Poland");
        Team team_B = new Team("England");
        Team team_C = new Team("Spain");
        Team team_D = new Team("Germany");
        Team team_E = new Team("Portugal");

        Game game_A = new Game(team_A, team_B);
        Game game_B = new Game(team_C, team_D);
        Game game_C = new Game(team_D, team_E);
        Game game_D = new Game(team_D, team_E);

        assertFalse(game_A.containsAnyTeamAs(game_B));

        assertTrue(game_B.containsAnyTeamAs(game_C));
        assertTrue(game_C.containsAnyTeamAs(game_B));
        assertTrue(game_C.containsAnyTeamAs(game_D));
    }

    @Test
    public void testContainsTheSameTeamsAs() {
        Team team_A = new Team("Poland");
        Team team_B = new Team("England");
        Team team_C = new Team("Spain");
        Team team_D = new Team("Germany");
        Team team_E = new Team("Portugal");

        Game game_A = new Game(team_A, team_B);
        Game game_B = new Game(team_C, team_D);
        Game game_C = new Game(team_D, team_E);
        Game game_D = new Game(team_D, team_E);

        assertFalse(game_A.containsTheSameTeamsAs(game_B));
        assertFalse(game_B.containsTheSameTeamsAs(game_C));
        assertFalse(game_C.containsTheSameTeamsAs(game_B));

        assertTrue(game_C.containsTheSameTeamsAs(game_D));
    }

    @Test
    public void testGetTotalNumberOfGoals() {
        Team team_A = new Team("Poland", 0);
        Team team_B = new Team("England", 2);

        Game game_A = new Game(team_A, team_B);

        assertEquals(2, game_A.getTotalNumberOfGoals());

        team_A.setScore(3);

        assertEquals(5, game_A.getTotalNumberOfGoals());
    }
}
