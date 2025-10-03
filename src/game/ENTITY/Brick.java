package game.ENTITY;

import game.MAIN.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Brick extends GameObject {

    // red -> 1 time -> 0
    // yellow -> 2 times -> 1

    GamePanel gp;
    Brick brick[];

    public BufferedImage brickImage;
    int brickMap[][];
    int countLife;

    public Brick(GamePanel gp) {
        this.gp = gp;
        brickMap = new int[10][20];
        brick = new Brick[3];
        getBrickImage();
        loadBrickMap();
    }

    public Brick() {

    }

    public void getBrickImage() {
        try {
            //

            // red
            brick[1] = new Brick();
            brick[1].brickImage = ImageIO.read(getClass().getResourceAsStream("/asset/brick/Brick1.png"));

            // yellow
            brick[2] = new Brick();
            brick[2].brickImage = ImageIO.read(getClass().getResourceAsStream("/asset/brick/Brick2.png"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadBrickMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/asset/brickMap/map1.txt");
            //if (is == null) {
            //    System.out.println(" Không tìm thấy map1.txt trong resource path!");
            //} else {
                //System.out.println(" Đã load được map1.txt");
            //}
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int i = 0; i < 10; ++i) {
                String line = br.readLine();

                for (int j = 0; j < 20; ++j) {
                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[j]); // string -> int
                    brickMap[i][j] = num;
                }
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        //g2.drawImage(brick[0].brickImage, 0, 0, 30, 30, null);
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 20; ++j) {
                if (brickMap[i][j] == 1) {
                    g2.drawImage(brick[1].brickImage, j*30, i*30, 30, 30, null);
                } else if (brickMap[i][j] == 2) {
                    g2.drawImage(brick[2].brickImage, j*30, i*30, 30, 30, null);
                }
            }
        }

    }
}
