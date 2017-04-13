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
	
	public MinesweeperHiscore(){
		score = "-1";
		scoreFile = new File("src/minesweeper/hiscore.txt");
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
				while((recentScore = hiscoreReader.readLine()) != null){
					if(scoreIsNumber(recentScore)){
						if(Integer.valueOf(recentScore) < Integer.valueOf(score) || score.equals("-1")){
							score = recentScore;
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
			hiscoreWriter.newLine();
			hiscoreWriter.write(score);
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
