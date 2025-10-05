package game.ENTITY;

import game.MAIN.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Ball extends MovableObject {
    GamePanel gp;
    Player player;

    public int diameter = 20;
    public int speedX, speedY;
    private static BufferedImage image;
    public boolean isActive = false;

    static {
        try {
            image = ImageIO.read(Ball.class.getResourceAsStream("/asset/ball/normalBall.png"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Ball(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        reset();
    }

    public void reset() {
        isActive = false;
        x = player.x + player.w / 2 - diameter / 2;
        y = player.y - diameter;
        speedX = 4;
        speedY = -4;
    }

    public void update() {
        if (!isActive) {
            x = player.x + player.w / 2 - diameter / 2;
            y = player.y - diameter;
        } else {
            x += speedX;
            y += speedY;

            // Nảy tường trái/phải
            if (x <= 0 || x + diameter >= gp.SCREEN_WIDTH) {
                speedX = -speedX;
            }

            // Nảy trần
            if (y <= 0) {
                speedY = -speedY;
            }

            // Rơi xuống đáy -> reset
            if (y + diameter >= gp.SCREEN_HEIGHT) {
                reset();
                player.lifeCount--;
            }

            checkCollisionWithPlayer();
        }
    }

    private void checkCollisionWithPlayer() {
        Rectangle ballRect = new Rectangle(x, y, diameter, diameter);
        Rectangle playerRect = new Rectangle(player.x, player.y, player.w, player.h);

        if (ballRect.intersects(playerRect)) {
            // Đặt bóng lên trên player để tránh dính vào
            y = player.y - diameter;

            // Nếu đang đi xuống thì nảy lên
            if (speedY > 0) {
                speedY = -speedY;
            }
        }
    }


    public void draw(Graphics2D g2) {
        if (image != null)
            g2.drawImage(image, x, y, diameter, diameter, null);
        else {
            g2.setColor(Color.WHITE);
            g2.fillOval(x, y, diameter, diameter);
        }
    }
}
