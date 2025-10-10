package game.BACKGROUND;

import game.MAIN.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Giaodien extends JPanel implements MouseListener {
    private Image image;
    private JFrame  frame;

    private MenuButton instructionBtn = new MenuButton(" Hướng dẫn", 100, 240, 150, 50);
    private MenuButton startBtn = new MenuButton("BẮT ĐẦU", 100, 170, 150, 50);
    private MenuButton scoreBtn = new MenuButton(" Điểm", 100, 100, 150, 50);
    private boolean showInstructions = false;
    private boolean showScore = false;

    public Giaodien(String imagePath, JFrame window) {
        image = new ImageIcon(getClass().getClassLoader().getResource(imagePath)).getImage();
        setPreferredSize(new Dimension(600, 700));
        addMouseListener(this);
        this.frame = window;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        instructionBtn.draw(g);
        startBtn.draw(g);
        scoreBtn.draw(g);

        if (showInstructions) {
            g.setColor(new Color(255, 255, 200, 220));
            g.fillRoundRect(300, 50, 300, 200, 20, 20);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.drawString("   HƯỚNG DẪN CHƠI ARKANOID", 300,70 );
            g.drawString("   - Di chuyển paddle bằng phím trái/phải", 300, 95);
            g.drawString("   - Đập bóng để phá gạch", 300, 115);
            g.drawString("   - Không để bóng rơi xuống đáy", 300, 135);
            g.drawString("   - Phá hết gạch để qua màn", 300, 155);
            g.drawString("   - Nhặt vật phẩm hỗ trợ khi có", 300, 175);
            g.drawString("  Chúc bạn chơi vui!", 300, 200);
        }
    }

    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (instructionBtn.isClicked(mouseX, mouseY)) {
            if (instructionBtn.isClicked(mouseX, mouseY)) {
                showInstructions = !showInstructions; // đảo trạng thái
                repaint();
            }

        }

        else if (startBtn.isClicked(mouseX, mouseY)) {

            System.out.println("Bắt đầu trò chơi!");

            frame.getContentPane().removeAll();

            GamePanel gamePanel = new GamePanel(); // Khởi tạo GamePanel
            frame.getContentPane().add(gamePanel); // Thêm vào JFrame
            frame.revalidate();
            frame.repaint();
            frame.setSize(600, 700);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            gamePanel.requestFocusInWindow();
            // Gắn KeyListener vào gamePanel
            KeyHandler keyH = new KeyHandler();
            gamePanel.addKeyListener(keyH);
            gamePanel.setFocusable(true);
            gamePanel.requestFocusInWindow();

            // Khởi động game
            gamePanel.startGameThread();

    }
        else if (scoreBtn.isClicked(mouseX, mouseY)) {
            showScore = ! showScore;
            System.out.println("Hiển thị điểm!");
            // TODO: hiển thị bảng điểm
        }
    }


    // Các phương thức MouseListener còn lại
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

}


