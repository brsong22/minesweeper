package minesweeper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Richard Song
 * Class: MinesweeperHiscore
 * *This class handles reading and writing high scores.
 *
 */
public class MinesweeperHiscore {

	private String score; 				//the score values read in from hiscore1/2/3.txt
	private File scoreFile; 			//the hiscore file we will read from.
	private BoardSizeEnum boardSize; 	//the board size to determine which file we will read/write.
	
	public MinesweeperHiscore(BoardSizeEnum size){
		boardSize = size;	//set the board size so we know which file to read/write.
		score = "-1";		//default the score to -1 since lower scores are better but we need to do a < comparison and scores must be > 0.
		switch(boardSize){
			case SMALL:
				scoreFile = new File("src/minesweeper/hiscore1.txt");
				break;
			case MEDIUM:
				scoreFile = new File("src/minesweeper/hiscore2.txt");
				break;
			case LARGE:
				scoreFile = new File("src/minesweeper/hiscore3.txt");
				break;
			default:
				scoreFile = new File("src/minesweeper/hiscore1.txt");
				break;
		}
	}
	
	/**
	 * scoreIsNumber
	 * regex to check if the input string represents an integer.
	 * 
	 * @param s - string we are validating
	 * @return - boolean if string represents integer or not
	 */
	public boolean scoreIsNumber(String s){
		return s.matches("\\d*");
	}
	
	/**
	 * readHiscore
	 * read the times (scores) from scoreFile
	 */
	public void readHiscore(){
		try {
			if(!scoreFile.createNewFile()){
				//the file exists. read in existing high score
				BufferedReader hiscoreReader = new BufferedReader(new FileReader(scoreFile));
				String recentScore = score;
				if((recentScore = hiscoreReader.readLine()) == null){
					//hiscore exists but file is empty
					score = "0";
				}
				else{
					//if hiscore.txt is not empty, set score since we ingested first line of file already.
					score = recentScore;
					while((recentScore = hiscoreReader.readLine()) != null){
						if(scoreIsNumber(recentScore)){
							if(Integer.valueOf(recentScore) < Integer.valueOf(score) || score.equals("-1")){
								score = recentScore;
							}
						}
					}
				}
				hiscoreReader.close();
			}
			else{
				//file did not exist. no high score existed. set score to 0.
				score = "0";
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	
	/**
	 * saveHiscore
	 * append the time (score) of a won game to scoreFile
	 * 
	 * @param score - the time it took to win
	 */
	public void saveHiscore(String score){
		try {
			BufferedWriter hiscoreWriter = new BufferedWriter(new FileWriter(scoreFile, true));
			hiscoreWriter.write(score);
			hiscoreWriter.newLine();
			hiscoreWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * getScore
	 * return score property of MinesweeperHiscore object
	 * 
	 * @return - score property
	 */
	public String getScore(){
		try {
			//make sure we have set score to represent a valid integer.
		    Integer.parseInt(score);
		}
		catch (NumberFormatException e) {
			score = "error";	
		}
		return score;
	}
}
