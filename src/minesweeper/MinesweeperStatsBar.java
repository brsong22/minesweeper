package minesweeper;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MinesweeperStatsBar {
	
	private JPanel stats;
	private MinesweeperStatusButton gameStatusButton;
	private JLabel bombsLabel;
	private JLabel timeLabel;
	private JLabel hiscoreLabel;
	private MinesweeperHiscore hiScore;
	private int numberOfBombs;
	
	public MinesweeperStatsBar(BoardSizeEnum size, JLabel bLabel, JLabel tLabel, JLabel sLabel){
		stats = new JPanel();
		stats.setLayout(null);
		gameStatusButton = new MinesweeperStatusButton();
		
		//set label properties
		this.bombsLabel = bLabel;
		this.bombsLabel.setOpaque(true);
		this.bombsLabel.setBackground(Color.WHITE);
		this.timeLabel = tLabel;
		this.timeLabel.setOpaque(true);
		this.timeLabel.setBackground(Color.WHITE);
		this.timeLabel.setText("0");
		this.hiscoreLabel = sLabel;
		this.hiscoreLabel.setOpaque(true);
		this.hiscoreLabel.setBackground(Color.WHITE);
		
		hiScore = new MinesweeperHiscore(size);
		hiScore.setHiscore();
		hiscoreLabel.setText(hiScore.getScore());
			
		switch(size){
			case SMALL:
				bombsLabel.setText("" + size.getBombs());
				numberOfBombs = size.getBombs();
				stats.setBounds(0, 0, 384, 35);
				gameStatusButton.getStatusButton().setBounds(171, 0, 35, 35);
				bombsLabel.setBounds(48, 0, 66, 26);
				timeLabel.setBounds(225, 0, 66, 26);
				hiscoreLabel.setBounds(300, 0, 66, 26);
				stats.add(bombsLabel);
				stats.add(gameStatusButton.getStatusButton());
				stats.add(timeLabel);
				stats.add(hiscoreLabel);
				break;
			case MEDIUM:
				bombsLabel.setText("" + size.getBombs());
				numberOfBombs = size.getBombs();
				stats.setBounds(0, 0, 690, 35);
				gameStatusButton.getStatusButton().setBounds(328, 0, 35, 35);
				bombsLabel.setBounds(84, 0, 66, 26);
				timeLabel.setBounds(450, 0, 66, 26);
				hiscoreLabel.setBounds(550, 0, 66, 26);
				stats.add(gameStatusButton.getStatusButton());
				stats.add(bombsLabel);
				stats.add(timeLabel);
				stats.add(hiscoreLabel);
				break;
			case LARGE:
				bombsLabel.setText("" + size.getBombs());
				numberOfBombs = size.getBombs();
				stats.setBounds(0, 0, 1300, 35);
				gameStatusButton.getStatusButton().setBounds(633, 0, 35, 35);
				bombsLabel.setBounds(150, 0, 66, 26);
				timeLabel.setBounds(830, 0, 66, 26);
				hiscoreLabel.setBounds(1000, 0, 66, 26);
				stats.add(gameStatusButton.getStatusButton());
				stats.add(bombsLabel);
				stats.add(timeLabel);
				stats.add(hiscoreLabel);
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
	
	public JLabel getHiscoreLabel(){
		return hiscoreLabel;
	}
}
