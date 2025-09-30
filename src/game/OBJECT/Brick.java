import java.awt.*;

public class Brick extends GameObject {
    private boolean Destroyed = false;
    protected int Hitpoints;
    protected Color Type;

    /**
     * tạo brick.
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public Brick(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        if (color == Color.RED) {
            Hitpoints = 1;
        }
        if (color == Color.GREEN) {
            Hitpoints = 2;
        }
        if (color == Color.BLUE) {
            Hitpoints = 3;
        }
    }
    /**
     * lấy màu của brick.
     * @return mùa của loại brick.
     */
    public Color getColor() {
        return Type;
    }
    /**
     * phá vỡ brick.
     */
    public void destroyed() {
        this.Destroyed = true;
    }
    /**
     * kiểm tra xem brick bị vỡ hay chưa.
     * @return bị vỡ hay chưa.
     */
    public boolean isDestroyed() {
        return this.Destroyed;
    }

    public void draw(Graphics g) {
        if (!Destroyed) {
            super.render(g);
        }
    }

    public void hit() {
        if (Hitpoints > 0) {
            Hitpoints--;
            if (Hitpoints == 0) {
                destroyed();
            }
        }
    }
}
