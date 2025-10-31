package game.HIGHSCORE;

import java.util.*;

public class HighscoreManager {
    public static int lastScore = 0;

    public static void saveScore(int newScore) {
        lastScore = newScore;
        List<Integer> scores = FileHandler.loadScores();
        scores.add(newScore);
        Collections.sort(scores, Collections.reverseOrder());

        if (scores.size() > 5) {
            scores = scores.subList(0, 5);
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
