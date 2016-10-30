package minesweeper;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
	private MinesweeperResetButtonMouseListener reset;
	private static MinesweeperGrid minefield;
	private GridSpace[][] gridSpaces;
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
	 * Create the application.
	 */
	public Board(BoardSizeEnum s) {

		/*board properties*/
		this.boardSize = s;
		bombsLabel = new JLabel();
		timeLabel = new JLabel();
		timeCopy = timeLabel;
		
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
		
		statsBar = new MinesweeperStatsBar(s, bombsLabel, timeLabel);
		reset = new MinesweeperResetButtonMouseListener(gameTime, this);
		statsBar.getStatsButton().addMouseActionListener(reset);
		
		minefield = new MinesweeperGrid(s.getName());
		gridSpaces = minefield.getButtonsArray();
		gridListener = new MinesweeperGridMouseListener(gridSpaces, numSpacesLeft, totalBombs, statsBar);
		minefield.addMouseListener(gridListener);
		gridListener.addGameTimerListener(gameTime);
		
		
		frame = new JFrame();
		frame.setVisible(true);
		frame.setJMenuBar(menuBar.getMenuBar());

		frame.getContentPane().add(statsBar.getStatsBar());

		frame.getContentPane().add(minefield.getMinesweeperGrid());		
		
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

	public void drawBoard(){
		frame.validate();
		frame.repaint();
	}

	public JFrame getFrame(){
		return frame;
	}

	public void resetBoard(BoardSizeEnum size){
		frame.dispose();
		new Board(size);
	}

	public void resizeBoard(BoardSizeEnum size){
		frame.getContentPane().remove(minefield.getMinesweeperGrid());
		frame.getContentPane().remove(statsBar.getStatsBar());
		minefield = new MinesweeperGrid(size.getName());
		statsBar = new MinesweeperStatsBar(size, bombsLabel, timeLabel);
		drawBoard();
		Main.changeIsFirstMoveStatus();
//		lastSize = size;
	}
	
	public static void endGame(int endStatus){
		String endTime = timeCopy.getText();
		Main.gameOver(endStatus, endTime);
	}
	
	public MinesweeperGrid getGridSpace(){
		return minefield;
	}
	
	public MinesweeperMenuBar getMenuBar(){
		return menuBar;
	}
	
	public BoardSizeEnum getBoardSize(){
		return boardSize;
	}
	
	public static Board getBoard(){
		return boardCopy;
	}
}
