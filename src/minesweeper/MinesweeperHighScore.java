package minesweeper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MinesweeperHighScore {

	private int highscore;
	private String filePath = "/minesweeper/src/minesweeper/hiscore.txt";
	
	public MinesweeperHighScore(){
		File f = new File(filePath);
		if(f.exists()) { 
		    try {
		    	String txtScore;
				BufferedReader b = new BufferedReader(new FileReader(f));
				try {
					txtScore = b.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			
		}
	}
}
