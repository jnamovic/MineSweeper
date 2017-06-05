package edu.chatham.games;


//easy 8x8-10 med 16x16-40 16x30-99
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import acm.graphics.GPoint;
import acm.program.GraphicsProgram;

/**
 * The classic Minesweeper game, (c) Microsoft.
 * @author Mark Jones
 *
 */
@SuppressWarnings("serial")
public class Minesweeper extends GraphicsProgram {
	/**
	 * Runs Minesweeper as an application.
	 * @param args	none are expected
	 */
	public static void main(String[] args) {
		(new Minesweeper()).start(args);
	}
	
	/**
	 * Create a Minesweeper game.
	 */
	public Minesweeper () {}
	
	/**
	 * Initializes the board, which is also the GUI.
	 */
	public void setup()
	{
		board= new Board(15,15,100, this);
		setSize((int)board.getWidth(),(int)board.getHeight());
		add(board);
	}
	public void init() {
		setup();
		addMouseListeners();
		add(newGame = new JButton("New Game"), SOUTH);// adds the "new game" button to the southern border 
		ActionListener buttonlistener = new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				removeAll();
				setup();
			}
			};
	
	}
	
	/**
	 * Reveal a cell on a mouseclick.
	 */
	public void mouseClicked(MouseEvent e) {//make sure to add a block on new game until won or lost
		
		if(SwingUtilities.isLeftMouseButton(e)){
			Cell cell=null;
		
		for(int i=0;i<board.getCells().length;i++){
			for(int x=0;x<board.getCells()[i].length;x++){
				if( board.getCells()[i][x].contains(new GPoint(e.getPoint()))){
				 cell=board.getCells()[i][x];
				}
			}
		}
			//Cell cell = board.getCellAt(e.getPoint());
		while(cell instanceof MineCell&& turns==0){
			board=new Board(20,30,10, this);
			for(int i=0;i<board.getCells().length;i++){
				for(int x=0;x<board.getCells()[i].length;x++){
					if( board.getCells()[i][x].contains(new GPoint(e.getPoint()))){
					 cell=board.getCells()[i][x];
					}
				}
			}
		}
		turns++;
		board.revealCell(cell);
		}
		if(e.equals(MouseEvent.BUTTON2)){
			Cell cell = (Cell) e.getSource();
			board.flagCell(cell);}
	}
	
	/** 
	 * Handler for button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		// complete the code
	}
	
	public void gameEnd(){
		board.blowAllUp();
	}

	public Board getBoard(){
		return board;
	}
	// other declarations go here
	private Board board;
	private int turns=0;
	JButton newGame;
}

