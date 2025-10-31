package game.HIGHSCORE;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HighscorePanel extends JPanel {
    public HighscorePanel(JFrame window, int lastScore, Runnable onExit) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JTextArea scoreArea = new JTextArea();
        scoreArea.setEditable(false);
        scoreArea.setFont(new Font("Monospaced", Font.BOLD, 18));
        scoreArea.setForeground(Color.WHITE);
        scoreArea.setBackground(Color.BLACK);
        scoreArea.setMargin(new Insets(20, 20, 20, 20));

        List<Integer> scores = FileHandler.loadScores();
        StringBuilder sb = new StringBuilder();
        sb.append("Your last score: ").append(lastScore).append(" points\n\n");
        sb.append("Top 5 Highscores:\n\n");
        scores.sort((a, b) -> b - a);
        int max = Math.min(scores.size(), 5);
        for (int i = 0; i < max; i++) {
            int score = scores.get(i);
            sb.append(String.format("%d. %d points\n", i + 1, score));
        }

        scoreArea.setText(sb.toString());

        JButton exitButton = new JButton("Return Menu");
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> onExit.run());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(Color.BLACK);
        topPanel.add(exitButton);

        add(topPanel, BorderLayout.NORTH);
        add(scoreArea, BorderLayout.CENTER);
    }
}
