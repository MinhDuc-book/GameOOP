package game.GAMESTATE;

import game.MAIN.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuState {
    static GamePanel gp;
    static BufferedImage[] buttonIcons;
    static BufferedImage[] selectImage;
    static BufferedImage image;
    static BufferedImage gameName;

    private static int selectedButton = 0;
    private static Rectangle[] buttonBounds;
    private static int totalButtons = 4;

    public MenuState(GamePanel gp) {
        this.gp = gp;

    }

    public void loadImage() {

    }
}
