package minesweeper;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MinesweeperStatusButton {

	private JButton statusButton;
	
	private ImageIcon happyIcon;
	private ImageIcon excitedIcon;
	private ImageIcon coolIcon;
	private ImageIcon sadIcon;
	
	private ImageIcon[] icons;
	public MinesweeperStatusButton(){
		icons = new ImageIcon[4];
		try {
	        Image happyFace = ImageIO.read(getClass().getResource("happy.jpg"));
	        happyFace = happyFace.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
	        happyIcon = new ImageIcon(happyFace);
	        icons[0] = happyIcon;
	        
	        Image excitedFace = ImageIO.read(getClass().getResource("excited.jpg"));
	        excitedFace = excitedFace.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
	        excitedIcon = new ImageIcon(excitedFace);
	        icons[1] = excitedIcon;
	        
	        Image coolFace = ImageIO.read(getClass().getResource("cool.jpg"));
	        coolFace = coolFace.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
	        coolIcon = new ImageIcon(coolFace);
	        icons[2] = coolIcon;
	        
	        Image sadFace = ImageIO.read(getClass().getResource("sad.jpg"));
	        sadFace = sadFace.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
	        sadIcon = new ImageIcon(sadFace);
	        icons[3] = sadIcon;
	        
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.statusButton = new JButton();
		statusButton.setIcon(happyIcon);
	}
	
	public void changeStatusIcon(int s){
		statusButton.setIcon(icons[s]);
	}
	
	public JButton getStatusButton(){
		return statusButton;
	}
	
	public void addMouseActionListener(MinesweeperResetButtonMouseListener listener){
		statusButton.addMouseListener(listener);
	}
}
