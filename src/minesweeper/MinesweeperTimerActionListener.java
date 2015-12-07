package minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class MinesweeperTimerActionListener implements ActionListener{

	private JLabel timeLabel;
	
	public MinesweeperTimerActionListener(JLabel tLabel){
		timeLabel = tLabel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int time = Integer.parseInt(timeLabel.getText());
		++time;
		timeLabel.setText(Integer.toString(time));
	}

}
