package game.OBJECT;

import java.awt.*;

public class GameObject {
    public int x, y, w, h;
    public Color color;

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }

    // thêm helper chung để lấy bounds
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }
}
