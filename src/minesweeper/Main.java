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
	
	//game components
	protected static JFrame gameFrame;
	protected static MinesweeperStatsBar statsBar;
	protected static MinesweeperGrid minefield;
	protected static MinesweeperMenuBar menubar;
	
	public static void changeIsFirstMoveStatus(){
		isFirstMove = (isFirstMove) ? false : true;
	}
	
	public static boolean getIsFirstMoveStatus(){
		return isFirstMove;
	}
	
	
	public static void startGame(){
		/*instantiate game ui*/
		gameFrame = new JFrame();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.getContentPane().setLayout(null);
		gameFrame.setBounds(0,0, 400, 425);
		
		//minesweeper game status bar
		statsBar = new MinesweeperStatsBar("small");
		gameFrame.add(statsBar.getStatsBar());
		
		//minesweeper playfield
		minefield = new MinesweeperGrid("small");
		gameFrame.add(minefield.getMinesweeperGrid());
		
		//size select menu bar
		menubar = new MinesweeperMenuBar();
		gameFrame.setJMenuBar(menubar.getMenuBar());
		
		//show the game
		gameFrame.setVisible(true);
		
		//Board window = new Board();
		//gameFrame = window.getFrame();
		//window.drawBoard();
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
