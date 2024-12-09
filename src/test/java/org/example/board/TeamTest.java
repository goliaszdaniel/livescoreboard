package org.example.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TeamTest {

    @Test
    public void testTeam_createWithDefaultScore() {
        Team team_A = new Team("Poland");

        assertEquals("Poland", team_A.getName());
        assertEquals(0, team_A.getScore());
    }

    @Test
    public void testTeam_createWithPassingCorrectScore() {
        Team team_A = new Team("Poland", 0);
        Team team_B = new Team("England", 3);

        assertEquals("Poland", team_A.getName());
        assertEquals(0, team_A.getScore());

        assertEquals("England", team_B.getName());
        assertEquals(3, team_B.getScore());
    }

    @Test
    public void testTeam_createWithPassingIncorrectScore() {
        assertThrows(IllegalArgumentException.class, () -> new Team("Poland", -1));
    }

    @Test
    public void testTeam_setScore_correct() {
        Team team_A = new Team("Poland");
        Team team_B = new Team("England");

        team_A.setScore(0);
        assertEquals("Poland", team_A.getName());
        assertEquals(0, team_A.getScore());

        team_B.setScore(3);
        assertEquals("England", team_B.getName());
        assertEquals(3, team_B.getScore());
    }

    @Test
    public void testTeam_setScore_incorrect() {
        Team team_A = new Team("Poland");

        assertThrows(IllegalArgumentException.class, () -> team_A.setScore(-1));
    }
}