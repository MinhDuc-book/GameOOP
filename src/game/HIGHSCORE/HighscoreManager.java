package game.HIGHSCORE;

import java.util.*;

public class HighscoreManager {
    private static final int MAX_ENTRIES = 5;

    public static void updateHighscores(String playerName, int newScore) {
        List<ScoreEntry> scores = FileHandler.loadScores();
        scores.add(new ScoreEntry(playerName, newScore));
        Collections.sort(scores);

        if (scores.size() > MAX_ENTRIES) {
            scores = scores.subList(0, MAX_ENTRIES);
        }

        FileHandler.saveScores(scores);
    }

    public static void displayHighscores() {
        List<ScoreEntry> scores = FileHandler.loadScores();
        Collections.sort(scores);
        System.out.println("Top 5 Highscores:");
        for (int i = 0; i < scores.size(); i++) {
            ScoreEntry entry = scores.get(i);
            System.out.printf("%d. %s - %d\n", i + 1, entry.getPlayerName(), entry.getScore());
        }
    }
}