package minesweeper;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MinesweeperMenuBar {
	
	private JMenuBar menuBar;
	private JMenu gameSizes;
	private JMenu gameBot;
	
	private static JMenuItem runBot;
	private static ArrayList<JCheckBoxMenuItem> checkboxMenu = new ArrayList<JCheckBoxMenuItem>();
	
	public MinesweeperMenuBar(String state){
		
		boolean smallState = state.equals("small");
		boolean medState = state.equals("medium");
		boolean largeState = state.equals("large");
		
		this.menuBar = new JMenuBar();
		this.gameSizes = new JMenu("Size");
		this.gameBot = new JMenu("Robot");
		this.menuBar.add(this.gameSizes);
		this.menuBar.add(this.gameBot);
		
		JCheckBoxMenuItem small = new JCheckBoxMenuItem("9x9: 10 Mines");
		small.setState(smallState);
		small.setName("small");
		gameSizes.add(small);
		checkboxMenu.add(small);
		
		JCheckBoxMenuItem medium = new JCheckBoxMenuItem("16x16: 40 Mines");
		medium.setState(medState);
		medium.setName("medium");
		gameSizes.add(medium);
		checkboxMenu.add(medium);
		
		JCheckBoxMenuItem large = new JCheckBoxMenuItem("30x16: 99 Mines");
		large.setState(largeState);
		large.setName("large");
		gameSizes.add(large);
		checkboxMenu.add(large);
		
		runBot = new JMenuItem("Run");
		gameBot.add(runBot);
	}
	
	public static void addSizeMenuActionListener(ActionListener l){
		for(JCheckBoxMenuItem c : checkboxMenu){
			c.addActionListener(l);
		}
	}
	
	public static void addBotMenuActionListener(ActionListener ai){
		runBot.addActionListener(ai);
	}
	
	public ArrayList<JCheckBoxMenuItem> getMenuCheckBoxItems(){
		return checkboxMenu;
	}
	
	public JMenuBar getMenuBar(){
		return this.menuBar;
	}
	
}
