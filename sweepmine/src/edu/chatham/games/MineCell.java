package edu.chatham.games;

import acm.graphics.GImage;
import  sun.audio.*;    //import the sun.audio package
import java.io.*;
import java.applet.AudioClip;
public class MineCell extends Cell{

	AudioClip clip;
	Sound boomer;
	public MineCell(int r, int c, Minesweeper d) 
	{
		super(r, c, d);
		GImage mineI = new GImage("mine.png");
		mineI.scale(CELL_WIDTH/mineI.getWidth(),CELL_HEIGHT/mineI.getHeight());
		add(mineI);
		mineI.sendToBack();
		boomer = new Sound("boom.mp3");
	}
	
	public void reveal()
	{
		isRevealed=true;
		rect.setFilled(false);
		boomer.playSoundOnce();
		//add boom every time it's a mime////
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
