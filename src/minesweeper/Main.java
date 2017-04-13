package minesweeper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	
//	public final static int NUM_ROWS_SMALL = 9;
//	public final static int NUM_COLS_SMALL = 9;
//	public final static int NUM_ROWS_MED = 16;
//	public final static int NUM_COLS_MED = 16;
//	public final static int NUM_ROWS_LARGE = 16;
//	public final static int NUM_COLS_LARGE = 30;
//	public final static int NUM_BOMBS_SMALL = 10;
//	public final static int NUM_BOMBS_MED = 40;
//	public final static int NUM_BOMBS_LARGE = 99;
	
	private static Board board;
	private static boolean isFirstMove = true;
	private static JFrame gameFrame;
	
	public static void changeIsFirstMoveStatus(){
		isFirstMove = (isFirstMove) ? false : true;
	}
	
	public static boolean getIsFirstMoveStatus(){
		return isFirstMove;
	}
	
	public static void setBoard(Board b){
		board = b;
	}
	public static Board getBoard(){
		return board;
	}
	
	public static void setGameFrame(JFrame f){
		gameFrame = f;
	}
	
	public static JFrame getGameFrame(){
		return gameFrame;
	}
	
	public static void startGame(){
		BoardSizeEnum boardSize = BoardSizeEnum.SMALL; //default starting size
		board = new Board(boardSize);
		gameFrame = board.getFrame();
	}
	
	public static void startGame(BoardSizeEnum size){
		board = new Board(size);
		gameFrame = board.getFrame();
	}
	
	public static void gameOver(int winLoss, String endTime){
		StringBuffer endGameText = new StringBuffer("");
		if(winLoss == 1){
			MinesweeperHiscore gameScore = new MinesweeperHiscore(board.getBoardSize());
			gameScore.saveHiscore(endTime);
			endGameText.append("You win! B)");
		}
		else{
			endGameText.append("You lose! X(");
		}
		int gameOver = JOptionPane.showConfirmDialog(gameFrame, endGameText.toString() + "\nTime: " + endTime + " seconds.\nPlay again?", "New Game?", JOptionPane.YES_NO_OPTION);
		if(gameOver == JOptionPane.YES_OPTION){
			gameFrame.dispose();
			changeIsFirstMoveStatus();
			startGame(Board.getBoard().getBoardSize());
		}
		else{
			gameFrame.dispose();
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
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
