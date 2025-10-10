package game.MAIN;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import game.BACKGROUND.BGManager;
import game.ENTITY.*;
import game.GAMESTATE.GameState;
import game.OBJECT.LifeCount;

public class GamePanel extends JPanel implements Runnable {
    public final int SCREEN_WIDTH = 600;
    public final int SCREEN_HEIGHT = 700;

    //FPS
    int FPS = 60;

    public BGManager bgManager = new BGManager(this);
    public Brick brick = new Brick(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread = new Thread(this);
    Player player = new Player(this, keyH);
    public ArrayList<Ball> balls = new ArrayList<>();
    public GameState gameState = new GameState();
    LifeCount lifeCount = new LifeCount(this, player);

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

        gameState.setCurrentState(GameState.State.PLAY);
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

        if (gameState.getCurrentState() == GameState.State.PLAY) {
            player.update();

            for (Ball b : balls) {
                if (b.isActive == false && keyH.spacePressed) {
                    b.isActive = true;

                }

                b.update();
            }
        } else if (gameState.getCurrentState() == GameState.State.MENU){
            // Menu, Level, else if
            System.out.println("END");
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //convert g to g2 for drawing 2D
        Graphics2D g2 = (Graphics2D)g;

        bgManager.draw(g2);

        player.draw(g2);

        brick.draw(g2);

        lifeCount.drawHeart(g2);

        for (Ball b : balls) {
            b.draw(g2);
        }

        g2.dispose();
    }
}