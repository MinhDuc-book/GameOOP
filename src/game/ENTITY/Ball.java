package game.ENTITY;

import game.GAMESTATE.GameState;
import game.MAIN.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Ball extends MovableObject {
    GamePanel gp;
    Player player;
    Brick bricks;
    GameState gameState = new GameState();

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
        this.gameState = gp.gameState;
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
                player.lifeCount--;
                if (player.lifeCount <= 0) {
                    gp.gameState.setCurrentState(GameState.State.END);
                } else {
                    reset();
                }
            }

            checkCollisionWithPlayer();
            checkCollisionWithBrick();

            if (isAllBricksDestroyed()) {
                gp.gameState.setCurrentState(GameState.State.DONE);
            }
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

    private void checkCollisionWithBrick() {
        Brick brick = gp.brick; // Truy cập brick trong GamePanel
        int[][] map = brick.brickMap;

        int brickWidth = 30;
        int brickHeight = 30;

        Rectangle ballRect = new Rectangle(x, y, diameter, diameter);

        // Duyệt toàn bộ map
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                int brickValue = map[row][col];
                if (brickValue > 0) {
                    int brickX = col * brickWidth;
                    int brickY = row * brickHeight;
                    Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);

                    if (ballRect.intersects(brickRect)) {

                        // Lấy trung tâm để xác định hướng va chạm
                        double ballCenterX = x + diameter / 2.0;
                        double ballCenterY = y + diameter / 2.0;

                        double brickCenterX = brickX + brickWidth / 2.0;
                        double brickCenterY = brickY + brickHeight / 2.0;

                        double dx = ballCenterX - brickCenterX;
                        double dy = ballCenterY - brickCenterY;

                        if (Math.abs(dx) > Math.abs(dy)) {
                            // Va theo trục X
                            speedX = -speedX;
                        } else {
                            // Va theo trục Y
                            speedY = -speedY;
                        }

                        // --- Giảm độ bền gạch ---
                        if (map[row][col] == 3) {
                            map[row][col] = 3;
                        } else if (map[row][col] == 4 || map[row][col] == 5) {
                            map[row][col] = 0;

                        } else {
                            map[row][col]--;
                        }



                        // Thoát khỏi vòng để tránh xử lý va nhiều viên cùng lúc
                        return;
                    }
                }
            }
        }
    }

    // check = true when have any brick with brick_id != 3
    private boolean isAllBricksDestroyed() {
        int[][] map = gp.brick.brickMap;
        boolean check = true;
        for (int[] row : map) {
            for (int value : row) {
                if (value != 3 && value != 0) {
                    check = false;
                }
            }
        }
        return check;
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
