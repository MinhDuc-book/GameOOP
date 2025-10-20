package game.OBJECT;

import game.ENTITY.*;
import game.MAIN.GamePanel;

import java.awt.image.BufferedImage;

public class BrickItem extends MovableObject {
    GamePanel gp;
    Player player;
    BrickItem[] brickItems;

    public static BufferedImage image0;
    public static BufferedImage image1;

    public static final int MORE_LIFE_COUNT = 0;
    public static final int DOUBLE_PLAYER_SIZE = 1;

    public BrickItem(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
    }
}
