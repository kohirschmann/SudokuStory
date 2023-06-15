import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * This class provides methods for playing and stopping a sound file.
 */
public class Sound_test {

    private static Clip clip;  // making clip variable static
    
    /**
     * Plays a sound file specified by the provided file path.
     * The sound file will be looped continuously until it is manually stopped.
     *
     * @param filepath The path of the sound file to be played.
     */
    
    public static void play(String filepath) {
        try {
            File musicPath = new File(filepath);

            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();  // assigning to static variable clip
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Stops playing the sound file.
     * If no sound file is currently playing, this method will do nothing.
     */
    
    public static void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}