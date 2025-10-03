package game.MAIN;

import javax.swing.*;
import java.awt.*;

import game.BACKGROUND.BGManager;
import game.ENTITY.*;

public class GamePanel extends JPanel implements Runnable{
    public final int SCREEN_WIDTH = 600;
    public final int SCREEN_HEIGHT = 700;

    //FPS
    int FPS = 60;

    BGManager bgManager = new BGManager(this);
    Brick brick = new Brick(this);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            double drawInterval = 1000000000/FPS; // about 0.166667 second
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
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //convert g to g2 for drawing 2D
        Graphics2D g2 = (Graphics2D)g;

        bgManager.draw(g2);

        player.draw(g2);

        brick.draw(g2);

        g2.dispose();
    }
}