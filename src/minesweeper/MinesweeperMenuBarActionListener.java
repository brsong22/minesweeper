package minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;

public class MinesweeperMenuBarActionListener implements ActionListener{
	private Board board;
	private MinesweeperTimer timer;
	
	MinesweeperMenuBarActionListener(Board board, MinesweeperTimer gameTime){
		this.board = board;
		this.timer = gameTime;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.stop();
		switch(((JCheckBoxMenuItem)e.getSource()).getName()){
			case "small":
				board.resetBoard(BoardSizeEnum.SMALL);
				break;
			case "medium":
				board.resetBoard(BoardSizeEnum.MEDIUM);
				break;
			case "large":
				board.resetBoard(BoardSizeEnum.LARGE);
				break;
			default:
				board.resetBoard(BoardSizeEnum.SMALL);
				break;
		}
	}

}
