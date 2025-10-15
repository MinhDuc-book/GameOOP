package game.MAIN;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import game.BACKGROUND.BGManager;
import game.ENTITY.*;
import game.GAMESTATE.GameState;
import game.OBJECT.BrickItem;
import game.OBJECT.EnhancedObject;
import game.OBJECT.LifeCount;

public class GamePanel extends JPanel implements Runnable {
    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 700;

    int FPS = 60;

    public static int getSreenWidth() {
        return SCREEN_WIDTH;
    }
    public static int getSreenHeight () {
        return SCREEN_HEIGHT;
    }

    public BGManager bgManager = new BGManager(this);
    public Brick brick = new Brick(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread = new Thread(this);
    Player player = new Player(this, keyH);
    public ArrayList<Ball> balls = new ArrayList<>();
    public GameState gameState = new GameState();
    public AssetSetter aSetter = new AssetSetter(this);
    LifeCount lifeCount = new LifeCount(this, player);
    public ArrayList<BrickItem> items = new ArrayList<>();  // ✅ PUBLIC để Ball có thể thêm vào

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        setupGame();
    }

    public void startGameThread() {
        gameThread.start();
    }

    public void setupGame() {
        Ball initBall = new Ball(this, player);
        balls.add(initBall);

        aSetter.setObject();

        gameState.setCurrentState(GameState.State.PLAY);
    }

    @Override
    public void run() {

        while (gameThread != null) {

            double drawInterval = 1000000000/FPS;
            double nextDrawTime = System.nanoTime() + drawInterval;

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        switch (gameState.getCurrentState()) {
            case PLAY:
                player.update();

                for (Ball b : balls) {
                    if (b.isActive == false && keyH.spacePressed) {
                        b.isActive = true;
                    }
                    b.update();
                }

                // Update items và check collision
                Iterator<BrickItem> iterator = items.iterator();
                while (iterator.hasNext()) {
                    BrickItem item = iterator.next();
                    item.update();

                    // Kiểm tra va chạm với player
                    if (item.itemActived && checkItemPlayerCollision(item)) {
                        activeItem(item);
                        iterator.remove();  // Xóa item sau khi nhặt
                    }

                    // Xóa item nếu rơi ra ngoài màn hình
                    if (!item.itemActived) {
                        iterator.remove();
                    }
                }

                break;

            case MENU:
                System.out.println("TURN ON MENU");
                break;

            case END:
                System.out.println("END");
                break;

            case DONE:
                System.out.println("DONE LEVEL X");
                break;

            default:
                break;
        }
    }

    // Kiểm tra va chạm item với player
    private boolean checkItemPlayerCollision(BrickItem item) {
        Rectangle itemRect = new Rectangle(item.x, item.y, 32, 22);
        Rectangle playerRect = new Rectangle(player.x, player.y, player.w, player.h);
        return itemRect.intersects(playerRect);
    }

    // Kích hoạt hiệu ứng item
    public void activeItem(BrickItem item) {
        switch (item.type) {
            case BrickItem.MORE_LIFE:
                player.lifeCount++;
                System.out.println("Nhận thêm mạng! Hiện có: " + player.lifeCount);
                break;
            case BrickItem.MORE_BALL:
                // TODO: Thêm bóng mới
                Ball newBall = new Ball(this, player);
                newBall.isActive = true;
                balls.add(newBall);
                System.out.println("Thêm bóng mới!");
                break;
            case BrickItem.PLAYER_SIZE:
                player.activateBigMode();
                break;
            case BrickItem.SLOW_BALL:
                for (Ball b : balls) {
                    b.speedX = b.speedX > 0 ? 2 : -2;
                    b.speedY = b.speedY > 0 ? 2 : -2;
                }
                System.out.println("Bóng chậm lại!");
                break;
            case BrickItem.BOMB:
                // TODO: Phá nhiều gạch
                System.out.println("Boom!");
                break;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        bgManager.draw(g2);

        player.draw(g2);

        brick.draw(g2);

        // Vẽ items
        for (BrickItem item : items) {
            if (item.itemActived) {
                item.draw(g2);
            }
        }

        lifeCount.drawHeart(g2);

        for (Ball b : balls) {
            b.draw(g2);
        }

        g2.dispose();
    }
}