import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Sound {
    GamePanel gp;
    Clip clip;
    File soundFile;
    public Sound(GamePanel gp, String soundFilePath) {
        this.gp = gp;
        soundFile = new File(soundFilePath);
    }

    public void setClip(){
        try {
            this.clip = AudioSystem.getClip();
            this.clip.open(AudioSystem.getAudioInputStream(soundFile));

        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }

    public void Loop(){
        setClip();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void Start() {
        setClip();
        clip.start();
    }
    public void Stop(){
        clip.stop();
    }
}
