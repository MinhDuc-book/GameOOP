package game.BACKGROUND;

import game.ENTITY.GameObject;

import java.awt.*;

public class MenuButton extends GameObject {
    private String Name;

    public MenuButton(String label, int x, int y, int width, int height) {
        this.Name = label;
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, w, h);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString(Name, x + 15, y + 30);
    }

    public boolean isClicked(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + w &&
                mouseY >= y && mouseY <= y + h;
    }

    public String getName() {
        return Name;
    }

}
