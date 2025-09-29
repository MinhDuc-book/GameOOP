package game.MAIN;

import javax.swing.*;
import java.util.Iterator;
import java.util.LinkedList;
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

        private LinkedList<Brick> bricks;
        int brick_rows = 5;
        int brick_cols = 8;
        int brick_gap = 4;
        int brick_h = 24;
        int brick_w;


    public GamePanel() {
            this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
            this.setBackground(Color.BLACK);
            this.setDoubleBuffered(true); // tăng hiệu suất vẽ

            player = new Player(100, SCREEN_HEIGHT-200, 100,30, Color.GREEN);

            // vẽ hàng loạt các ô gạch
            brick_w = (SCREEN_WIDTH - (brick_cols + 1) * brick_gap) / brick_cols;
            bricks = new LinkedList<>();
            for (int r = 0; r < brick_rows; r++) {
                for (int c = 0; c < brick_cols; c++) {
                    int x = brick_gap + c * (brick_w + brick_gap);
                    int y = 40 + r * (brick_h + brick_gap);
                    Color col;
                    if (r % 3 == 0) col = Color.RED;
                    else if (r % 3 == 1) col = Color.ORANGE;
                    else col = Color.YELLOW;
                    bricks.add(new Brick(x, y, brick_w, brick_h, col));
                }
            }

            for (Brick b : bricks) {
                if (b.getColor() == Color.RED) b.countColid = 1;
                else if (b.getColor() == Color.ORANGE) b.countColid = 2;
                // cái còn lại màu vàng thì tặng cho 1 hiệu ứng nào đó (để sau)
            }

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

            gameTimer = new Timer(12, ev -> {
                int dx = 0;
                if (leftPressed) dx -= MOVE_STEP;
                if (rightPressed) dx += MOVE_STEP;
                if (dx != 0) player.move(dx, 0, SCREEN_WIDTH);
                repaint();
            });
            gameTimer.start();

            requestFocusInWindow();
        }

        public boolean hitBrick(Rectangle rect) {
            Iterator<Brick> it = bricks.iterator();
            while (it.hasNext()) {
                Brick b = it.next();
                if (b.getBounds().intersects(rect)) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            player.draw(g);

            for (Brick b : bricks) {
                b.draw(g);
            }
        }

}
