package game.MAIN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import game.MAIN.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Nguyen Minh Duc");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(300,100);
        window.setResizable(false);

        GamePanel panel = new GamePanel();
        window.add(panel);

        window.pack();
        window.setVisible(true);

        // đảm bảo panel nhận focus để KeyListener hoạt động
        panel.requestFocusInWindow();
    }
}
