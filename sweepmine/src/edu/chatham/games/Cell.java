package edu.chatham.games;

import java.awt.Color;
import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GRect;

/**
 * An abstract class that models a Minesweeper cell.
 * @author Mark Jones
 *
 */

@SuppressWarnings("serial")
public abstract class Cell extends GCompound {

	public static final int CELL_WIDTH = 25;
	public static final int CELL_HEIGHT = 25;
	Minesweeper game;
	GImage flag;
	GRect rect;
	
	// the instance variables are protected for convenient access by the subclasses
	protected int row, col;
	protected boolean isRevealed,isFlagged,miner;
	protected int mineCount;
	
	/**
	 * Creates a Minesweeper Cell given a row and a column.
	 * The default unrevealed cell is currently a simple blue rectangle. 
	 * @param r
	 * @param c
	 */
	public Cell(int r, int c, Minesweeper d) {
		super();
		game=d;
		rect = new GRect(CELL_WIDTH, CELL_WIDTH);
		rect.setFillColor(Color.gray);
		rect.setFilled(true);
		add(rect, 0, 0);
		row = r;
		col = c;
		isRevealed = false;
		isFlagged=false;
		flag = new GImage("flag.png");
		flag.scale(CELL_WIDTH/flag.getWidth(),CELL_HEIGHT/flag.getHeight());
		add(flag);
		flag.setVisible(false);
	}
	
	/**
	 * Any subclass of Cell must implement this method to change the 
	 * appearance and state of the cell to reflect its being revealed.
	 */
	public abstract void reveal();
	
	/**
	 * Test if the cell has been revealed.
	 * @return   true if the cell has been revealed, false otherwise
	 */
	public boolean isRevealed() {
		return isRevealed;
	}
	
	/**
	 * A cell has a count of the surrounding mines.
	 */
	public void addToMineCount() {
		mineCount++;
	}
	
	/**
	 * Getter for the mine count.
	 * @return
	 */
	public int getMineCount() {
		return mineCount;
	}

	/**
	 * Getter for the cell's row.
	 * @return   the row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Getter for the cell's column
	 * @return   the column
	 */
	public int getCol() {
		return col;
	}
	public boolean isMine()
	{
		return miner;
	}
	public void flagMe()
	{
		isFlagged=!isFlagged;
		flag.setVisible(isFlagged);
	
	}
	public boolean getflag(){
		return isFlagged;
	}
	
	/**
	 * A printable representation of a Cell.
	 */
	public String toString() {
		return String.format("[%d,%d]", row, col);
	}
}
 

