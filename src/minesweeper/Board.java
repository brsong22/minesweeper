package minesweeper;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Board{

	private static JLabel timeCopy;
	protected JFrame frame;
	private MinesweeperGrid minefield;
	private MinesweeperStatsBar statsBar;
	private GridSpace[][] gridSpaces;
	private MinesweeperGridMouseListener gridListener;
	private MinesweeperResetButtonMouseListener reset;

	private String lastSize;
	private int numRows;
	private int numCols;
	private int totalSpaces;
	private int numSpacesLeft;
	private int totalBombs;


	/**
	 * Create the application.
	 */
	public Board() {

		numRows = Main.NUM_ROWS_SMALL;
		numCols = Main.NUM_ROWS_SMALL;
		totalSpaces = numRows * numCols;
		numSpacesLeft = totalSpaces;
		totalBombs = Main.NUM_BOMBS_SMALL;

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setVisible(true);

		final MinesweeperMenuBar menubar = new MinesweeperMenuBar();
		//move these action listeners to be instantiated in the menubar class
		MinesweeperMenuBarActionListener sizeSelected = new MinesweeperMenuBarActionListener(menubar, this, gameTime);
		MinesweeperMenuBar.addActionListener(sizeSelected);
		frame.setJMenuBar(menubar.getMenuBar());

		statsBar = new MinesweeperStatsBar("small");
		//move these action listeners to be instantiated in the statsbar class
		reset = new MinesweeperResetButtonMouseListener(gameTime, this);
		statsBar.getStatsButton().addMouseActionListener(reset);
		frame.getContentPane().add(statsBar.getStatsBar());

		minefield = new MinesweeperGrid("small");	//default starting size
		gridSpaces = minefield.getButtonsArray();
		//add these action listeners to be instantiated in the mouselistener class
		gridListener = new MinesweeperGridMouseListener(gridSpaces, numSpacesLeft, totalBombs, statsBar);
		minefield.addMouseListener(gridListener);
		gridListener.addGameTimerListener(gameTime);
		frame.getContentPane().add(minefield.getMinesweeperGrid());

		frame.setBounds(0, 0, 400, 425);
		frame.setSize(400, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lastSize = "small";
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

	public void resizeBoard(String size){

		frame.getContentPane().removeAll();

		
		//move listeners to appropriate classes
		statsBar = new MinesweeperStatsBar(size, bombsLabel, timeLabel);
		reset = new MinesweeperResetButtonMouseListener(gameTime, this);
		statsBar.getStatsButton().addMouseActionListener(reset);
		
		minefield = new MinesweeperGrid(size);
		gridSpaces = minefield.getButtonsArray();
		gridListener = new MinesweeperGridMouseListener(gridSpaces, totalSpaces, totalBombs, statsBar);
		minefield.addMouseListener(gridListener);
		gridListener.addGameTimerListener(gameTime);

		frame.getContentPane().add(minefield.getMinesweeperGrid());
		frame.getContentPane().add(statsBar.getStatsBar());
		Main.changeIsFirstMoveStatus();
		switch(size){
		case "small":
			frame.setBounds(0, 0, 400, 425);
			frame.setSize(400, 425);
			drawBoard();
			break;
		case "medium":
			frame.setBounds(0, 0, 707, 735);
			frame.setSize(707, 735);
			drawBoard();
			break;
		case "large":
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
}
