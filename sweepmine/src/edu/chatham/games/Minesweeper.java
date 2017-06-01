package edu.chatham.games;


//easy 8x8-10 med 16x16-40 16x30-99
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
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
	public void init() {
		setSize(60,60);
		board= new Board(20,30,10, this);
		add(board);
	}
	
	/**
	 * Reveal a cell on a mouseclick.
	 */
	public void mouseClicked(MouseEvent e) {
		if(e.equals(MouseEvent.BUTTON1)){
		Cell cell = (Cell) e.getSource();
		while(cell instanceof MineCell&& turns==0){
			board=new Board(20,30,10, this);
			cell = (Cell) e.getSource();
		}
		turns++;
		board.revealCell(cell);}
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
}

