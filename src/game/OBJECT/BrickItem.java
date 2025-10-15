package game.OBJECT;

import game.ENTITY.Ball;
import game.ENTITY.Player;
import game.GAMESTATE.GameState;
import game.MAIN.GamePanel;

import java.util.ArrayList;

public class BrickItem {
    GamePanel gp;
    Ball ball;
    ArrayList<BrickItem> items;

    int speedY = 2;

    public boolean objectActived = true;
    public boolean objectCollision = false;

    public static final int MORE_BALL = 0;
    public static final int PLAYER_SIZE = 1;
    public static final int MORE_LIFE = 2;
    public static final int SLOW_BALL = 3;
    public static final int BOMB = 4;



}
