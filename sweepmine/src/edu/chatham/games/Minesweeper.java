package edu.chatham.games;



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
		// complete the code
	}
	
	/**
	 * Reveal a cell on a mouseclick.
	 */
	public void mouseClicked(MouseEvent e) {
		if(e.equals(MouseEvent.BUTTON1)){
		Cell cell = (Cell) e.getSource();
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

	// other declarations go here
	private Board board;
}

