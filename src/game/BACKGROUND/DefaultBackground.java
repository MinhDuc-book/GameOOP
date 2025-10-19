package game.BACKGROUND;

import game.MAIN.*;
import game.SOUND.*;

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
    private boolean showInstructions = false;
    private boolean showScore = false;
    public DefaultBackground(String imagePath, JFrame window) {
        image = new ImageIcon(getClass().getClassLoader().getResource(imagePath)).getImage();
        setPreferredSize(new Dimension(GamePanel.getSreenWidth(), GamePanel.getSreenHeight()));
        addMouseListener(this);
        this.frame = window;
            sound.setSound(soundpath);
            sound.loop();
    }

    public void getSoundActive() {
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        instructionBtn.draw(g);
        startBtn.draw(g);
        scoreBtn.draw(g);

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
    }

    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (instructionBtn.intoBound(mouseX, mouseY)) {
            if (instructionBtn.intoBound(mouseX, mouseY)) {
                showInstructions = !showInstructions; // đảo trạng thái
                repaint();
            }
        }

        else if (startBtn.intoBound(mouseX, mouseY)) {
            sound.stop();
            System.out.println("Bắt đầu trò chơi!");
            GamePanel gamePanel = new GamePanel(); // Khởi tạo GamePanel
            frame.getContentPane().add(gamePanel); // Thêm vào JFrame
            frame.revalidate();
            frame.repaint();
            frame.setSize(GamePanel.getSreenWidth(), GamePanel.getSreenHeight()+50);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            // Gắn KeyListener vào gamePanel
            KeyHandler keyH = new KeyHandler();
            gamePanel.addKeyListener(keyH);
            gamePanel.setFocusable(true);
            gamePanel.requestFocusInWindow();
            // Khởi động game
            gamePanel.startGameThread();

    }
        else if (scoreBtn.intoBound(mouseX, mouseY)) {
            showScore = ! showScore;
            System.out.println("Hiển thị điểm!");
            // TODO: hiển thị bảng điểm
        }
    }


    // Các phương thức MouseListener còn lại
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {}

}


