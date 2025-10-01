package game.OBJECT;

public class MovableObject extends GameObject {
    protected int dx, dy;

    public void move(int dx, int dy, int SCREEN_WIDTH) {
        x += dx;
        y += dy;
        if (x < 0) x = 0;
        if (x > SCREEN_WIDTH - w) x = SCREEN_WIDTH - w;
    }
}
