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
        g2.fillRect(0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT + 50);

        // Vẽ text "WELL DONE"
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 48));
        String doneText = "WELL DONE";
        int textWidth = g2.getFontMetrics().stringWidth(doneText);
        g2.drawString(doneText, (GamePanel.SCREEN_WIDTH - textWidth) / 2, GamePanel.SCREEN_HEIGHT / 2);

        // Thêm hướng dẫn nhấn ESC để quay lại menu
        String escText = "Press ESC to return to menu";
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        int escWidth = g2.getFontMetrics().stringWidth(escText);
        g2.drawString(escText, (GamePanel.SCREEN_WIDTH - escWidth) / 2, GamePanel.SCREEN_HEIGHT / 2 + 60);
    }
}
