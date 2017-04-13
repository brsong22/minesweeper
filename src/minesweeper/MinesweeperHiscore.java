package minesweeper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MinesweeperHiscore {

	private String score;
	private File scoreFile;
	private BoardSizeEnum boardSize;
	
	public MinesweeperHiscore(BoardSizeEnum size){
		boardSize = size;
		score = "1";
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
		/*check if hiscore file exists*/
	}
	
	public boolean scoreIsNumber(String s){
		return s.matches("\\d*");
	}
	
	/**
	 * setHiscore
	 * set the score variable of the MinesweeperHiscore object
	 * read from hiscore.txt and find the fastest time recorded
	 */
	public void setHiscore(){
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
	
	public String getScore(){
		System.out.println("score: "+score);
		try {
		    Integer.parseInt(score);
		}
		catch (NumberFormatException e) {
			score = "error";	
		}
		return score;
	}
}
