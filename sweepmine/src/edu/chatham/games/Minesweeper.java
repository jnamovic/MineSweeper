package edu.chatham.games;


//easy 8x8-10 med 16x16-40 16x30-99
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import APCS2016Proj06Solitaire.Difficulty;
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
		initDifficulty();//sets up the difficulty combobox
		makeBoard(getDifficulty());
		setSize((int)board.getWidth(),(int)board.getHeight());
		turns=0;
		startTime = System.currentTimeMillis();
			
	}
	public void init() {
		setup();
		addMouseListeners();
		add(newGame = new JButton("New Game"), SOUTH);// adds the "new game" button to the southern border 
	    add(difficult, SOUTH);//adds the difficulty combobox to the southern border
	    add(messages = new JLabel("Welcome to minesweeper"), NORTH);
	    add(timer = new JLabel("0"),NORTH);
		ActionListener buttonlistener = new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				removeAll();
				setup();
				messages.setText("Good Luck!");
			}
			};
			newGame.addActionListener(buttonlistener);
			run();
	}
	public void run()
	{
		while(true)
		{
			if(keepCounting)
			{
				long totalSeconds= (System.currentTimeMillis() - startTime)/1000;
				int dispSecond = (int) (totalSeconds%60);
				int dispMin = (int) (totalSeconds/60);
				System.out.println(dispMin);
				timer.setText(dispMin + ":" + dispMin);
			}
				
		}
	}
	public void makeBoard(Difficulty_ diff)
	{
		board= new Board(diff, this);
		add(board);
	}
	
	/**
	 * Reveal a cell on a mouseclick.
	 */
	public void mouseClicked(MouseEvent e) {//make sure to add a block on new game until won or lost
		
		if(SwingUtilities.isLeftMouseButton(e)){
			int xLoc=-2;
			int yLoc=-2;
			Cell cell=null;
		
		for(int i=0;i<board.getCells().length;i++){
			for(int x=0;x<board.getCells()[i].length;x++){
				if( board.getCells()[i][x].contains(new GPoint(e.getPoint()))){
				 cell=board.getCells()[i][x];
				 xLoc=i;
				 yLoc=x;
				 if(turns==0)
				 {
					 board.generate(xLoc, yLoc,10);
					 newGame.setEnabled(false);
					 startTime = System.currentTimeMillis();
					 keepCounting=true;
				 }
				 turns++;
				 board.revealCell(cell);
				}
			}
		}
			//Cell cell = board.getCellAt(e.getPoint());
		
		}
		if(SwingUtilities.isRightMouseButton(e)){
			Cell cell=null;
			for(int i=0;i<board.getCells().length;i++)
			for(int x=0;x<board.getCells()[i].length;x++)
				if( board.getCells()[i][x].contains(new GPoint(e.getPoint())))
				 cell=board.getCells()[i][x];
			board.flagCell(cell);}
		if(board.allFlagged()||board.allRevealed())
		{
			gameEnd();
			newGame.setEnabled(true);
			messages.setText("Congratulations, you won!");
		}
		
		if(board.blownUpSir())
		{
			board.blowAllUp();
			gameEnd();
			messages.setText("You blew up!");
		}
	}
	
	/** 
	 * Handler for button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		// complete the code
	}
	
	public void gameEnd(){
		
		newGame.setEnabled(true);
		keepCounting=false;
	}

	public Board getBoard(){
		return board;
	}
	private void initDifficulty() {
		difficult = new JComboBox<String>();
		difficult.addItem("Beginner");//adds the beginner option to the combo box
		difficult.addItem("Intermediate");//adds the intermediate option to the combo box
		difficult.addItem("Expert");//adds the expert option to the combo box
		difficult.setEditable(false);
		difficult.setSelectedItem("Beginner");//sets the default value of the combo box to intermediate
	} 
	
	private Difficulty_ getDifficulty() {
		String name = (String) difficult.getSelectedItem();// sets name equal to the current value of the difficulty combo box
		if (name.equals("Beginner")) return Difficulty_.BEGINNER;//returns beginner difficulty if the combo box is set to beginner
		if (name.equals("Intermediate")) return Difficulty_.INTERMEDIATE;//returns intermediate difficulty if the combo box is set to intermediate
		if (name.equals("Expert")) return Difficulty_.EXPERT;//returns expert difficulty if the combo box is set to expert
		
		return Difficulty_.INTERMEDIATE;
	}
	// other declarations go here
	private Board board;
	private int turns=0;
	JButton newGame;
	JLabel messages,timer;
	JComboBox<String> difficult;
	long startTime;
	boolean keepCounting;
}

