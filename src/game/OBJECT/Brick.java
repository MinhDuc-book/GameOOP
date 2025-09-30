import java.awt.*;

public class Brick extends GameObject {
    private boolean destroyed = false;
    protected int hitpoints;
    protected Color type;

    public Brick(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        this.type = color;

        if (color == Color.RED) hitpoints = 1;
        else if (color == Color.GREEN) hitpoints = 2;
        else if (color == Color.BLUE) hitpoints = 3;
        else hitpoints = 1;
    }

    public Color getColor() {
        return type;
    }

    public void destroy() {
        this.destroyed = true;
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public void render(Graphics g) {
        if (!destroyed) {
            g.setColor(type);
            g.fillRect(getX(),
                    getY(),
                    getWidth(),
                    getHeight());
        }
    }
    public void update() {
        if (hitpoints > 0) {
            hitpoints--;
            if (hitpoints == 0) {
                destroy();
            }
        }
    }
}