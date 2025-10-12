package game.BACKGROUND;

import game.MAIN.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BGManager {
    GamePanel gp;
    Background[] bg;

    public BGManager(GamePanel gp) {
        this.gp = gp;
        bg = new Background[10];
        getBGImage();
    }

    public void getBGImage() {
        try {
            bg[0] = new Background();
            bg[0].image = ImageIO.read(getClass().getResourceAsStream("/asset/background/Background.png"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {

        for (int i = 0; i < gp.SCREEN_WIDTH; i = i + 30) {
            for (int j = 0; j < gp.SCREEN_HEIGHT; j = j + 30) {
                g2.drawImage(bg[0].image, i, j, 30, 30, null);
            }
        }

    }
}
