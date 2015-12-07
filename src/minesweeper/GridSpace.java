package minesweeper;

import javax.swing.JButton;

public class GridSpace {
	private JButton gridButton;
	private boolean hasBomb;
	private int id;
	private boolean revealed = false;
	private boolean isFlagged = false;
	
	GridSpace(boolean hasBomb, int id){
		this.gridButton = new JButton();
		this.hasBomb = hasBomb;
		this.id = id;
	}
	
	public JButton getJButton(){
		return this.gridButton;
	}
	
	public void setJButton(JButton j){
		this.gridButton = j;
	}
	
	public boolean containsBomb(){
		return this.hasBomb;
	}
	
	public void setBomb(boolean b){
		this.hasBomb = b;
	}
	
	public int getSpaceID(){
		return this.id;
	}
	
	public boolean isRevealed(){
		return this.revealed;
	}
	
	public void reveal(){
		this.revealed = true;
	}
	
	public void setFlag(){
		if(isFlagged){
			isFlagged = false;
		}
		else{
			isFlagged = true;
		}
	}
	public boolean getFlagStatus(){
		return isFlagged;
	}
}
