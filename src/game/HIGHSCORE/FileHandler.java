package game.HIGHSCORE;

import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_NAME = "highscores.txt";

    public static List<ScoreEntry> loadScores() {
        List<ScoreEntry> scores = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return scores;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(ScoreEntry.fromString(line.trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

    public static void saveScores(List<ScoreEntry> scores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (ScoreEntry entry : scores) {
                writer.write(entry.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}