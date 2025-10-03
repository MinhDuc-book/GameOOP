package game.ENTITY;

import game.MAIN.GamePanel;
import game.MAIN.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Player extends MovableObject {
    private int speed = 7;

    GamePanel gp;
    KeyHandler keyH;

    public BufferedImage playerImage;
    public String state;

    public Player(GamePanel gp, KeyHandler keyH) {
        this(250, 550, 100, 30, Color.GREEN);
        this.gp = gp;
        this.keyH = keyH;
        this.state = "normalMode";
        getPlayerImage();
        //setDefaultValue();
    }

    public Player(int x, int y, int w, int h, Color color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }

    //public void setDefaultValue() {
    //    x = 500;
    //    y = 600;
    //    w = 100;
    //    h = 30;
    //    color = Color.GREEN;
    //}

    public void setWidth(int w) {
        this.w = w;
    }

    public void setSpeed(int s) {
        this.speed = s;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void getPlayerImage() {
        try {
            playerImage = ImageIO.read(getClass().getResourceAsStream("/asset/player/Player.png"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setState(String state) {
        this.state = state;
    }

    public void update() {

        if (keyH.rightPressed == true) {
            x += getSpeed();
            if (x > 600 - w) {
                x = 600 - w;
            }
        } else if (keyH.leftPressed == true) {
            x -= getSpeed();
            if (x < 0) {
                x = 0;
            }
        }


    }

    public void draw(Graphics2D g2) {
        //g2.setColor(color);
        //g2.fillRect(x, y, w, h);

        BufferedImage image = null;
        switch (state) {
            case "normalMode":
                image = playerImage;
                break;
            case "bigMode":
                image = playerImage;
                this.w = 200;
                this.h = 50;
                break;
        }
        g2.drawImage(image, x, y, w, h, null);
    }


}