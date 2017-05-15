package minesweeper;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author Richard Song
 * Class: Board
 * *This class contains the game components of minesweeper
 *
 */
public class Board{

	private static JLabel timeCopy;
	private static Board boardCopy;
	protected JFrame frame;
	
	/*Board Components*/
	private MinesweeperMenuBar menuBar;
	private MinesweeperStatsBar statsBar;
	private MinesweeperTimer gameTime;
	private JLabel bombsLabel;
	private JLabel timeLabel;
	private JLabel hiscoreLabel;
	private MinesweeperResetButtonMouseListener reset;
	private static MinesweeperGrid minefield;
	private GridSpace gridSpaces[][];
	private MinesweeperGridMouseListener gridListener;
	
	/*Board Properties*/
	private BoardSizeEnum boardSize = BoardSizeEnum.SMALL; //default board size
//	private BoardSizeEnum lastSize;
	private int numRows;
	private int numCols;
	private int totalSpaces;
	private int numSpacesLeft;
	private int totalBombs;


	/**
	 * Board constructor
	 * 
	 * @param s - size of the board we are creating
	 */
	public Board(BoardSizeEnum s) {

		/*board properties*/
		/*because of cyclical dependencies I create a new instance of 
		 * each components' class and initialize the Board component variables
		 * so we can have a reference for setting values to these components
		 * as the game is played.*/
		this.boardSize = s;
		bombsLabel = new JLabel();
		timeLabel = new JLabel();
		timeCopy = timeLabel;
		hiscoreLabel = new JLabel();
		
		numRows = boardSize.getRows();
		numCols = boardSize.getCols();
		totalSpaces = numRows * numCols;
		numSpacesLeft = totalSpaces;
		totalBombs = boardSize.getBombs();
		
		MinesweeperTimerActionListener timerActionListener = new MinesweeperTimerActionListener(timeLabel);
		gameTime = new MinesweeperTimer(1000, timerActionListener, timeLabel);
		menuBar = new MinesweeperMenuBar(s.getName());
		MinesweeperMenuBarActionListener sizeSelected = new MinesweeperMenuBarActionListener(this, gameTime);
		MinesweeperMenuBar.addActionListener(sizeSelected);
		
		statsBar = new MinesweeperStatsBar(s, bombsLabel, timeLabel, hiscoreLabel);
		reset = new MinesweeperResetButtonMouseListener(gameTime, this);
		statsBar.getStatsButton().addMouseActionListener(reset);
		
		minefield = new MinesweeperGrid(s);
		gridSpaces = minefield.getButtonsArray();
		gridListener = new MinesweeperGridMouseListener(gridSpaces, numSpacesLeft, totalBombs, statsBar);
		minefield.addMouseListener(gridListener);
		gridListener.addGameTimerListener(gameTime);
		
		
		frame = new JFrame();
		frame.setVisible(true);
		frame.setJMenuBar(menuBar.getMenuBar());

		frame.getContentPane().add(statsBar.getStatsBar());

		frame.getContentPane().add(minefield.getMinesweeperGrid());
		Main.setGameFrame(frame);
		
		switch(boardSize){
			case SMALL:
				this.boardSize = BoardSizeEnum.SMALL;
				frame.setBounds(0, 0, 400, 425);
				frame.setSize(400, 425);
				break;
			case MEDIUM:
				this.boardSize = BoardSizeEnum.MEDIUM;
				frame.setBounds(0, 0, 707, 735);
				frame.setSize(707, 735);
				break;
			case LARGE:
				this.boardSize = BoardSizeEnum.LARGE;
				frame.setBounds(0, 0, 1315, 735);
				frame.setSize(1315, 735);
				break;
			default:
				this.boardSize = BoardSizeEnum.SMALL;
				frame.setBounds(0, 0, 400, 425);
				frame.setSize(400, 425);
				break;
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		boardCopy = this;
		drawBoard();
	}

	/**
	 * drawBoard
	 * draw the GUI frame and components so we can see the game
	 */
	public void drawBoard(){
		frame.validate();
		frame.repaint();
	}

	/**
	 * getFrame
	 * return the GUI frame so we can manipulate it through menu options
	 * i.e. starting new game or resizing the game board
	 * 
	 * @return - GUI frame
	 */
	public JFrame getFrame(){
		return frame;
	}
	
	/**
	 * getMinefield
	 * return the minefield of the board
	 * 
	 * @return - minefield
	 */
	public static MinesweeperGrid getMinefield(){
		return minefield;
	}

	/**
	 * resetBoard
	 * creates a new board to simulate starting a new game
	 * called whenever a game is won/lost or when the board is resized
	 * 
	 * @param size - the size of the new game
	 */
	public void resetBoard(BoardSizeEnum size){
		frame.dispose();
		Main.changeIsFirstMoveStatus();
		new Board(size);
	}
	
	/**
	 * endGame
	 * gather the necessary game information when the game is over
	 * 
	 * @param endStatus - status of whether game was won or lost
	 */
	public static void endGame(int endStatus){
		//getting the gameplay time so we can display it in the game over window
		String endTime = timeCopy.getText();
		Main.gameOver(endStatus, endTime);
	}
	
	/**
	 * getGridSpace
	 * gets the grid space of the GUI
	 * 
	 * @return - minefield grid
	 */
	public MinesweeperGrid getGridSpace(){
		return minefield;
	}
	
	/**
	 * getMenuBar
	 * gets the menu bar of the GUI
	 * 
	 * @return - game menu bar
	 */
	public MinesweeperMenuBar getMenuBar(){
		return menuBar;
	}
	
	/**
	 * getBoardSize
	 * returns the size of the current game
	 * 
	 * @return - size of the game
	 */
	public BoardSizeEnum getBoardSize(){
		return boardSize;
	}
	
	/**
	 * getBoard
	 * returns a copy of the current board
	 * this is called from Main when the game is over so that we can accurately obtain
	 * 	the previous game size when restarting a game
	 * 
	 * @return - copy of the board that was just played
	 */
	public static Board getBoard(){
		return boardCopy;
	}
}
