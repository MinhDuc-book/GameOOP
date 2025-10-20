package game.MAIN;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import game.BACKGROUND.BGManager;
import game.ENTITY.*;
import game.GAMESTATE.DoneState;
import game.GAMESTATE.EndState;
import game.GAMESTATE.GameState;
import game.GAMESTATE.PauseState;
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
    public ArrayList<BrickItem> items = new ArrayList<>();

    // 60FPS thì khi giữ 1 giây coi như autoclick esc 60 lần -> liên tục chuyển đổi pause và resume -> cần 1 biến để giữ trạng thái
    private boolean escPressedLastFrame = false;  // Để tránh toggle nhiều lần



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
        // Xử lý toggle pause/play khi nhấn ESC
        if (keyH.escPressed && !escPressedLastFrame) {
            if (gameState.getCurrentState() == GameState.State.PLAY) {
                gameState.setCurrentState(GameState.State.PAUSE);
                System.out.println("Game Paused");
            } else if (gameState.getCurrentState() == GameState.State.PAUSE) {
                gameState.setCurrentState(GameState.State.PLAY);
                System.out.println("Game Resumed");
            }
        }
        escPressedLastFrame = keyH.escPressed;

        switch (gameState.getCurrentState()) {
            case PLAY:
                player.update();

                Iterator<Ball> ballIterator = balls.iterator();
                while (ballIterator.hasNext()) {
                    Ball b = ballIterator.next();

                    if (!b.isActive && keyH.spacePressed) {
                        b.isActive = true;
                    }

                    b.update();

                    if (b.isRemoved) {
                        ballIterator.remove();
                    }
                }

                if (balls.isEmpty()) {
                    player.lifeCount--;

                    System.out.println("Player lost ball. Remaining life: " + player.lifeCount);

                    if (player.lifeCount <= 0) {
                        gameState.setCurrentState(GameState.State.END); //thua
                    } else {
                        Ball newBall = new Ball(this, player);
                        newBall.isActive = false;
                        balls.add(newBall);
                    }
                }

                Iterator<BrickItem> iterator = items.iterator();
                while (iterator.hasNext()) {
                    BrickItem item = iterator.next();
                    item.update();

                    if (item.itemActived && checkItemPlayerCollision(item)) {
                        activeItem(item);
                        iterator.remove();
                    }

                    if (!item.itemActived) {
                        iterator.remove();
                    }
                }


                break;

            case MENU:
                break;

            case END:
                break;

            case DONE:

                break;

            default:
                break;
        }
    }

    private boolean checkItemPlayerCollision(BrickItem item) {
        Rectangle itemRect = new Rectangle(item.x, item.y, 32, 22);
        Rectangle playerRect = new Rectangle(player.x, player.y, player.w, player.h);
        return itemRect.intersects(playerRect);
    }

    public void activeItem(BrickItem item) {
        switch (item.type) {
            case BrickItem.MORE_LIFE:
                player.lifeCount++;
                System.out.println("Nhận thêm mạng! Hiện có: " + player.lifeCount);
                break;
            case BrickItem.MORE_BALL:
                Ball newBall = new Ball(this, player);
                newBall.isActive = true;
                balls.add(newBall);
                System.out.println("Thêm bóng mới!");
                break;
            case BrickItem.PLAYER_SIZE:
                player.activateBigMode();
                break;
            case BrickItem.SLOW_BALL:
                System.out.println("Bóng chậm lại!");
                break;
            case BrickItem.BOMB:
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

        for (BrickItem item : items) {
            if (item.itemActived) {
                item.draw(g2);
            }
        }

        lifeCount.drawHeart(g2);

        for (Ball b : balls) {
            b.draw(g2);
        }

        if (gameState.getCurrentState() == GameState.State.PAUSE) {
            PauseState.draw(g2);
        } else if (gameState.getCurrentState() == GameState.State.END) {
            EndState.draw(g2);
        } else if (gameState.getCurrentState() == GameState.State.DONE) {
            DoneState.draw(g2);
        }

        g2.dispose();
    }
}