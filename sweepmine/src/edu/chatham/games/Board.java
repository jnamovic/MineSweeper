package edu.chatham.games;

import acm.graphics.GCompound;

public class Board extends GCompound {
private Cell[][] cellList;
private Minesweeper game;	
public static final int CELL_WIDTH = 50;
public static final int CELL_HEIGHT = 50;
private int rows, cols;
	public Board(int x, int y, int mines, Minesweeper sweep)
	{
		super();
		game=sweep;
		cellList=new Cell[x][y];
		int minenum=mines;
		int space=x*y;
		rows=x;
		cols=y;
		for(int i=0; i<x;i++){
			for(int j=0;j<y;j++){
				if( (minenum>0)&&((Math.random()>1.0*mines/(x*y))||space==minenum)){
					cellList[i][j]=new MineCell(i,j,sweep);
					minenum--;
					space--;
				}//
				else{
					cellList[i][j]=new EmptyCell(i,j, sweep);
					space--;
				}
				
			}
		}
		for(int g=0; g<rows;g++)
			for(int h=0;h<cols;h++)
				add(cellList[g][h],CELL_WIDTH*g,CELL_HEIGHT*h);
	}
	
	public void revealCell(Cell cell){
		for(int x=0; x<cellList.length; x++)
		{
			for(int i=0; i<cellList[x].length; i++)
			{	
				if(cell.equals(cellList[x][i])){
					cellList[x][i].reveal();
				}
			}
		}
	}
	
	public void blowAllUp(){
		
	}
	
	public Cell[][] getCells(){
		return cellList;
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getCols(){
		return cols;
	}
	
	public void flagCell(Cell cell){
		
	}
}
