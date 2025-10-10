package game.SOUND;
import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class Sound {
    private Clip clip; // phat lai.
    private String fileName; // file am thanh.
    public static void setSound(String fileName) {
        try
        {
            InputStream is = Sound.class.getResourceAsStream(fileName);
            if(fileName == null) {
                throw new IllegalArgumentException("sound can not be null.");
            }
            assert is != null;
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
