package minesweeper;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * @author Richard Song
 * Class: MinesweeperGrid
 * *This class represents the overall collection of GridSpaces
 *
 */
public class MinesweeperGrid {

	//JPanel to hold the GridSpaces for the game
	private JPanel grid;

	private int numRows;
	private static int numCols;
	private static int numSpacesLeft;
	private int numBombsLeft;
	private int totalBombs;
	private GridSpace gridButtons[][];

	public MinesweeperGrid(BoardSizeEnum size){
		numRows = size.getRows();
		numCols = size.getCols();
		numSpacesLeft = numRows * numCols;
		numBombsLeft = size.getBombs();
		totalBombs = numBombsLeft;
		grid = new JPanel();
		grid.setLayout(new GridLayout(numRows, numCols));
		switch(size){
		case SMALL:
			grid.setBounds(0, 35, 384, 325);
			break;
		case MEDIUM:
			grid.setBounds(0, 35, 690, 635);
			break;
		case LARGE:
			grid.setBounds(0, 35, 1300, 635);
			break;
		default:
			grid.setBounds(0, 35, 384, 325);
			break;
		}
		addGridSpaces();
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
	
	public void addBotListener(MinesweeperBotActionListener botAction){
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				JButton btn = gridButtons[i][j].getJButton();
				btn.addActionListener(botAction);
				MouseListener[] mlisteners = btn.getMouseListeners();
				for(MouseListener ml : mlisteners){
					btn.removeMouseListener(ml);
				}
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
