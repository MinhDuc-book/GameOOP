package game.MAIN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.MAIN.*;
import game.OBJECT.*;

public class GamePanel extends JPanel {
        final int SCREEN_WIDTH = 600;
        final int SCREEN_HEIGHT = 700;
        private Player player;

        private boolean leftPressed = false;
        private boolean rightPressed = false;
        Timer gameTimer;
        final int MOVE_STEP = 6;



    public GamePanel() {
            this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
            this.setBackground(Color.BLACK);
            this.setDoubleBuffered(true); // tăng hiệu suất vẽ

            player = new Player(100, SCREEN_HEIGHT-200, 100,30, Color.RED);


            this.setFocusable(true);
            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT -> player.move(-player.speed, 0, SCREEN_WIDTH);
                        case KeyEvent.VK_RIGHT -> player.move(player.speed, 0, SCREEN_WIDTH);
                        case KeyEvent.VK_UP -> player.move(0, -player.speed, SCREEN_WIDTH);
                        case KeyEvent.VK_DOWN -> player.move(0, player.speed, SCREEN_WIDTH);
                    }
                }
            });

            gameTimer = new Timer(16, ev -> {
                int dx = 0;
                if (leftPressed) dx -= MOVE_STEP;
                if (rightPressed) dx += MOVE_STEP;
                if (dx != 0) player.move(dx, 0, SCREEN_WIDTH);
                repaint();
            });
            gameTimer.start();

            requestFocusInWindow();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            player.draw(g);
        }

}
