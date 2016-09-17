package minesweeper;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JPanel;

public class MinesweeperGrid {

	//JPanel to hold the GridSpaces for the game
	private JPanel grid;

	//small grid size
	private final static int SMALL_ROW = 9;
	private final static int SMALL_COL = 9;

	//medium grid size
	private final static int MED_ROW = 16;
	private final static int MED_COL = 16;

	//large grid size
	private final static int LARGE_ROW = 16;
	private final static int LARGE_COL = 30;

	//number of bombs for each size
	private final static int NUM_BOMBS_SMALL = 10;
	private final static int NUM_BOMBS_MED = 40;
	private final static int NUM_BOMBS_LARGE = 99;

	private int numRows;
	private static int numCols;
	private static int numSpacesLeft;
	private int numBombsLeft;
	private int totalBombs;
	private GridSpace gridButtons[][];
	
	//mouse listener for the grid spaces
	private MinesweeperGridMouseListener gridListener;

	public MinesweeperGrid(String size){
		switch(size){
		case "small":
			numRows = SMALL_ROW;
			numCols = SMALL_COL;
			numSpacesLeft = numRows * numCols;
			numBombsLeft = NUM_BOMBS_SMALL;
			totalBombs = numBombsLeft;
			grid = new JPanel();
			grid.setBounds(0, 35, 384, 325);
			grid.setLayout(new GridLayout(SMALL_ROW, SMALL_COL));
			addGridSpaces();
			break;
		case "medium":
			numRows = MED_ROW;
			numCols = MED_COL;
			numSpacesLeft = numRows * numCols;
			numBombsLeft = NUM_BOMBS_MED;
			totalBombs = numBombsLeft;
			grid = new JPanel();
			grid.setBounds(0, 35, 690, 635);
			grid.setLayout(new GridLayout(MED_ROW, MED_COL));
			addGridSpaces();
			break;
		case "large":
			numRows = LARGE_ROW;
			numCols = LARGE_COL;
			numSpacesLeft = numRows * numCols;
			numBombsLeft = NUM_BOMBS_LARGE;
			totalBombs = numBombsLeft;
			grid = new JPanel();
			grid.setBounds(0, 35, 1300, 635);
			grid.setLayout(new GridLayout(LARGE_ROW, LARGE_COL));
			addGridSpaces();
			break;
		default:
			numSpacesLeft = numRows * numCols;
			numBombsLeft = totalBombs;
			grid.setLayout(new GridLayout(numRows, numCols));
			break;
		}
		
		gridListener = new MinesweeperGridMouseListener(gridButtons, numSpacesLeft, totalBombs);
		this.addMouseListener(gridListener);
	}

	private void addGridSpaces(){
		int remainingSpaces = numSpacesLeft;
		int bombs = totalBombs;
		final GridSpace buttons[][] = new GridSpace[numRows][numCols];
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				Random rand = new Random();
				boolean bombHere = false;
				if(bombs > 0){
					if(remainingSpaces == bombs){
						bombHere = true;
					}
					else{
						bombHere = rand.nextFloat() < (float)totalBombs/(float)(numRows*numCols);
					}
					if(bombHere){

						--bombs;
					}
				}
				buttons[i][j] = new GridSpace(bombHere, ((numCols*i)+j));
				buttons[i][j].getJButton().setName(""+((numCols*i)+j));
				grid.add(buttons[i][j].getJButton());
				--remainingSpaces;
			}
		}
		gridButtons = buttons.clone();
	}

	public void addMouseListener(MinesweeperGridMouseListener minesweeperMouseListener){
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				gridButtons[i][j].getJButton().addMouseListener(minesweeperMouseListener);
			}
		}
	}

	public static int numberOfAdjacentBombs(GridSpace[][] board, int coordX, int coordY, int cleared){
		board[coordX][coordY].reveal();
		cleared++;
		board[coordX][coordY].getJButton().setEnabled(false);
		board[coordX][coordY].getJButton().setBackground(Color.white);
		int numBombsTouch = 0;
		for(int i = coordX-1; i <= coordX+1; i++){
			for(int j = coordY-1; j <= coordY+1; j++){
				if(!(i < 0 || i > board.length-1) && !(j < 0 || j > board[0].length-1)){
					if(board[i][j].containsBomb()){
						numBombsTouch++;
					}
				}
			}
		}
		if(numBombsTouch == 0){
			board[coordX][coordY].getJButton().setText("");
			cleared = checkNeighbors(board, coordX, coordY, cleared);
		}
		else{
			board[coordX][coordY].getJButton().setText(String.valueOf(numBombsTouch));
		}
		return cleared;
	}
	
	public static int checkNeighbors(GridSpace[][] board, int coordX, int coordY, int cleared){
		for(int i = coordX-1; i <= coordX+1; i++){
			for(int j = coordY-1; j <= coordY+1; j++){
				if(!(i < 0 || i > board.length-1) && !(j < 0 || j > board[0].length-1)){
					if(!board[i][j].isRevealed()){
						cleared = numberOfAdjacentBombs(board, i, j, cleared);
					}
				}
			}
		}
		return cleared;
	}

	public JPanel getMinesweeperGrid(){
		return this.grid;
	}

	public static int getRow(int gridNum){
		return gridNum/numCols;
	}

	public static int getCol(int gridNum){
		return gridNum%numCols;
	}

	public GridSpace[][] getButtonsArray(){
		return gridButtons;
	}
}
