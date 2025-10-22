package game.ENTITY;

import game.MAIN.GamePanel;
import game.MAIN.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MovableObject {
    private int speed = 7;
    public int lifeCount = 1;

    GamePanel gp;
    KeyHandler keyH;

    public BufferedImage playerImage;
    public String state;

    private int normalWidth = 100;   // Kích thước bình thường
    private int bigWidth = 200;      // Kích thước lớn
    private boolean isBigMode = false;
    private long bigModeStartTime = 0;
    private final long BIG_MODE_DURATION = 5000; // 5 giây (5000 milliseconds)

    public Player(GamePanel gp, KeyHandler keyH) {
        this(250, 550, 100, 30, Color.GREEN);
        this.gp = gp;
        this.keyH = keyH;
        this.state = "normalMode";
        this.normalWidth = 100;  // Lưu kích thước ban đầu
        getPlayerImage();
    }

    public Player(int x, int y, int w, int h, Color color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }

    public void setWidth(int w) {
        this.w = w;
    }

    public void setSpeed(int s) {
        this.speed = s;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setLifeCount(int x) {
        this.lifeCount = x;
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

    public void activateBigMode() {
        isBigMode = true;
        bigModeStartTime = System.currentTimeMillis();
        this.w = bigWidth;
        System.out.println("Big Mode activated!");
    }

    public void deactivateBigMode() {
        isBigMode = false;
        this.w = normalWidth;
        System.out.println("Big Mode deactivated - back to normal");
    }

    // KIỂM TRA HẾT HẠN
    private void checkBigModeExpiration() {
        if (isBigMode) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - bigModeStartTime;

            if (elapsedTime >= BIG_MODE_DURATION) {
                deactivateBigMode();
            }
        }
    }

    public void update() {
        // Kiểm tra hết hạn hiệu ứng
        checkBigModeExpiration();

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
        BufferedImage image = null;

        // Luôn dùng cùng 1 ảnh, chỉ thay đổi kích thước
        image = playerImage;

        g2.drawImage(image, x, y, w, h, null);

        // Hiển thị thời gian còn lại (optional)
        if (isBigMode) {
            long remainingTime = BIG_MODE_DURATION - (System.currentTimeMillis() - bigModeStartTime);
            int seconds = (int) (remainingTime / 1000);

            g2.setColor(Color.YELLOW);
            g2.setFont(new Font("Arial", Font.BOLD, 12));
            g2.drawString("BIG: " + (seconds + 1) + "s", x + w/2 - 20, y - 5);
        }
    }
    public void reset() {
        this.x = GamePanel.SCREEN_WIDTH / 2 - this.normalWidth / 2;
        this.w = normalWidth;
        this.lifeCount = 3; // hoặc giá trị mặc định
        this.isBigMode = false;
        this.bigModeStartTime = 0;
    }
}