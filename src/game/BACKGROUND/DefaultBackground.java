package game.BACKGROUND;

import game.MAIN.*;
import game.SOUND.*;
import game.HIGHSCORE.HighscorePanel;
import game.HIGHSCORE.HighscoreManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class DefaultBackground extends JPanel implements MouseListener {
    private Image image;
    private JFrame  frame;
    private String soundpath = "/asset/sound/game-music-loop-7-145285.wav";
    private Sound sound = new Sound();;



    int startX = 230;
    int startY = 130;
    private MenuButton instructionBtn = new MenuButton(" Hướng dẫn", startX,  startY+2*70, 150, 50);
    private MenuButton startBtn = new MenuButton("BẮT ĐẦU", startX, startY+70, 150, 50);
    private MenuButton scoreBtn = new MenuButton(" Điểm", startX, startY, 150, 50);
    private MenuButton levelBtn = new MenuButton(" Cấp độ", startX, startY + 3 * 70, 150, 50);
    private boolean showInstructions = false;
    private boolean showScore = false;
    private boolean showLevel = false;

    private int selectedLevel = 1; // mặc định là level 1

    public DefaultBackground(String imagePath, JFrame window) {
        image = new ImageIcon(getClass().getClassLoader().getResource(imagePath)).getImage();
        setPreferredSize(new Dimension(GamePanel.getSreenWidth(), GamePanel.getSreenHeight()));
        addMouseListener(this);
        this.frame = window;
            sound.setSound(soundpath);
            sound.loop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        instructionBtn.draw(g);
        startBtn.draw(g);
        scoreBtn.draw(g);
        levelBtn.draw(g);

        if (showInstructions) {
            g.setColor(new Color(255, 255, 200, 220));
            g.fillRoundRect(150, 400, 300, 200, 20, 20);
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.setColor(Color.magenta);
            g.drawString("   HƯỚNG DẪN CHƠI ARKANOID", 180, 420 );
            g.setColor(Color.black);
            g.drawString("   - Di chuyển paddle bằng phím trái/phải", 150, 450);
            g.drawString("   - Đập bóng để phá gạch", 150, 475);
            g.drawString("   - Không để bóng rơi xuống đáy", 150, 500);
            g.drawString("   - Phá hết gạch để qua màn", 150, 525);
            g.drawString("   - Nhặt vật phẩm hỗ trợ khi có", 150, 550);
            g.drawString("  Chúc bạn chơi vui!", 200, 580);
        }

        if (showLevel) {
            g.setColor(new Color(255, 255, 200, 220));
            g.fillRoundRect(150, 400, 300, 250, 20, 20);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.setColor(Color.BLACK);
            g.drawString("Chọn cấp độ:", 240, 435);

            // DỄ
            g.setColor(selectedLevel == 1 ? Color.GREEN.darker() : Color.GREEN);
            g.fillRoundRect(250, 450, 80, 40, 10, 10);
            g.setColor(Color.BLACK);
            g.drawString("DỄ", 280, 475);

            // THƯỜNG
            g.setColor(selectedLevel == 2 ? Color.ORANGE.darker() : Color.ORANGE);
            g.fillRoundRect(240, 505, 100, 40, 10, 10);
            g.setColor(Color.BLACK);
            g.drawString("THƯỜNG", 255, 530);

            // KHÓ
            g.setColor(selectedLevel == 3 ? Color.RED.darker() : Color.RED);
            g.fillRoundRect(250, 560, 80, 40, 10, 10);
            g.setColor(Color.BLACK);
            g.drawString("KHÓ", 275, 585);
        }
    }

    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (showLevel) {
            // DỄ
            if (mouseX >= 250 && mouseX <= 330 && mouseY >= 450 && mouseY <= 490) {
                selectedLevel = 1;
                showLevel = false;
                repaint();
                System.out.println("Chọn cấp độ DỄ");
                return;
            }
            // THƯỜNG
            else if (mouseX >= 240 && mouseX <= 340 && mouseY >= 505 && mouseY <= 545) {
                selectedLevel = 2;
                showLevel = false;
                repaint();
                System.out.println("Chọn cấp độ THƯỜNG");
                return;
            }
            // KHÓ
            else if (mouseX >= 250 && mouseX <= 330 && mouseY >= 560 && mouseY <= 600) {
                selectedLevel = 3;
                showLevel = false;
                repaint();
                System.out.println("Chọn cấp độ KHÓ");
                return;
            }
        }

        if (instructionBtn.intoBound(mouseX, mouseY)) {
            if (instructionBtn.intoBound(mouseX, mouseY)) {
                showInstructions = !showInstructions; // đảo trạng thái
                showLevel = false;
                repaint();
            }
        }
        else if (levelBtn.intoBound(mouseX, mouseY)) {
            showLevel = !showLevel;
            showInstructions = false;
            repaint();
        }
        else if (startBtn.intoBound(mouseX, mouseY)) {
            sound.stop();
            startGameWithSelectedLevel();
        }
        else if (scoreBtn.intoBound(mouseX, mouseY)) {
            showScore = ! showScore;
            System.out.println("Hiển thị điểm!");

            HighscorePanel highscorePanel = new HighscorePanel(frame, HighscoreManager.lastScore, () -> {
                frame.setContentPane(this);
                frame.revalidate();
            });
            frame.setContentPane(highscorePanel);
            frame.revalidate();


        }

    }

    private void startGameWithSelectedLevel() {
        System.out.println("Bắt đầu trò chơi!");
        GamePanel gamePanel = new GamePanel(frame, selectedLevel);
        gamePanel.setLevel(selectedLevel); // cấp độ đã chọn trong menu

        frame.setContentPane(gamePanel);
        frame.revalidate();
        frame.repaint();
        frame.setSize(GamePanel.getSreenWidth(), GamePanel.getSreenHeight() + 50);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        KeyHandler keyH = new KeyHandler();
        gamePanel.addKeyListener(keyH);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        gamePanel.startGameThread();
    }



    // Các phương thức MouseListener còn lại
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {}

}


