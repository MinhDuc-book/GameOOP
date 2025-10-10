package game.MAIN;
import game.BACKGROUND.Giaodien;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.BACKGROUND.Giaodien;
import game.MAIN.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        Giaodien giaodien = new Giaodien("asset/background/giaodien.png", window);
        // Thêm giao diện vào cửa sổ
        window.add(giaodien);
        window.pack();
        window.setLocationRelativeTo(null); // căn giữa màn hình
        window.setVisible(true);

    }

}
