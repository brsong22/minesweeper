package minesweeper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MinesweeperHiscore {

	private String score;
	
	public MinesweeperHiscore(){
		score = "-1";
		/*check if hiscore file exists*/
		
		File scoreFile = new File("src/minesweeper/hiscore.txt");
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
				score = "0";
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	
	public boolean scoreIsNumber(String s){
		return s.matches("\\d*");
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
