import java.awt.*;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Color color;

    public GameObject(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public GameObject(GameObject a) {
        this.x = a.x;
        this.y = a.y;
        this.width = a.width;
        this.height = a.height;
        this.color = a.color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public  int getWidth() {
        return width;
    }
    public  int getHeight() {
        return height;
    }

    /**
     * Phương thức trừu tượng để cập nhật trạng thái đối tượng.
     * Các lớp con phải tự định nghĩa.
     */
    public abstract void update();

    /**
     * Phương thức trừu tượng để vẽ đối tượng.
     * Các lớp con phải tự định nghĩa.
     */
    public abstract void render(Graphics g);
}