package edu.chatham.games;

import java.awt.Point;

import acm.graphics.GCompound;
import acm.graphics.GPoint;

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
		//System.out.println(cellList[x-1][y-1]);
		int minenum=mines;
		int space=x*y;
		rows=x;
		cols=y;
		for(int i=0; i<rows;i++)
			for(int j = 0; j<cols;j++)
			{
				cellList[i][j]=new EmptyCell(i,j,game);
				add(cellList[i][j],CELL_WIDTH*i,CELL_HEIGHT*j);
			}
		
//		while(minenum>0)
//		{
//			do{
//			xPos = (int) (Math.random()*rows);
//			yPos = (int) (Math.random()*cols);
//			}while(cellList[xPos][yPos]!=null);
//			cellList[xPos][yPos]= new MineCell(xPos,yPos,game);
//			minenum--;
//				
//		}
//		for(int blag = 0;blag<cellList.length;blag++)
//			for(int glab=0;glab<cellList[blag].length;glab++)
//				if(cellList[blag][glab]==null)
//					cellList[blag][glab]=new EmptyCell(blag,glab,game);
//		for(int g=0; g<rows;g++)
//			for(int h=0;h<cols;h++)
//				add(cellList[g][h],CELL_WIDTH*g,CELL_HEIGHT*h);
	}
	public void paintIt()
	{
		for(int i=0; i<rows;i++)
			for(int j = 0; j<cols;j++)
			{
				cellList[i][j]=new EmptyCell(i,j,game);
				add(cellList[i][j],CELL_WIDTH*i,CELL_HEIGHT*j);
			}
	}
	public void revealCell(Cell cell){
		for(int x=0; x<cellList.length; x++)
		{
			for(int i=0; i<cellList[x].length; i++)
			{	
				//cell.scream();
				if(cell.equals(cellList[x][i])){
					cellList[x][i].reveal();
				}
			}
		}
	}
	
	public Cell getCellAt(Point loc){
		Cell result;
		for(int i=0;i<cellList.length;i++){
			for(int x=0;x<cellList.length;x++){
				if( cellList[i][x].contains(new GPoint(loc))){
					result=cellList[i][x];
				}
			}
		}
		return null;
	}
	
	public void blowAllUp(){
		
	}
	
	public Cell[][] getCells(){
		return cellList;
	}
	
	public int getRows(){
		return rows-1;
	}
	
	public int getCols(){
		return cols-1;
	}
	
	public void flagCell(Cell cell){
		cell.flagMe();
		System.out.println("here");
	}
	public void generate(int x,int y, double mines){
		double left=mines;
		double ratio= mines/ (rows*cols);
		while(left>0)
		{
			for(int i=0; i<rows;i++)
				for(int j = 0; j<cols;j++)
					if(Math.random()<ratio&&cellList[i][j].getNum()==0&&!((i==x&&j==y)||(i==x-1&&j==y)||
							(i==x-1&&j==y-1)||(i==x-1&&j==y+1)||(i==x&&j==y-1)||(i==x&&j==y+1)||(i==x+1&&j==y)||
							(i==x+1&&j==y-1)||(i==x+1&&j==y+1))){
						remove(cellList[i][j]);
						cellList[i][j]=new MineCell(i,j,game);
						add(cellList[i][j],CELL_WIDTH*i,CELL_HEIGHT*j);
						left--;
					}
		}
		
}
}
