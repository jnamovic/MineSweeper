package edu.chatham.games;

import acm.graphics.GImage;

public class MineCell extends Cell{


	public MineCell(int r, int c, Minesweeper d) 
	{
		super(r, c, d);
		GImage mineI = new GImage("mine");
		mineI.scale(CELL_WIDTH/mineI.getWidth(),CELL_HEIGHT/mineI.getHeight());
		add(mineI);
		mineI.sendToBack();
	}
	
	public void reveal()
	{
		isRevealed=false;
		rect.setFilled(false);
		//add boom every time it's a mime
	}
}
