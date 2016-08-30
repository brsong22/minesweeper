package minesweeper;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MinesweeperStatsBar {
	
	private JPanel stats;
	private MinesweeperStatusButton gameStatusButton;
	private JLabel bombsLabel;
	private JLabel timeLabel;
	MinesweeperTimerActionListener timerActionListener;
	MinesweeperTimer gameTime;
	private int numberOfBombs;
	
	public MinesweeperStatsBar(String size){
		stats = new JPanel();
		stats.setBounds(0, 0, 384, 35);
		stats.setLayout(null);
		gameStatusButton = new MinesweeperStatusButton();
		this.bombsLabel = new JLabel();
		this.timeLabel = new JLabel();
		this.timeLabel.setText("0");
		timerActionListener = new MinesweeperTimerActionListener(timeLabel);
		gameTime = new MinesweeperTimer(1000, timerActionListener, timeLabel);
		
		switch(size){
			case "small":
				bombsLabel.setText("" + Main.NUM_BOMBS_SMALL);
				numberOfBombs = Main.NUM_BOMBS_SMALL;
				stats.setBounds(0, 0, 384, 35);
				gameStatusButton.getStatusButton().setBounds(171, 0, 35, 35);
				bombsLabel.setBounds(48, 0, 66, 26);
				timeLabel.setBounds(277, 0, 66, 26);
				stats.add(bombsLabel);
				stats.add(gameStatusButton.getStatusButton());
				stats.add(timeLabel);
				
				break;
			case "medium":
				bombsLabel.setText("" + Main.NUM_BOMBS_MED);
				numberOfBombs = Main.NUM_BOMBS_MED;
				stats.setBounds(0, 0, 690, 35);
				gameStatusButton.getStatusButton().setBounds(328, 0, 35, 35);
				bombsLabel.setBounds(84, 0, 66, 26);
				timeLabel.setBounds(510, 0, 66, 26);
				stats.add(gameStatusButton.getStatusButton());
				stats.add(bombsLabel);
				stats.add(timeLabel);
				break;
			case "large":
				bombsLabel.setText("" + Main.NUM_BOMBS_LARGE);
				numberOfBombs = Main.NUM_BOMBS_LARGE;
				gameStatusButton.getStatusButton().setBounds(633, 0, 35, 35);
				bombsLabel.setBounds(150, 0, 66, 26);
				timeLabel.setBounds(987, 0, 66, 26);
				stats.add(gameStatusButton.getStatusButton());
				stats.add(bombsLabel);
				stats.add(timeLabel);
				stats.setBounds(0, 0, 1300, 35);
				break;
			default:
				bombsLabel.setText("" + numberOfBombs);
				stats.add(gameStatusButton.getStatusButton());
				stats.add(bombsLabel);
				stats.add(timeLabel);
				break;
		}
	}

	public JPanel getStatsBar(){
		return stats;
	}
	
	public MinesweeperStatusButton getStatsButton(){
		return gameStatusButton;
	}
	
	public JLabel getBombLabel(){
		return bombsLabel;
	}
	
	public JLabel getTimeLabel(){
		return timeLabel;
	}
}
