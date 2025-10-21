package game.MAIN;
import game.BACKGROUND.DefaultBackground;
import game.HIGHSCORE.HighscoreManager;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        DefaultBackground defaultBackground = new DefaultBackground("asset/background/giaodien.png", window);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Thêm giao diện vào cửa sổ

        GamePanel gamePanel = new GamePanel();

        window.add(defaultBackground);
        window.pack();
        window.setLocationRelativeTo(null); // căn giữa màn hình
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

        HighscoreManager.displayHighscores();


    }

}
