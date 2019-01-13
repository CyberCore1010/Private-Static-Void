package Backend;

import javax.sound.sampled.*;
import java.io.File;

public class SFXplayer {
    private static Clip music;
    public static int gain = 10;

    public static synchronized void footstepSound() {
        int random = 1 + (int)(Math.random() * ((6 - 1)+1));
        try {
            AudioInputStream temp = AudioSystem.getAudioInputStream(new File("res/SFX/Footstep"+random+".wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(temp);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static synchronized void playSFX(String name) {
        try {
            AudioInputStream temp = AudioSystem.getAudioInputStream(new File("res/SFX/"+name+".wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(temp);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static synchronized void backgroundMusic(String Music) {
        if(music != null) {
            music.stop();
            music.close();
        }
        try {
            AudioInputStream temp = AudioSystem.getAudioInputStream(new File("res/Music/"+Music+".wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(temp);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-1 * gain);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            music = clip;
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public static void resetVolume() {
        FloatControl volume = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-1 * gain);
    }
}
