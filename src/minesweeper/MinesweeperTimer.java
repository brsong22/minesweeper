package minesweeper;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class MinesweeperTimer extends Timer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel timeLabel;
	
	public MinesweeperTimer(int delay, ActionListener actionListener, JLabel time) {
		super(delay, actionListener);
		timeLabel = time;
	}

	public void resetTimer(){
		timeLabel.setText("0");
	}
}
