package game.ENTITY;

public class MovableObject extends GameObject {
    protected int dx, dy;

    public int getDx() { return this.dx; }
    public int getDy() { return this.dy; }

    // di chuyển theo dx/dy hiện tại
    public void move() {
        this.x += dx;
        this.y += dy;
    }

    public void setVelocity(int vx, int vy) {
        this.dx = vx;
        this.dy = vy;
    }
}