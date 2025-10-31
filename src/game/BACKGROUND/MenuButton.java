package game.BACKGROUND;

import game.ENTITY.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton extends GameObject {
    private String Name;
    private static BufferedImage imageButton;

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
        g.setColor(Color.decode("#00b8e6"));
        g.setFont(new Font("Arial", Font.BOLD, 18));
        FontMetrics fm = g.getFontMetrics();
        int textX = x + (w - fm.stringWidth(Name)) / 2;
        int textY = y + ((h - fm.getHeight()) / 2) + fm.getAscent();
        g.drawString(Name, textX, textY);
    }

    public boolean intoBound(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + w &&
                mouseY >= y && mouseY <= y + h;
    }

    public String getName() {
        return Name;
    }

}