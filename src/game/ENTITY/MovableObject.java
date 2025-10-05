package game.ENTITY;

public class MovableObject extends GameObject {
    protected int dx = 4;
    protected int dy = 4;

    public int getDx() { return this.dx; }
    public int getDy() { return this.dy; }

    public void setVelocity(int vx, int vy) {
        this.dx = vx;
        this.dy = vy;
    }
}