package game.ENTITY;

import game.MAIN.GamePanel;

public class Ball extends MovableObject {
    GamePanel gp;
    Player player;
    Brick[] bricks;

    public int diameter = 20;
    int speedX, speedY;

    public boolean isActive;

    public void bounceOff(GameObject other) {

    }

    public void checkCollision(GameObject other) {

    }
}