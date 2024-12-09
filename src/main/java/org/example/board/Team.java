package org.example.board;

public class Team {

    private final String name;
    private int score;

    public Team(String name) {
        this(name, 0);
    }

    public Team(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name + " " + score;
    }
}