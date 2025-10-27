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
        bg = new Background[4];
        getBGImage();
    }

    public void getBGImage() {
        try {
            bg[0] = new Background();
            bg[0].image = ImageIO.read(getClass().getResourceAsStream("/asset/background/background.jpg"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(bg[0].image, 0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT, null);

    }
}
