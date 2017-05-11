package minesweeper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author Richard Song
 * Class: Main
 * *This class is where the Minesweeper game is run
 *
 */
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
	
	/**
	 * changeIsFirstMoveStatus
	 * changes the isFirstMove property to denote if the move is the first move or not
	 */
	public static void changeIsFirstMoveStatus(){
		isFirstMove = (isFirstMove) ? false : true;
	}
	
	/**
	 * getIsFirstMoveStatus
	 * get whether this is the first move or not
	 * 
	 * @return - boolean denoting if this move is the first or not
	 */
	public static boolean getIsFirstMoveStatus(){
		return isFirstMove;
	}
	
	/**
	 * setBoard
	 * set the board property as a reference to the board object we create
	 * 
	 * @param b - the board we are currently playing on
	 */
	public static void setBoard(Board b){
		board = b;
	}
	
	/**
	 * getBoard
	 * get the board we are currently playing on so we have a reference to necessary information when starting/restarting/ending games
	 * 
	 * @return - board property
	 */
	public static Board getBoard(){
		return board;
	}
	
	/**
	 * setGameFrame
	 * set the gameFrame property so that we have a reference to the gameFrame components
	 * 
	 * @param f - the game's gameFrame which contains the components of the game
	 */
	public static void setGameFrame(JFrame f){
		gameFrame = f;
	}
	
	/**
	 * getGameFrame
	 * get the JFrame object we set as gameFrame
	 * 
	 * @return - gameFrame
	 */
	public static JFrame getGameFrame(){
		return gameFrame;
	}
	
	/**
	 * startGame
	 * start the minesweeper game
	 */
	public static void startGame(){
		BoardSizeEnum boardSize = BoardSizeEnum.SMALL; //default starting size
		board = new Board(boardSize);
		gameFrame = board.getFrame();
	}
	
	/**
	 * startGame overloaded function
	 * start the game with specified size
	 * 
	 * @param size - size of the game we would like to play
	 */
	public static void startGame(BoardSizeEnum size){
		board = new Board(size);
		gameFrame = board.getFrame();
	}
	
	/**
	 * gameOver
	 * end the game
	 * 
	 * @param winLoss - status of whether we won or lost
	 * @param endTime - time it took to end the game (win or lose)
	 */
	public static void gameOver(int winLoss, String endTime){
		StringBuffer endGameText = new StringBuffer(""); //building our end game message
		if(winLoss == 1){
			//we won
			//save the time (score)
			MinesweeperHiscore gameScore = new MinesweeperHiscore(board.getBoardSize());
			gameScore.saveHiscore(endTime);
			endGameText.append("You win! B)");
		}
		else{
			//we lost
			endGameText.append("You lose! X(");
		}
		//popup gameover window prompt
		int gameOver = JOptionPane.showConfirmDialog(gameFrame, endGameText.toString() + "\nTime: " + endTime + " seconds.\nPlay again?", "New Game?", JOptionPane.YES_NO_OPTION);
		if(gameOver == JOptionPane.YES_OPTION){
			//play again
			gameFrame.dispose();
			changeIsFirstMoveStatus();
			startGame(Board.getBoard().getBoardSize());
		}
		else{
			//exit the game
			gameFrame.dispose();
			System.exit(0);
		}
	}
	
	/**
	 * main
	 * main function to run the program
	 * 
	 * @param args - empty array; no args are needed
	 */
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
