package minesweeper;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MinesweeperResetButtonMouseListener implements MouseListener{

	private MinesweeperTimer gameTime;
	private Board board;
	
	public MinesweeperResetButtonMouseListener(MinesweeperTimer timer, Board board){
		gameTime = timer;
		this.board = board;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		gameTime.stop();
		gameTime.resetTimer();
		board.resetBoard();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
