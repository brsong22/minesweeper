package minesweeper;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MinesweeperGridMouseListener implements MouseListener{

	private JButton pressedButton;
	private GridSpace buttons[][];
	private int numSpacesLeft;
	private int numBombsLeft;
	private MinesweeperStatusButton statusButton;
	private JLabel bombsLabel;
	private MinesweeperTimer gameTime;

	private ImageIcon flagIcon;
	
	public MinesweeperGridMouseListener(GridSpace[][] buttonsArray, int numSpaces, int numBombs, MinesweeperStatsBar statsBar){
		try{
			Image flagImage = ImageIO.read(getClass().getResource("flag.png"));
	        flagImage = flagImage.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
	        flagIcon = new ImageIcon(flagImage);
		}catch (Exception e){
			e.printStackTrace();
		}
		buttons = buttonsArray.clone();
		numSpacesLeft = numSpaces;
		numBombsLeft = numBombs;
		statusButton = statsBar.getStatsButton();
		bombsLabel = statsBar.getBombLabel();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {
		int pressedID = Integer.parseInt(((JButton)e.getSource()).getName());
		int buttonRow = MinesweeperGrid.getRow(pressedID);
		int buttonCol = MinesweeperGrid.getCol(pressedID);
		GridSpace exited = buttons[buttonRow][buttonCol];
		if(exited.getJButton() == pressedButton){
			pressedButton = null;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int pressedID = Integer.parseInt(((JButton)e.getSource()).getName());
		int buttonRow = MinesweeperGrid.getRow(pressedID);
		int buttonCol = MinesweeperGrid.getCol(pressedID);
		GridSpace pressed = buttons[buttonRow][buttonCol];
		pressedButton = pressed.getJButton();
		if(e.getButton() == MouseEvent.BUTTON1){
			statusButton.changeStatusIcon(1);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int pressedID = Integer.parseInt(((JButton)e.getSource()).getName());
		int buttonRow = MinesweeperGrid.getRow(pressedID);
		int buttonCol = MinesweeperGrid.getCol(pressedID);
		GridSpace played = buttons[buttonRow][buttonCol];
		if(Main.getIsFirstMoveStatus()){
			//first move should always be valid
			//if first move contains bomb, relocate the bomb.
			if(played.containsBomb()){
				played.setBomb(false);
				outerloop: //loop label to allow for breaking out of nested loop easily.
				for(GridSpace[] g : Board.getMinefield().getButtonsArray()){
					for(GridSpace gs : g){
						if(!gs.containsBomb()){
							gs.setBomb(true);
							break outerloop; //we've successfully relocated the bomb into an empty space. continue play.
						}
					}
				}
			}
			gameTime.start();
			Main.changeIsFirstMoveStatus();
		}
		if(pressedButton == played.getJButton() && !played.isRevealed()){	
			if(e.getButton() == MouseEvent.BUTTON1){
				if(!played.getFlagStatus()){
					if(played.containsBomb() && !Main.getIsFirstMoveStatus()){
						gameTime.stop();
						statusButton.changeStatusIcon(3);
						played.getJButton().setEnabled(false);
						played.reveal();
						played.getJButton().setBackground(Color.red);
						Board.endGame(2);
					}
					else{
						numSpacesLeft -= MinesweeperGrid.numberOfAdjacentBombs(buttons, buttonRow, buttonCol, 0);
						if(numSpacesLeft == 0 && numBombsLeft == 0){
							gameTime.stop();
							statusButton.changeStatusIcon(2);
							Board.endGame(1);
						}
						statusButton.changeStatusIcon(0);
					}
				}
			}
			else if(e.getButton() == MouseEvent.BUTTON3){
				if(!played.isRevealed() && !played.getFlagStatus()){
					played.setFlag();
					played.getJButton().setIcon(flagIcon);
					played.getJButton().setDisabledIcon(flagIcon);
					played.getJButton().setEnabled(false);
					--numSpacesLeft;
					--numBombsLeft;
					bombsLabel.setText("" + numBombsLeft);
					if(numSpacesLeft == 0 && numBombsLeft == 0){
						gameTime.stop();
						statusButton.changeStatusIcon(2);
						Board.endGame(1);
					}
				}
				else if(!played.isRevealed() && played.getFlagStatus()){
					played.setFlag();
					played.getJButton().setIcon(null);
					played.getJButton().setDisabledIcon(null);
					played.getJButton().setEnabled(true);
					++numSpacesLeft;
					++numBombsLeft;
					bombsLabel.setText("" + numBombsLeft);
				}
			}
		}
		else{
			statusButton.changeStatusIcon(0);
		}
	}
	
	public void addGameTimerListener(MinesweeperTimer timer){
		gameTime = timer;
	}
	
	public MinesweeperTimer getTimer(){
		return gameTime;
	}
}
