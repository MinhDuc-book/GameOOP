package game.HIGHSCORE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class HighscorePanel extends JPanel {
    public HighscorePanel(JFrame window, Runnable onExit) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Hiển thị điểm cao
        JTextArea scoreArea = new JTextArea();
        scoreArea.setEditable(false);
        scoreArea.setFont(new Font("Monospaced", Font.BOLD, 18));
        scoreArea.setForeground(Color.WHITE);
        scoreArea.setBackground(Color.BLACK);

        List<ScoreEntry> scores = FileHandler.loadScores();
        StringBuilder sb = new StringBuilder("🏆 Top 5 Highscores:\n\n");
        for (int i = 0; i < scores.size(); i++) {
            ScoreEntry entry = scores.get(i);
            sb.append(String.format("%d. %s - %d\n", i + 1, entry.getPlayerName(), entry.getScore()));
        }
        scoreArea.setText(sb.toString());

        // Nút Exit
        JButton exitButton = new JButton("Exit");
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

