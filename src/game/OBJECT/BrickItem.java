package game.OBJECT;

import game.ENTITY.Ball;
import game.ENTITY.GameObject;
import game.ENTITY.MovableObject;
import game.ENTITY.Player;
import game.GAMESTATE.GameState;
import game.MAIN.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class BrickItem extends GameObject {
    GamePanel gp;
    Ball ball;
    ArrayList<BrickItem> items;

    int speedY = 2;

    public int type;

    public boolean objectActived = true;
    public boolean objectCollision = false;

    public static final int MORE_LIFE = 4;
    public static final int PLAYER_SIZE = 5;
    public static final int MORE_BALL = 6;
    public static final int SLOW_BALL = 7;
    public static final int BOMB = 8;

    private static BufferedImage moreBall;
    private static BufferedImage playerSize;
    private static BufferedImage moreLife;
    private static BufferedImage slowBall;
    private static BufferedImage bomb;

    public boolean itemActived;
    public boolean itemCollision;

    static {
        try {
            moreLife = ImageIO.read(BrickItem.class.getResourceAsStream("/asset/BrickItem/ItemHeart.png"));
            playerSize = ImageIO.read(BrickItem.class.getResourceAsStream("/asset/BrickItem/ItemSize.png"));
            moreBall = ImageIO.read(BrickItem.class.getResourceAsStream("/asset/BrickItem/Item3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BrickItem() {

    }

    public BrickItem(GamePanel gp, int itemX, int itemY, int type) {
        this.gp = gp;
        this.x = itemX;
        this.y = itemY;
        this.type = type;
        this.itemActived = true;
        this.objectCollision = false;
    }

    public void update() {
        if (itemActived) {
            y += speedY;
            if (y > gp.SCREEN_HEIGHT) {
                itemActived = false;
            }
        }
    }

    public static void setItemCollision(BrickItem item, boolean objectCollision) {
        item.objectCollision = objectCollision;
    }

    public void draw(Graphics2D g2) {
        if (!objectActived) return;

        BufferedImage img = null;
        switch(type) {
            case MORE_BALL: img = moreBall; break;
            case PLAYER_SIZE:img = playerSize; break;
            case MORE_LIFE:img = moreLife; break;
            case SLOW_BALL: img = slowBall; break;
            case BOMB: img = bomb; break;
        }

        if (img != null) {
            g2.drawImage(img, x, y, 32, 22, null);
        }

    }
}
