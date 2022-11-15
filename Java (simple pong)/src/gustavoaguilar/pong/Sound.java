package gustavoaguilar.pong;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	
	public static final AudioClip Bip = Applet.newAudioClip(Sound.class.getResource("Bip.wav"));
	public static final AudioClip Hit = Applet.newAudioClip(Sound.class.getResource("Hit.wav"));
	public static final AudioClip GO = Applet.newAudioClip(Sound.class.getResource("GO.wav"));

}
