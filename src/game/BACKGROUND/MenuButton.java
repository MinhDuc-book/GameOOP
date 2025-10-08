package face;

import java.awt.*;

public class MenuButton {
    private int x, y, width, height;
    private String tennut;

    public MenuButton(String label, int x, int y, int width, int height) {
        this.tennut = label;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString(tennut, x + 15, y + 30);
    }

    public boolean isClicked(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height;
    }

    public String getTennut() {
        return tennut;
    }
}