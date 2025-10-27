package game.HIGHSCORE;

import java.util.*;

public class HighscoreManager {
    private static final int MAX_ENTRIES = 5;

    public static void saveScore(int newScore) {
        List<Integer> scores = FileHandler.loadScores();
        scores.add(newScore);
        Collections.sort(scores, Collections.reverseOrder());

        if (scores.size() > MAX_ENTRIES) {
            scores = scores.subList(0, MAX_ENTRIES);
        }

        FileHandler.saveScores(scores);
    }

    public static void displayHighscores() {
        List<Integer> scores = FileHandler.loadScores();
        Collections.sort(scores, Collections.reverseOrder());
        System.out.println("Top 5 Highscores:");
        for (int i = 0; i < scores.size(); i++) {
            System.out.printf("%d. %d points\n", i + 1, scores.get(i));
        }
    }
}
