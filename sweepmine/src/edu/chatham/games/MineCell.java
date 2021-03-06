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
import java.awt.Color;
public class MineCell extends Cell{

	//AudioClip clip;
//	Sound boomer;
	GImage mineI;
	public MineCell(int r, int c, Minesweeper d) 
	{
		super(r, c, d);
		mineI = new GImage("mine.png");
		mineI.scale(CELL_WIDTH/mineI.getWidth(),CELL_HEIGHT/mineI.getHeight());
		add(mineI);
		mineI.sendToBack();
		mineI.setVisible(false);
	}
	
	

	
	public void reveal()
	{
		isRevealed=true;
		rect.setFillColor(Color.lightGray);
		mineI.setVisible(true);
		mineI.sendToFront();
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
