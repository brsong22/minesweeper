package minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesweeperBotMenuActionListener implements ActionListener{

//	private Board board;
//	private MinesweeperTimer timer;
	private MinesweeperBot bot;
	
	MinesweeperBotMenuActionListener(MinesweeperBot b){
		this.bot = b;
//		this.board = board;
//		this.timer = gameTime;
//		this.bot = bot;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Run"){
			bot.startBot();
		}
		
	}

}
