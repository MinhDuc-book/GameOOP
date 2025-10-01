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
        private Ball ball;

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
            this.setDoubleBuffered(true);

            player = new Player(100, SCREEN_HEIGHT-200, 100,30, Color.GREEN);

            // tạo ball, đặt lên trên player lúc bắt đầu (chưa bắn)
            if (player.lifeCount > 0) {
                ball = new Ball();
                ball.setBounds(player.x + player.w/2 - 6, player.y - 12, 12, 12);
                ball.isActive = false;
            } else {
                System.out.print("GAME OVER");
            }


            // vẽ hàng gạch
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

                    // row 0 => unbreakable StrongBrick
                    if (r == 0) {
                        bricks.add(new StrongBrick(x, y, brick_w, brick_h, col));
                    } else {
                        bricks.add(new Brick(x, y, brick_w, brick_h, col));
                    }
                }
            }

            // chỉ set countColid cho các viên gạch có thể phá (không phải StrongBrick)
            for (Brick b : bricks) {
                if (b instanceof StrongBrick) continue;
                if (b.getColor() == Color.RED) b.countColid = 1;
                else if (b.getColor() == Color.ORANGE) b.countColid = 2;
            }

            this.setFocusable(true);
            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT -> leftPressed = true;
                        case KeyEvent.VK_RIGHT -> rightPressed = true;
                        case KeyEvent.VK_SPACE -> {
                            if (!ball.isActive) {
                                // launch lên trên với góc tùy ý
                                int sx = player.x + player.w/2 - ball.w/2;
                                int sy = player.y - ball.h;
                                ball.launch(sx, sy, -75, 8);
                            }
                        }
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT -> leftPressed = false;
                        case KeyEvent.VK_RIGHT -> rightPressed = false;
                    }
                }
            });

            gameTimer = new Timer(12, ev -> {
                int dx = 0;
                if (leftPressed) dx -= MOVE_STEP;
                if (rightPressed) dx += MOVE_STEP;
                if (dx != 0) player.move(dx, 0, SCREEN_WIDTH);

                // nếu ball chưa active thì giữ nó trên thanh player
                if (!ball.isActive) {
                    ball.x = player.x + player.w/2 - ball.w/2;
                    ball.y = player.y - ball.h;
                } else {
                    // update vị trí ball
                    ball.update(new Rectangle(0,0,SCREEN_WIDTH,SCREEN_HEIGHT));
                    // va chạm với player
                    if (ball.getBounds().intersects(player.getBounds())) {
                        ball.bounceOff(player);
                        // tăng điều chỉnh hướng theo vị trí chạm trên paddle
                        int paddleCenter = player.x + player.w/2;
                        int diff = ball.x - paddleCenter;
                        // điều chỉnh vx nhẹ
                        ball.vx += diff / 6;
                    }

                    // va chạm với bricks
                    Iterator<Brick> it = bricks.iterator();
                    while (it.hasNext()) {
                        Brick b = it.next();
                        if (ball.getBounds().intersects(b.getBounds())) {
                            ball.bounceOff(b);
                            if (b.countColid <= 0) it.remove();
                            break; // 1 va chạm mỗi tick là đủ
                        }
                    }

                    // kiểm tra còn cục gạch "có thể phá" hay không
                    boolean hasDestroyable = false;
                    for (Brick b : bricks) {
                        if (!(b instanceof StrongBrick)) {
                            hasDestroyable = true;
                            break;
                        }
                    }
                    if (!hasDestroyable) {
                        gameTimer.stop();
                        System.out.println("Well done");
                    }
                }

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

            // vẽ ball
            ball.draw(g);
        }

}
