package edu.chatham.games;

import acm.graphics.GImage;

public class MineCell extends Cell{


	public MineCell(int r, int c) 
	{
		super(r, c);
		GImage mineI = new GImage("mine");
		mineI.scale(CELL_WIDTH/mineI.getWidth(),CELL_HEIGHT/mineI.getHeight());
		add(mineI);
		mineI.sendToBack();
	}
	
	public void reveal()
	{
		rect.setFilled(false);
		//add boom every time it's a mime
	}
}
