import java.awt.*;

public class GameObject {
   private int x;
    private int y;
    private int width;
    private int height;
    private Color color;


    public GameObject(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    public int getX () {
        return x;
    }
    public int getY () {
        return y;
    }

    public GameObject(GameObject a) {
        /**
         * sao chép đối tượng a.
         */
        this.x = a.x;
        this.y = a.y;
        this.width = a.width;
        this.height = a.height;

    }
    /**
     * tô màu cho vật thể
     * @param g là cây bút để vẽ.
     */
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}

