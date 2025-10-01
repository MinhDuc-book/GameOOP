package game.OBJECT;

import java.awt.*;

public class StrongBrick extends Brick {

    public StrongBrick(int x, int y, int w, int h, Color color) {
        super(x, y, w, h, color);
        // đảm bảo không bao giờ <= 0 nếu code kiểm tra trực tiếp field
        this.countColid = Integer.MAX_VALUE;
    }

    @Override
    public void setDestroyed() {
        // ignore attempts to mark this brick as destroyed
    }

    @Override
    public boolean isDestroyed() {
        // luôn trả về false => không bao giờ bị coi là destroyed
        return false;
    }
}
