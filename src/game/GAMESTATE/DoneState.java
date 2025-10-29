package game.GAMESTATE;

import game.BACKGROUND.DefaultBackground;
import game.BACKGROUND.MenuButton;
import game.MAIN.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DoneState {
    private GamePanel gp;
    public DoneState(GamePanel gp) {
        this.gp = gp;
    }

    public static void draw(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 250));
        g2.fillRect(0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT + 50);

        // Vẽ text "PAUSED"
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 48));
        String pauseText = "WELL DONE";
        int textWidth = g2.getFontMetrics().stringWidth(pauseText);
        g2.drawString(pauseText, (GamePanel.SCREEN_WIDTH - textWidth) / 2, GamePanel.SCREEN_HEIGHT / 2);
        MenuButton.getReplayButton().draw(g2);
        MenuButton.getExitButton().draw(g2);
    }

    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (MenuButton.getReplayButton().intoBound(mouseX, mouseY)) {
            System.out.println("Replay clicked");
        }
        else if (MenuButton.getExitButton().intoBound(mouseX, mouseY)) {
            System.out.println("Exit clicked");
            System.exit(0); // Thoát game
        }
    }


}
