package gui;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound2 {
	static Clip clip;
	
	public static void runMusic(String path) {
		try {
		AudioInputStream input = AudioSystem.getAudioInputStream(new File(path));
		clip = AudioSystem.getClip();
		clip.open(input);
		//clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void stopMusic() {
		clip.stop();
	}

}
