package edu.chatham.games;

import acm.graphics.GImage;
import  sun.audio.*;    //import the sun.audio package
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.applet.AudioClip;
public class MineCell extends Cell{

	//AudioClip clip;
//	Sound boomer;
	public MineCell(int r, int c, Minesweeper d) 
	{
		super(r, c, d);
		GImage mineI = new GImage("mine.png");
		mineI.scale(CELL_WIDTH/mineI.getWidth(),CELL_HEIGHT/mineI.getHeight());
		add(mineI);
		mineI.sendToBack();
	}
	
	public void soundPlayer(String boom)
	{
		try {
			File explosion = new File(boom);
			AudioInputStream inStream;
			inStream = AudioSystem.getAudioInputStream(explosion);
			Clip clip = AudioSystem.getClip();
			clip.open(inStream);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//	public String randomName()
//	{
//		int randoNum = (int) (Math.random()*3);
//		return "boom"+randoNum+".wav";
//	}
	
	public void reveal()
	{
		isRevealed=true;
		rect.setFilled(false);
		soundPlayer("boom0.wav");
	
	}
	public boolean isMine()
	{
		return true;
	}
	public void scream()
	{
		System.out.println("AHHHHHHHHHHHHHHH");
	}
	
	
}
