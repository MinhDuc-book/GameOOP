package game.SOUND;

import javax.sound.sampled.*;
import java.io.InputStream;

public class Sound {
    private Clip clip;
    private String fileName;

    private static float globalVolume = 1.0f; // 0 = mute , 1 = max

    public void setSound(String fileName) {
        try {
            this.fileName = fileName;
            InputStream is = Sound.class.getResourceAsStream(fileName);
            if (is == null) throw new IllegalArgumentException("File không tồn tại: " + fileName);

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);

            applyVolume();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi load âm thanh: " + e.getMessage());
        }
    }

    public static void setGlobalVolume(float value) {
        if (value < 0f) value = 0f;
        if (value > 1f) value = 1f;
        globalVolume = value;
    }

    public static float getGlobalVolume() {
        return globalVolume;
    }

    public void applyVolume() {
        if (clip != null) {
            try {
                FloatControl gainControl = (FloatControl)
                        clip.getControl(FloatControl.Type.MASTER_GAIN);

                float dB = (float)(20 * Math.log10(globalVolume == 0 ? 0.0001 : globalVolume));
                gainControl.setValue(dB);
            } catch (Exception ignored) {}
        }
    }

    public void playSound() {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
            applyVolume();
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            applyVolume();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
        }
    }
}
