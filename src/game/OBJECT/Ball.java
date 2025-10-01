package game.OBJECT;

import java.awt.*;

public class Ball extends MovableObject {
    public int speed = 2;
    public boolean isActive = false;

    // tốc độ theo từng trục
    public int vx = 0;
    public int vy = -speed;

    // cập nhật vị trí và bật nảy
    public void update(Rectangle worldBounds) {
        if (!isActive) return;

        // move
        x += vx;
        y += vy;

        // nảy ở tường trái phải
        if (x < worldBounds.x) {
            x = worldBounds.x;
            vx = -vx;
        } else if (x + w > worldBounds.x + worldBounds.width) {
            x = worldBounds.x + worldBounds.width - w;
            vx = -vx;
        }

        // nảy ở tường trên
        if (y < worldBounds.y) {
            y = worldBounds.y;
            vy = -vy;
        }

        // rơi xuống là chết
        if (y + h > worldBounds.y + worldBounds.height) {
            // đặt xuống dưới và deactivate (không bật lên)
            y = worldBounds.y + worldBounds.height - h;
            isActive = false;
            vx = 0;
            vy = 0;
        }
    }

    // nảy khi gặp vật thể khác
    public void bounceOff(GameObject other) {
        if (other == null) return;
        Rectangle rOther = other.getBounds();
        Rectangle rBall = getBounds();
        if (!rBall.intersects(rOther)) return;

        Rectangle inter = rBall.intersection(rOther);

        // Decide collision normal by smallest overlap
        if (inter.width < inter.height) {
            // horizontal collision -> reflect vx
            if (rBall.x < rOther.x) {
                x = rOther.x - w;
            } else {
                x = rOther.x + rOther.width;
            }
            vx = -vx;
        } else {
            // vertical collision -> reflect vy
            if (rBall.y < rOther.y) {
                y = rOther.y - h;
            } else {
                y = rOther.y + rOther.height;
            }
            vy = -vy;
        }

        // ensure minimum speed
        if (Math.abs(vx) < 2) vx = vx < 0 ? -2 : 2;
        if (Math.abs(vy) < 2) vy = vy < 0 ? -2 : 2;

        // Brick hit: decrement hit count if exists
        if (other instanceof Brick) {
            Brick b = (Brick) other;
            try {
                if (b.countColid > 0) b.countColid--;
            } catch (Throwable ignored) { }
        }
    }

    // convenience: check intersection and bounce if needed
    public void checkCollision(GameObject other) {
        if (other == null) return;
        if (getBounds().intersects(other.getBounds())) {
            bounceOff(other);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }

    // draw simple white ball
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, w, h);
    }

    // place ball and set initial velocity by angle (degrees) and speed
    public void launch(int startX, int startY, double angleDeg, int launchSpeed) {
        this.x = startX;
        this.y = startY;
        this.speed = launchSpeed;
        double rad = Math.toRadians(angleDeg);
        this.vx = (int) Math.round(Math.cos(rad) * launchSpeed);
        this.vy = (int) Math.round(Math.sin(rad) * launchSpeed);
        this.isActive = true;
    }

    // simple setter for bounds
    public void setBounds(int bx, int by, int bw, int bh) {
        this.x = bx;
        this.y = by;
        this.w = bw;
        this.h = bh;
    }
}
