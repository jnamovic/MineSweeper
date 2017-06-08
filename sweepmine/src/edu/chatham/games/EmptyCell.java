package edu.chatham.games;

import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GLabel;

public class EmptyCell extends Cell {
	int minesNear=0;
	GImage number;

	public EmptyCell(int r, int c, Minesweeper d) 
	{
		super(r, c, d);
		
	}

	@Override
	public void reveal() {
		if(!isRevealed){
			isRevealed=true;
			rect.setFillColor(Color.lightGray);
			for(int x=-1;x<2;x++){
				for(int y=-1;y<2;y++){//the line after this one will cause an error when the square is in a corner but i dont know how to fix it
					if(!(x+row<0||x+row>game.getBoard().getRows()||y+col<0||y+col>game.getBoard().getCols()))
						if((game.getBoard().getCells()[row+x][col+y]) instanceof MineCell){
							minesNear++;
						}
				}
			}
			if(minesNear==0){
				for(int x=-1;x<2;x++){
					for(int y=-1;y<2;y++){//the line after this one will cause an error when the square is in a corner but i dont know how to fix it
						if(!(x+row<0||x+row>game.getBoard().getRows()||y+col<0||y+col>game.getBoard().getCols())){
							game.getBoard().getCells()[row+x][col+y].reveal();
							
						}
					}
				}	
			}	
		}
		if(minesNear>0)
		{
			colorNum();
			number.scale(getWidth()/number.getWidth(),getHeight()/number.getHeight());
		add(number);
		//add the mines-near number to the image
		
		}
		
	}
	public void colorNum()
	{
		switch(minesNear)
		{
		case 1:number = new GImage("1.png");
		break;
		case 2:number = new GImage("2.png");
		break;
		case 3: number = new GImage("3.png");
		break;
		case 4: number = new GImage("4.png");
		break;
		case 5: number = new GImage("5.png");
		break;
		case 6: number = new GImage("6.png");
		break;
		case 7: number = new GImage("7.png");
		break;
		case 8: number = new GImage("8.png");
		break;
		}
	}
	public boolean isMine()
	{
		return false;
	}
	public void scream()
	{
		System.out.println("I'm empty");
	}
}
