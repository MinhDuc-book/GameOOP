package game.GAMESTATE;

import game.MAIN.GamePanel;
import java.awt.*;

public class PauseState {
    private GamePanel gp;

    public PauseState(GamePanel gp) {
        this.gp = gp;
    }

    public static void draw(Graphics2D g2) {
        // Vẽ overlay màu đen trong suốt
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT + 50);

        // Vẽ text "PAUSED"
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 48));
        String pauseText = "PAUSED";
        int textWidth = g2.getFontMetrics().stringWidth(pauseText);
        g2.drawString(pauseText, (GamePanel.SCREEN_WIDTH - textWidth) / 2, GamePanel.SCREEN_HEIGHT / 2);

        // Vẽ hướng dẫn
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        String resumeText = "Press ESC to Resume";
        int resumeWidth = g2.getFontMetrics().stringWidth(resumeText);
        g2.drawString(resumeText, (GamePanel.SCREEN_WIDTH - resumeWidth) / 2, GamePanel.SCREEN_HEIGHT / 2 + 50);
    }
}