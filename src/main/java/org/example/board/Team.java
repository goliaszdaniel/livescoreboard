package org.example.board;

public class Team {

    private final String name;
    private int score;

    public Team(String name) {
        this(name, 0);
    }

    public Team(String name, int score) {
        if(score < 0) {
            throw new IllegalArgumentException("Score cannot be lower than zero");
        }
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
        if(score < 0) {
            throw new IllegalArgumentException("Score cannot be lower than zero");
        }
        this.score = score;
    }

    @Override
    public String toString() {
        return name + " " + score;
    }
}