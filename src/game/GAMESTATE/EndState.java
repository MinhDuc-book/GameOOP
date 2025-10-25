package game.GAMESTATE;

import game.MAIN.GamePanel;

import java.awt.*;

public class EndState {
    private GamePanel gp;

    public EndState(GamePanel gp) {
        this.gp = gp;
    }

    public static void draw(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 250));
        g2.fillRect(0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT + 50);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 48));
        String pauseText = "GAME OVER";
        int textWidth = g2.getFontMetrics().stringWidth(pauseText);
        g2.drawString(pauseText, (GamePanel.SCREEN_WIDTH - textWidth) / 2, GamePanel.SCREEN_HEIGHT / 2);

        // thêm hướng dẫn nhấn ESC để chơi lại
        String escText = "Press ESC to play again";
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        int escWidth = g2.getFontMetrics().stringWidth(escText);
        g2.drawString(escText, (GamePanel.SCREEN_WIDTH - escWidth) / 2, GamePanel.SCREEN_HEIGHT / 2 + 60);
    }
}