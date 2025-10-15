package game.OBJECT;

import game.ENTITY.*;
import game.MAIN.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnhancedObject extends MovableObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;

    public void draw(Graphics2D g2, GamePanel gp) {
        //int screenX = 300;
        //int screenY = 550;

        // thêm phần kiểm tra xem rơi ra khỏi màn hình chưa (y > SCREEN_HEIGHT)
        //.drawImage(image, screenX, screenY, 32, 22, null);
    }
}
