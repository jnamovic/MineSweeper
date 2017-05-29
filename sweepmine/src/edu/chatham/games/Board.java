package edu.chatham.games;

public class Board {
Cell[][] cellList;
Minesweeper game;	
	public Board(int x, int y, int mines, Minesweeper sweep)
	{
		game=sweep;
		cellList=new Cell[x][y];
		int minenum=mines;
		int space=x*y;
		for(int i=0; i<x;i++){
			for(int j=0;j<y;j++){
				if( (minenum>0&&(Math.random()>1.0*mines/(x*y)))||space==minenum){
					cellList[i][j]=new MineCell(i,j,sweep);
				}
				else{
					cellList[i][j]=new EmptyCell(i,j, sweep);	
				}
				
			}
		}
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
	
	public void blowAll(){
		
	}
	
	public void flagCell(Cell cell){
		
	}
}
