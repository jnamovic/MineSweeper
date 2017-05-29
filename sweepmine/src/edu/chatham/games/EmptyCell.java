package edu.chatham.games;

import acm.graphics.GImage;

public class EmptyCell extends Cell {
	public EmptyCell(int r, int c, Minesweeper d) 
	{
		super(r, c, d);
		GImage mineI = new GImage("mine");
		mineI.scale(CELL_WIDTH/mineI.getWidth(),CELL_HEIGHT/mineI.getHeight());
		add(mineI);
		mineI.sendToBack();
	}

	@Override
	public void reveal() {
		isRevealed=true;
		rect.setFilled(false);
		
	}
}
