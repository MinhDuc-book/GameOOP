package game.OBJECT;

import java.awt.*;

public class Player {
    private int x, y, w, h;
    private Color color;
    public int speed = 5;

    public Player(int x, int y, int w, int h, Color color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }

    public void move(int dx, int dy, int SCREEN_WIDTH) {
        //dx, dy = speed
        x += dx;
        y += dy;
        if (x < 0) x = 0;
        if (x > SCREEN_WIDTH - w) x = SCREEN_WIDTH - w;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }
}
