package minesweeper;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Board{

	private static JLabel timeCopy;
	protected JFrame frame;
	
	/*Board Components*/
	private MinesweeperMenuBar menuBar;
	private MinesweeperStatsBar statsBar;
	private MinesweeperTimer gameTime;
	private JLabel bombsLabel;
	private JLabel timeLabel;
	private MinesweeperResetButtonMouseListener reset;
	private MinesweeperGrid minefield;
	private GridSpace[][] gridSpaces;
	private MinesweeperGridMouseListener gridListener;
	
	/*Board Properties*/
	private BoardSizeEnum boardSize;
	private BoardSizeEnum lastSize;
	private int numRows;
	private int numCols;
	private int totalSpaces;
	private int numSpacesLeft;
	private int totalBombs;


	/**
	 * Create the application.
	 */
	public Board(BoardSizeEnum s) {

		this.boardSize = s;
		bombsLabel = new JLabel();
		timeLabel = new JLabel();
		timeCopy = timeLabel;
		MinesweeperTimerActionListener timerActionListener = new MinesweeperTimerActionListener(timeLabel);
		gameTime = new MinesweeperTimer(1000, timerActionListener, timeLabel);
		numRows = boardSize.getRows();
		numCols = boardSize.getCols();
		totalSpaces = numRows * numCols;
		numSpacesLeft = totalSpaces;
		totalBombs = boardSize.getBombs();

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setVisible(true);

		this.menuBar = new MinesweeperMenuBar();
		MinesweeperMenuBarActionListener sizeSelected = new MinesweeperMenuBarActionListener(menuBar, this, gameTime);
		MinesweeperMenuBar.addActionListener(sizeSelected);
		frame.setJMenuBar(menuBar.getMenuBar());

		statsBar = new MinesweeperStatsBar("small", bombsLabel, timeLabel);
		reset = new MinesweeperResetButtonMouseListener(gameTime, this);
		statsBar.getStatsButton().addMouseActionListener(reset);
		frame.getContentPane().add(statsBar.getStatsBar());

		minefield = new MinesweeperGrid("small");	//default starting size
		gridSpaces = minefield.getButtonsArray();
		gridListener = new MinesweeperGridMouseListener(gridSpaces, numSpacesLeft, totalBombs, statsBar);
		minefield.addMouseListener(gridListener);
		gridListener.addGameTimerListener(gameTime);
		frame.getContentPane().add(minefield.getMinesweeperGrid());

		frame.setBounds(0, 0, 400, 425);
		frame.setSize(400, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lastSize = BoardSizeEnum.SMALL;
	}

	public void drawBoard(){
		frame.validate();
		frame.repaint();
	}

	public JFrame getFrame(){
		return frame;
	}

	public void resetBoard(){
		resizeBoard(lastSize);
	}

	public void resizeBoard(BoardSizeEnum size){
		frame.getContentPane().removeAll();

		statsBar = new MinesweeperStatsBar(size.getName(), bombsLabel, timeLabel);
		reset = new MinesweeperResetButtonMouseListener(gameTime, this);
		statsBar.getStatsButton().addMouseActionListener(reset);
		
		System.out.println(size.getName());
		minefield = new MinesweeperGrid(size.getName());
		gridSpaces = minefield.getButtonsArray();
		gridListener = new MinesweeperGridMouseListener(gridSpaces, totalSpaces, totalBombs, statsBar);
		minefield.addMouseListener(gridListener);
		gridListener.addGameTimerListener(gameTime);

		frame.getContentPane().add(minefield.getMinesweeperGrid());
		frame.getContentPane().add(statsBar.getStatsBar());
		Main.changeIsFirstMoveStatus();
		switch(size){
			case SMALL:
				this.boardSize = BoardSizeEnum.SMALL;
				frame.setBounds(0, 0, 400, 425);
				frame.setSize(400, 425);
				drawBoard();
				break;
			case MEDIUM:
				this.boardSize = BoardSizeEnum.MEDIUM;
				frame.setBounds(0, 0, 707, 735);
				frame.setSize(707, 735);
				drawBoard();
				break;
			case LARGE:
				this.boardSize = BoardSizeEnum.LARGE;
				frame.setBounds(0, 0, 1315, 735);
				frame.setSize(1315, 735);
				drawBoard();
				break;
			default:
				drawBoard();	
				break;
		}
		lastSize = size;
	}
	
	public static void endGame(int endStatus){
		String endTime = timeCopy.getText();
		Main.gameOver(endStatus, endTime);
	}
	
	public MinesweeperGrid getGridSpace(){
		return this.minefield;
	}
	
	public MinesweeperMenuBar getMenuBar(){
		return this.menuBar;
	}
}
