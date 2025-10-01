package game.OBJECT;

import java.awt.*;
import game.OBJECT.*;

public class Player extends MovableObject  {
    public int speed = 7;
    public int lifeCount = 3;

    public Player(int x, int y, int w, int h, Color color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }

    public void applyPower() {

    }

}
