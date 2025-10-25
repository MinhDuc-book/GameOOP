package game.GAMESTATE;

import game.BACKGROUND.MenuButton;
import game.MAIN.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;

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
    }

    public void mousePressed(MouseEvent e) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            if (MenuButton.getReplayButton().intoBound(mouseX, mouseY)) {
                System.out.println("Replay clicked");
                gp.restartGame();
            }
    }
}
