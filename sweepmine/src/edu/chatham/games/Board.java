package edu.chatham.games;

public class Board {
Cell[][] cellList;
	
	public Board(int x, int y)
	{
		cellList=new Cell[x][y];
		for(int i=0; i<x;i++){
			for(int j=0;j<y;j++){
				//cellList[i][j]=new Cell(i,j);
			}
		}
	}
	
	public void revealCell(Cell cell){
		
	}
	
	public void flagCell(Cell cell){
		
	}
}
