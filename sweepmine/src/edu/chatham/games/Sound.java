package edu.chatham.games;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

import javax.swing.JApplet;

public class Sound extends JApplet // Holds one audio file
{
private AudioClip song; // Sound player
private URL songPath; // Sound path
Sound(String filename)
{
	 try
	 {
songPath = new URL(getCodeBase(),filename); // Get the Sound URL
song = Applet.newAudioClip(songPath); // Load the Sound
	 }
	 catch(Exception e){} // Satisfy the catch
}
public void playSound()
{
	 song.loop(); // Play 
}
public void stopSound()
{
	 song.stop(); // Stop
}
public void playSoundOnce()
{
	 song.play(); // Play only once
	 System.out.println("boom");
}
}