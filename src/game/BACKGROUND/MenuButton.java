package game.BACKGROUND;


import game.ENTITY.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class MenuButton extends GameObject {
    private String Name;
    private static BufferedImage imageButton;

    static int startX = 230;
    static int startY  = 130;
    private static MenuButton instructionBtn = new MenuButton(" Hướng dẫn", startX,  startY+2*70, 150, 50);
    private static MenuButton startBtn = new MenuButton("BẮT ĐẦU", startX, startY+70, 150, 50);
    private static MenuButton scoreBtn = new MenuButton(" Điểm", startX, startY, 150, 50);
    private static MenuButton replayBtn = new MenuButton("CHƠI LẠI", startX, startY + 5 * 70, 150, 50);
    private static MenuButton exitBtn = new MenuButton("THOÁT", startX, startY + 4 * 70, 150, 50);
    private boolean showInstructions = false;
    private boolean showScore = false;

    public MenuButton(String label, int x, int y, int width, int height) {
        this.Name = label;
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
    }

    static {
        try {
            imageButton = ImageIO.read(MenuButton.class.getResourceAsStream("/asset/background/Button.png"));
        } catch (Exception e) {
            System.out.println("Could not load button image: " + e.getMessage());
        }
    }

    public void draw(Graphics g) {
        // Draw button image
        if (imageButton != null) {
            g.drawImage(imageButton, x, y, w, h, null);
        } else {
            // Fallback if image not loaded
            g.setColor(new Color(100, 100, 150));
            g.fillRoundRect(x, y, w, h, 10, 10);
            g.setColor(Color.WHITE);
            g.drawRoundRect(x, y, w, h, 10, 10);
        }

        // Draw text centered on button
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        FontMetrics fm = g.getFontMetrics();
        int textX = x + (w - fm.stringWidth(Name)) / 2;
        int textY = y + ((h - fm.getHeight()) / 2) + fm.getAscent();
        g.drawString(Name, textX, textY);
    }
      public void drawInstruction(Graphics g ) {
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

    public boolean intoBound(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + w &&
                mouseY >= y && mouseY <= y + h;
    }

    public static MenuButton getInstructionButton() {
        return instructionBtn;
    }
    public static MenuButton getStartButton () {
        return startBtn;
    }
    public static MenuButton getScoreButton() {
        return scoreBtn;
    }
    public void toggleInstructions() {
        showInstructions = !showInstructions;
    }

    public boolean getShowScore() {
        return showScore ;
    }
    public static MenuButton getReplayButton() {
        return replayBtn;
    }

    public static MenuButton getExitButton() {
        return exitBtn;
    }


}