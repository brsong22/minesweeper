package minesweeper;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JCheckBoxMenuItem;

public class MinesweeperMenuBarActionListener implements ActionListener{

	private MinesweeperMenuBar menubar;
	private Board board;
	private MinesweeperTimer timer;
	
	MinesweeperMenuBarActionListener(MinesweeperMenuBar menu, Board board, MinesweeperTimer gameTime){
		this.menubar = menu;
		this.board = board;
		this.timer = gameTime;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<JCheckBoxMenuItem> checkBoxes = menubar.getMenuCheckBoxItems();
		for(Component c : checkBoxes){
			if(c.getName().equals(((JCheckBoxMenuItem)e.getSource()).getName())){
				((AbstractButton)c).setSelected(true);
				timer.stop();
				switch(c.getName()){
					case "small":
						board.resizeBoard(BoardSizeEnum.SMALL);
						break;
					case "medium":
						board.resizeBoard(BoardSizeEnum.MEDIUM);
						break;
					case "large":
						board.resizeBoard(BoardSizeEnum.LARGE);
						break;
					default:
						board.resizeBoard(BoardSizeEnum.SMALL);
						break;
				}
			}
			else{
				((AbstractButton)c).setSelected(false);
			}
		}
		
	}

}
