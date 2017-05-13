package minesweeper;

import javax.swing.JButton;

/**
 * 
 * @author Richard Song
 * Class: GridSpace
 * *This class handles creating the individual gridspaces to put on the game board
 */
public class GridSpace {
	private JButton gridButton;
	private boolean hasBomb = false;
	private int id;
	private boolean revealed = false;
	private boolean isFlagged = false;
	
	/**
	 * GridSpace constructor
	 * 
	 * @param hasBomb - denotes if space will have a bomb or not
	 * @param id - id to reference gridspace
	 */
	GridSpace(int id){
		this.gridButton = new JButton();
		this.id = id;
	}
	
	/**
	 * getJButton
	 * gets the JButton object associated to the gridspace
	 * 
	 * @return - JButton object in the gridspace
	 */
	public JButton getJButton(){
		return this.gridButton;
	}
	
	/**
	 * setJButton
	 * assign JButton to the gridspace
	 * 
	 * @param j - JButton to be associated to the gridspace
	 */
	public void setJButton(JButton j){
		this.gridButton = j;
	}
	
	/**
	 * containsBomb
	 * returns the status on if the gridspace contains a bomb
	 * 
	 * @return - boolean of bomb status
	 */
	public boolean containsBomb(){
		return this.hasBomb;
	}
	
	/**
	 * setBomb
	 * set a bomb on the gridspace
	 * 
	 */
	public void setBomb(){
		this.hasBomb = true;
	}
	
	/**
	 * getSpaceID
	 * get the id associated to the gridspace
	 * 
	 * @return - id of the gridspace
	 */
	public int getSpaceID(){
		return this.id;
	}
	
	/**
	 * isRevealed
	 * denotes if a gridspace has been revealed
	 * 
	 * @return - boolean of revealed state of gridspace
	 */
	public boolean isRevealed(){
		return this.revealed;
	}
	
	/**
	 * reveal
	 * set status of gridspace to denote it has been played and revealed
	 */
	public void reveal(){
		this.revealed = true;
	}
	
	/**
	 * setFlag
	 * set the status of the gridspace to denote that it has been flagged (marked as having a bomb)
	 */
	public void setFlag(){
		if(isFlagged){
			isFlagged = false;
		}
		else{
			isFlagged = true;
		}
	}
	
	/**
	 * getFlagStatus
	 * get the flagged status of the gridspace
	 * 
	 * @return - boolean of flagged status
	 */
	public boolean getFlagStatus(){
		return isFlagged;
	}
}
