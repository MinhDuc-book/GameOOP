package game.SOUND;

import javax.sound.sampled.*;
import java.io.InputStream;

public class Sound {
    private Clip clip; // phat lai.
    private String fileName; // file am thanh.

    public void setSound(String fileName) {
        try {
            this.fileName = fileName;
            if (fileName == null) throw new IllegalArgumentException("sound can not be null.");
            InputStream is = Sound.class.getResourceAsStream(fileName);
            if (is == null) {
                throw new IllegalArgumentException("Not exist source.");
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);

        } catch (Exception e) {
            throw new RuntimeException("error when start sound.");
        }
    }
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
    public void loop() {
        if (this.clip != null) {
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    public void playSound() {
        if (this.clip != null) {
            this.clip.start();
        }
    }





}
