package game.ENTITY;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {
    public int x, y, w, h;
    public Color color;

    // trả về vùng bao quanh đối tượng
    public Rectangle setBounds() {
        return new Rectangle(x,y,w,h);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }
}