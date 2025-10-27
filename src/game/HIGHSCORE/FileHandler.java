package game.HIGHSCORE;

import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_PATH = "highscores.txt";

    public static List<Integer> loadScores() {
        List<Integer> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            System.out.println("No highscore file found, starting fresh.");
        }
        return scores;
    }

    public static void saveScores(List<Integer> scores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int score : scores) {
                writer.write(score + "\n");
            }
        } catch (IOException e) {
            System.out.println("Failed to save highscores.");
        }
    }
}
