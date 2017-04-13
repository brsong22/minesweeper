package minesweeper;

/**
 * 
 * @author Richard Song
 * Enum: BoardSizeEnum
 * *This enum defines the sizes of the game
 * 
 * TO DO: add custom size and bomb count
 *
 */
public enum BoardSizeEnum {
	SMALL("small", 9, 9, 10),
	MEDIUM("medium", 16, 16, 40),
	LARGE("large", 16, 30, 99);
	
	private final String name;
	private final int rows;
	private final int cols;
	private final int numBombs;
	
	/**
	 * BoardSizeEnum constructor
	 * 
	 * @param name - String value of the board size
	 * @param rows - number of rows for the board size
	 * @param cols - number of columns for the board size
	 * @param numBombs - number of bombs for the board size
	 */
	private BoardSizeEnum(String name, int rows, int cols, int numBombs){
		this.name = name;
		this.rows = rows;
		this.cols = cols;
		this.numBombs = numBombs;
	}
	
	/**
	 * getName
	 * gets the size of the board
	 * 
	 * @return - size of the board
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * getRows
	 * gets the number of rows of the board size
	 * 
	 * @return - number of rows
	 */
	public int getRows(){
		return rows;
	}
	
	/**
	 * getCols
	 * gets the number of columns of the board size
	 * 
	 * @return - number of columns
	 */
	public int getCols(){
		return cols;
	}
	
	/**
	 * getBombs
	 * gets the number of bombs for the board size
	 * 
	 * @return - number of bombs
	 */
	public int getBombs(){
		return numBombs;
	}
}
