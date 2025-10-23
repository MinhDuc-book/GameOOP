package game.GAMESTATE;

import game.MAIN.GamePanel;

import java.awt.*;

public class DoneState {
    private GamePanel gp;

    public DoneState(GamePanel gp) {
        this.gp = gp;
    }

    public static void draw(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 250));
        g2.fillRect(0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT + 20);

        // Vẽ text "PAUSED"
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 48));
        String pauseText = "WELL DONE";
        int textWidth = g2.getFontMetrics().stringWidth(pauseText);
        g2.drawString(pauseText, (GamePanel.SCREEN_WIDTH - textWidth) / 2, GamePanel.SCREEN_HEIGHT / 2);
    }
}
