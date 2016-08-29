package minesweeper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MinesweeperHighScore {

	private int highscore;
	private String filePath = "src/minesweeper/hiscore.txt";
	
	public MinesweeperHighScore(){
		File f = new File(filePath);
		if(f.exists()) { 
		    try {
		    	String txtScore;
				BufferedReader b = new BufferedReader(new FileReader(f));
				try {
					if((txtScore = b.readLine()) != null){
						highscore = Integer.parseInt(txtScore);
					}
					else{
						highscore = 0;
					}
					b.close();
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
			File newScore = new File(filePath);
			try {
				newScore.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			highscore = 0;
		}
	}
	
	public void saveHighScore(int score){
		File f = new File(filePath);
		try {
			FileWriter scoreWriter = new FileWriter(f, false);
			scoreWriter.write(score);
			scoreWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		highscore = score;
	}
	
	public int getHighScore(){
		return highscore;
	}
}
