package minesweeper;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MinesweeperBot {

	private Board board;
	private MinesweeperTimer timer;
	private ImageIcon flagIcon;
	
	public MinesweeperBot(){
		try{
			Image flagImage = ImageIO.read(getClass().getResource("flag.png"));
	        flagImage = flagImage.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
	        flagIcon = new ImageIcon(flagImage);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void startBot(){
		boolean runBot = true;
		MinesweeperBotActionListener botAction = new MinesweeperBotActionListener();
		Board board = Board.getBoard();
		board.getGridSpace().addBotListener(botAction);
		while(runBot){
			
		}
		GridSpace played = board.getGridSpace().getButtonsArray()[0][0];
//		played.setFlag();
//		played.getJButton().setIcon(flagIcon);
//		played.getJButton().setDisabledIcon(flagIcon);
//		played.getJButton().setEnabled(false);
		played.getJButton().doClick();
	}
}
