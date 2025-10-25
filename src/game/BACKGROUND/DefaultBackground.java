package game.BACKGROUND;

import game.GAMESTATE.GameState;
import game.MAIN.*;
import game.SOUND.*;
import game.HIGHSCORE.HighscorePanel;
import game.BACKGROUND.MenuButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import static game.BACKGROUND.MenuButton.*;

public class DefaultBackground extends JPanel implements MouseListener {
    private Image image;
    private JFrame  frame;
    private static String soundpath = "/asset/sound/game-music-loop-7-145285.wav";
    private Sound sound = new Sound();
    private boolean showScore = false;

    public DefaultBackground() {}
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
        getInstructionButton().draw(g);
       getStartButton().draw(g);
       getScoreButton().draw(g);
        getInstructionButton().drawInstruction(g);
    }

    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (getInstructionButton().intoBound(mouseX, mouseY)) {
            if (getInstructionButton().intoBound(mouseX, mouseY)) {
                getInstructionButton().toggleInstructions();// đảo trạng thái
                repaint();
            }

        }

        else if (getStartButton().intoBound(mouseX, mouseY)) {
            System.out.println("Bắt đầu trò chơi!");
            GamePanel gamePanel = new GamePanel(); // Khởi tạo GamePanel
            frame.getContentPane().add(gamePanel); // Thêm vào JFrame
            frame.revalidate();
            frame.repaint();
            frame.setSize(GamePanel.getSreenWidth(), GamePanel.getSreenHeight()+50);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            sound.stop();
             // Gắn KeyListener vào gamePanel
            KeyHandler keyH = new KeyHandler();
            gamePanel.addKeyListener(keyH);
            gamePanel.setFocusable(true);
            gamePanel.requestFocusInWindow();
            // Khởi động game
            gamePanel.startGameThread();

    }
        else if (getScoreButton().intoBound(mouseX, mouseY)) {
            showScore = ! showScore;
            System.out.println("Hiển thị điểm!");

            HighscorePanel highscorePanel = new HighscorePanel(frame, () -> {
                // Quay lại menu chính
                frame.setContentPane(this); // this = DefaultBackground
                frame.revalidate();
            });

            frame.setContentPane(highscorePanel);
            frame.revalidate();
        }

    }


    // Các phương thức MouseListener còn lại
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {}

}


