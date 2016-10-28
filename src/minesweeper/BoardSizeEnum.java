package minesweeper;

public enum BoardSizeEnum {
	SMALL("small", 9, 9, 10),
	MEDIUM("medium", 16, 16, 40),
	LARGE("large", 16, 30, 99);
	
	private final String name;
	private final int rows;
	private final int cols;
	private final int numBombs;
	
	private BoardSizeEnum(String name, int rows, int cols, int numBombs){
		this.name = name;
		this.rows = rows;
		this.cols = cols;
		this.numBombs = numBombs;
	}
	
	public String getName(){
		return name;
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
