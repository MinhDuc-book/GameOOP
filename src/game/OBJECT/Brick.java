package game.OBJECT;

import java.awt.*;

public class Brick {
    private int x, y, w, h;
    private Color color;
    private boolean destroyed = false;
    public int countColid;

    public Brick (int x, int y, int w, int h, Color color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }

    public void draw(Graphics g) {
        if (destroyed) return;
        g.setColor(color);
        g.fillRect(x,y,w,h);
    }

    public Color getColor() {
        return this.color;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y,w, h);
    }

    public void setDestroyed() {
        destroyed = true;
    }
}
