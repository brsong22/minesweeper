package minesweeper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	
	public final static int NUM_ROWS_SMALL = 9;
	public final static int NUM_COLS_SMALL = 9;
	public final static int NUM_ROWS_MED = 16;
	public final static int NUM_COLS_MED = 16;
	public final static int NUM_ROWS_LARGE = 16;
	public final static int NUM_COLS_LARGE = 30;
	public final static int NUM_BOMBS_SMALL = 10;
	public final static int NUM_BOMBS_MED = 40;
	public final static int NUM_BOMBS_LARGE = 99;
	
	private static boolean isFirstMove = true;
	private static JFrame gameFrame;
	
	public static void changeIsFirstMoveStatus(){
		isFirstMove = (isFirstMove) ? false : true;
	}
	
	public static boolean getIsFirstMoveStatus(){
		return isFirstMove;
	}
	
	public static void startGame(){
		Board window = new Board();
		gameFrame = window.getFrame();
		window.drawBoard();
	}
	
	public static void gameOver(int winLoss, String endTime){
		StringBuffer endGameText = new StringBuffer("");
		if(winLoss == 1){
			endGameText.append("You win! B)");
		}
		else{
			endGameText.append("You lose! X(");
		}
		int gameOver = JOptionPane.showConfirmDialog(gameFrame, endGameText.toString() + "\nTime: " + endTime + " seconds.", "New Game?", JOptionPane.YES_NO_OPTION);
		if(gameOver == JOptionPane.YES_OPTION){
			changeIsFirstMoveStatus();
			gameFrame.setVisible(false);
			gameFrame.dispose();
			startGame();
		}
		else{
			gameFrame.dispose();
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					startGame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
