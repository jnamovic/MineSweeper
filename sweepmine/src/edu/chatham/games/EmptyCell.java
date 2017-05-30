package edu.chatham.games;

import acm.graphics.GImage;

public class EmptyCell extends Cell {
	int minesNear=0;

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
		//add the mines-near number to the image
	}
}
