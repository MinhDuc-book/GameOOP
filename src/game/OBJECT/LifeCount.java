package game.OBJECT;

import game.ENTITY.Player;
import game.MAIN.GamePanel;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;


public class LifeCount {

    public static BufferedImage image;
    public GamePanel gp;
    public Player player;

    private int w = 50;
    private int h = 30;
    private int x;
    private int y;

    public LifeCount(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        x = 20;
        y = 650;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/asset/lifeCount/LifeCount.png"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void drawHeart(Graphics2D g2) {
        int pos = x;
        for (int i = 0; i < player.lifeCount; ++i) {
            g2.drawImage(image, pos, y, w, h, null);
            pos += 50;
        }
    }
}
