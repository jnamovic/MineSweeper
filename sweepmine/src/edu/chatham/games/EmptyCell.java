package edu.chatham.games;

import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GLabel;

public class EmptyCell extends Cell {
	int minesNear=0;
	GLabel number;

	public EmptyCell(int r, int c, Minesweeper d) 
	{
		super(r, c, d);
		
	}

	@Override
	public void reveal() {
		if(!isRevealed){
			isRevealed=true;
			rect.setFilled(false);
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
						if(!(x+row<0||x+row>game.getBoard().getRows()||y+col<0||y+col>game.getBoard().getCols()))
							game.getBoard().getCells()[row+x][col+y].reveal();
					}
				}	
			}	
		}
		number = new GLabel(""+minesNear);
		if(minesNear>0)
		add(number,15,30);//add the mines-near number to the image
		colorNum();
	}
	public void colorNum()
	{
		switch(minesNear)
		{
		case 1:number.setColor(Color.blue);
		break;
		case 2:number.setColor(Color.green);
		break;
		case 3: number.setColor(Color.red);
		break;
		case 4: number.setColor(Color.magenta);
		break;
		case 5: number.setColor(Color.orange);
		break;
		case 6: number.setColor(Color.cyan);
		break;
		case 7: number.setColor(Color.black);
		break;
		case 8: number.setColor(Color.gray);
		break;
		}
	}
	public int getNum()
	{
		return 0;
	}
	public void scream()
	{
		System.out.println("I'm empty");
	}
}
