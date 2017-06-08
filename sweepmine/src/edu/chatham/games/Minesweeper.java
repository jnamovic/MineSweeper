package edu.chatham.games;


//easy 8x8-10 med 16x16-40 16x30-99
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.graphics.GScalable;
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
		//sets up the difficulty combobox
		makeBoard(getDifficulty());
		setSize((int)board.getWidth()+X_OFFSET,(int)board.getHeight()+Y_OFFSET);
		turns=0;
		startTime = System.currentTimeMillis();
		gameDone=false;
		iAllowYouTo=true;	
	}
	public void init() {
		initDifficulty();
		setup();
		addMouseListeners();
		add(newGame = new JButton("New Game"), SOUTH);// adds the "new game" button to the southern border 
	    add(difficult, SOUTH);//adds the difficulty combobox to the southern border
	    add(messages = new JLabel("Welcome to minesweeper"), NORTH);
	    add(timer = new JLabel("0:00"),NORTH);
	    //catchResizeEvents();
		ActionListener buttonlistener = new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				removeAll();
				iAllowYouTo=false;
				setup();
				messages.setText("Good Luck!");
				timer.setText("0:00");
			}

			};
			newGame.addActionListener(buttonlistener);
			

		}
	

	
	public void run()
	{
		while(true)
		{
			pause(1);
			if(keepCounting)
			{
				long totalSeconds= (System.currentTimeMillis() - startTime)/1000;
				long dispSecond = (totalSeconds%60);
				long dispMin =(totalSeconds/60);
				if(dispSecond>9)
				timer.setText(dispMin + ":" + dispSecond);
				else
					timer.setText(dispMin+ ":0" + dispSecond);
			}
				
		}
	}
	public void makeBoard(Difficulty_ diff)
	{
		board = new Board(diff,this);
		add(board);
	}
	
	/**
	 * Reveal a cell on a mouseclick.
	 */
	public void mouseClicked(MouseEvent e) {//make sure to add a block on new game until won or lost
		if(!gameDone){
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
					 board.generate(xLoc, yLoc);
					 newGame.setEnabled(false);
					 startTime = System.currentTimeMillis();
					 keepCounting=true;
				 }
				 turns++;
				 if(!cell.isFlagged)
				 board.revealCell(cell);

				}
			}
		}
			//Cell cell = board.getCellAt(e.getPoint());
		
		}
		if(SwingUtilities.isRightMouseButton(e)&&turns>0){
			Cell cell=null;
			for(int i=0;i<board.getCells().length;i++)
			for(int x=0;x<board.getCells()[i].length;x++)
				if( board.getCells()[i][x].contains(new GPoint(e.getPoint())))
				 cell=board.getCells()[i][x];
			if(!cell.isRevealed())
			board.flagCell(cell);}
		if((board.allFlagged()||board.allRevealed())&&turns>0)
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
		gameDone=true;
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
	private void catchResizeEvents() 
	{
		if(iAllowYouTo){
		wid = getWidth();
		ht = getHeight();

		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				double scaleX = getWidth() / wid,  scaleY = getHeight() / ht;
				for (int i = 0; i < getElementCount(); i++) {
					Object obj = getElement(i);
					if (obj instanceof GObject) {
						if (obj instanceof GScalable) {
							((GScalable) obj).scale(scaleX, scaleY);
						}
						((GObject) obj).setLocation(((GObject) obj).getX()*scaleX, ((GObject) obj).getY()*scaleY);
					}
				}
				wid = getWidth(); ht = getHeight();
			}
		}); }		
	}
	// other declarations go here
	private Board board;
	private int turns=0;
	JButton newGame;
	double wid, ht;
	JLabel messages,timer;
	JComboBox<String> difficult;
	long startTime;
	private final int Y_OFFSET = 85;
	private final int X_OFFSET = 10;
	boolean keepCounting=false,gameDone,iAllowYouTo=false;
}

