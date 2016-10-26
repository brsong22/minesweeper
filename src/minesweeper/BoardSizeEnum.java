package minesweeper;

public enum BoardSizeEnum {
	SMALL(9, 9, 10),
	MEDIUM(16, 16, 40),
	LARGE(16, 30, 99);
	
	private final int rows;
	private final int cols;
	private final int numBombs;
	
	private BoardSizeEnum(int rows, int cols, int numBombs){
		this.rows = rows;
		this.cols = cols;
		this.numBombs = numBombs;
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getCols(){
		return cols;
	}
	
	public int getBombs(){
		return numBombs;
	}
}
