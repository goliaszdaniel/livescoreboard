package org.example.board;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class LiveScoreBoard implements Board {

    private final List<Game> listOfGames = new ArrayList<>();

    @Override
    public void start(Game game) {

        if(game.getHomeTeam().getScore() != 0 || game.getAwayTeam().getScore() != 0) {
            throw new IllegalArgumentException("Starting score has to be 0-0");
        }
        for(Game existingGame : listOfGames) {
            if(existingGame.containsAnyTeamNameAs(game)) {
                throw new IllegalArgumentException("Cannot start a game for the team that is already playing");
            }
        }

        listOfGames.add(game);
        game.initStartTime();
    }

    @Override
    public void finish(Game game) {

        for(Game elem : listOfGames) {
            if(elem.containsTheSameTeamNamesAs(game)) {
                listOfGames.remove(elem);
                return;
            }
        }
        throw new NoSuchElementException("There is no such game on the board");
    }

    @Override
    public void update(Game game) {

        for(Game elem : listOfGames) {
            if(elem.containsTheSameTeamNamesAs(game)) {
                elem.getHomeTeam().setScore(game.getHomeTeam().getScore());
                elem.getAwayTeam().setScore(game.getAwayTeam().getScore());
                return;
            }
        }
        throw new NoSuchElementException("There is no such game on the board");
    }

    @Override
    public String getSummaryByTotalScore() {

        List<Game> copy = new ArrayList<>(listOfGames);

        copy.sort(Comparator.comparing(Game::getStartTime).reversed());
        copy.sort(Comparator.comparing(Game::getTotalNumberOfGoals).reversed());

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<copy.size(); i++) {
            Team homeTeam = copy.get(i).getHomeTeam();
            Team awayTeam = copy.get(i).getAwayTeam();
            sb.append( String.format("%s. %s %s - %s %s\n", i+1, homeTeam.getName(), homeTeam.getScore(), awayTeam.getName(), awayTeam.getScore()));
        }
        return sb.toString().trim();
    }

    List<Game> getListOfGames() {
        return listOfGames;
    }
}
