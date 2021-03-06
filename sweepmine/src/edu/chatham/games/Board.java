package edu.chatham.games;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import acm.graphics.GCompound;
import acm.graphics.GPoint;

public class Board extends GCompound {
private Cell[][] cellList;
private Minesweeper game;	
public static final int CELL_WIDTH = 25;
public static final int CELL_HEIGHT = 25;
Clip clip;
private int rows, cols,mines;
	public Board(Difficulty_ diff, Minesweeper sweep)
	{
		super();
		game=sweep;
		switch (diff)
		{
		case BEGINNER: 
			rows=9;
			cols=9;
			mines=10;
			break;
		case INTERMEDIATE:
			rows=16;
			cols=16;
			mines=40;
			break;
		case EXPERT:
			rows=32;
			cols=16;
			mines=99;
			break;
		}
		cellList=new Cell[rows][cols];
		//System.out.println(cellList[x-1][y-1]);
		
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
	public void rePaintIt()
	{
		for(int i=0; i<rows;i++)
			for(int j = 0; j<cols;j++)
			{
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
		for(int a = 0; a<cellList.length;a++)
			for(int b = 0; b<cellList[a].length;b++)
				if(!cellList[a][b].isRevealed())
					cellList[a][b].sendToFront();
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
	
	public void blowAllUp()
	{
		soundPlayer(randomName());
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				if(!cellList[i][j].isRevealed())
				{
					cellList[i][j].reveal();
//					if(cellList[i][j].isMine()){
//						
//						pause(1);
//						stopIt();
//						removeAll();
//						rePaintIt();
									
//						pause(100);
				}
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
		}
	public void generate(int x,int y){
		double left=mines;
		double ratio= mines/1./ (rows*cols);
		while(left>0)
		{
			for(int i=0; i<rows;i++){
				for(int j = 0; j<cols;j++){
					
					if(Math.random()<ratio&&!cellList[i][j].isMine()&&!((i==x&&j==y)||(i==x-1&&j==y)||
							(i==x-1&&j==y-1)||(i==x-1&&j==y+1)||(i==x&&j==y-1)||(i==x&&j==y+1)||(i==x+1&&j==y)||
							(i==x+1&&j==y-1)||(i==x+1&&j==y+1))){
						remove(cellList[i][j]);
						cellList[i][j]=new MineCell(i,j,game);
						add(cellList[i][j],CELL_WIDTH*i,CELL_HEIGHT*j);
						left--;
						if(left==0)
							break;
//						System.out.println(left);
//						System.out.println(mines);
					}
					//System.out.println(rando + " " + ratio);
				}
				if(left==0)
					break;
			}
		}
		
}
	public void soundPlayer(String boom)
	{
		try {
			File explosion = new File(boom);
			AudioInputStream inStream;
			inStream = AudioSystem.getAudioInputStream(explosion);
			clip = AudioSystem.getClip();
			clip.open(inStream);
			clip.start();
			} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void stopIt()
	{
		clip.drain();
	}
	public String randomName()
	{
		int randoNum = (int) (Math.random()*8);
		return "boom"+randoNum+".wav";
	}
	public boolean allFlagged()
	{
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++){
				if(!cellList[i][j].isMine()&&cellList[i][j].isFlagged)
					return false;
				if(cellList[i][j].isMine()&&!cellList[i][j].isFlagged)
				 	return false;
			}
		return true;
	}
	public boolean allRevealed()
	{
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				if(!cellList[i][j].isMine()&&!cellList[i][j].isRevealed())
					return false;
		return true;
	}
	public boolean blownUpSir()
	{
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				if(cellList[i][j].isMine()&&cellList[i][j].isRevealed)
				{
					return true;
				}
			return false;
	}
}
