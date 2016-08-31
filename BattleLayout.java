/**
 * MyungJae (Andrew) Lee, Calvin Tan, James Kattukudiyl, and Samuel Joseph
 * 
 * BattleLayout class for Battleship Application
 * 
 * Wednesday, January 21, 2015
 */

import java.awt.*;

import javax.swing.*; 

import java.awt.event.*;
import java.util.Random;

public class BattleLayout extends JPanel{
	
	// Arrays of JButton, learned from Blue Pelican Java by Charles E. Cook
	String letter[] = {"A","B","C","D","E","F","G","H","I","J"}; // For the y-coordinates
	
	JButton[][] btnPlayer; // from http://stackoverflow.com/questions/12231453/creating-two-dimensional-array
	JButton[][] btnComputer;
	boolean[][] isPlayerTaken;
	boolean[][] isComputerTaken;
	JComboBox shipList;
    JComboBox orientList;
    JButton btnStart;
    JButton btnNext;
    int boat;
	
	JLabel[] labelPlayer = new JLabel[10]; //the x-coordinates of the player grid
	JLabel[] labelComputer = new JLabel[10]; //the x-coordinates of the computer grid
	GameModel Data;
	//gets variables from the GameModel class
	public BattleLayout(GameModel sh){
		Data = sh;
		shipList = Data.getshipList();
    	orientList = Data.getorientList();
    	btnNext = Data.getbtnNext();
    	btnStart = Data.getbtnStart();
		btnPlayer = Data.getPlayerBtn();
		btnComputer = Data.getComputerBtn();
		isPlayerTaken = Data.getPlayerSpace();
		isComputerTaken = Data.getComputerSpace();
		this.setLayout(new GridLayout(11, 21));
		
		for(int i = 0; i <= 9; i++) {
			labelPlayer[i] = new JLabel(i+1+"",SwingConstants.CENTER);  //TextAllignment from http://www.cs.cf.ac.uk/Dave/HCI/HCI_Handout_CALLER/node37.html
			this.add(labelPlayer[i]);
		}
		
		this.add(new JLabel("", SwingConstants.CENTER)); //adds space in the middle to separate the two grids
		
		for(int i = 0; i <= 9; i++) {
			labelComputer[i] = new JLabel(i+1+"",SwingConstants.CENTER);  
			this.add(labelComputer[i]);
		}
		//buttons for player
		for(int i = 0; i <= 9; i++) {
			for(int j = 0; j <= 9; j++) {
				btnPlayer[i][j] = new JButton(); 
				this.add(btnPlayer[i][j]);
			}
			
			//adds the letter coordinates in the middle
			this.add(new JLabel(letter[i], SwingConstants.CENTER));
			
			//buttons for computer
			for(int j = 0; j <= 9; j++) {
				btnComputer[i][j] = new JButton();
				this.add(btnComputer[i][j]);	
			}
		}
		this.addEvents();
		//this.Data.ComputerShip();
	}
	//events are added to the buttons
	public void addEvents(){
		for(int i = 0; i <= 9; i++) {
			for(int j = 0; j <= 9; j++) {
				final int temp = i; // temporary variable
				final int temp2 = j;
				btnPlayer[i][j].addActionListener(new ActionListener(){ // ship arrangement for the player
					public void actionPerformed(ActionEvent e) {
						if (shipList.getSelectedItem() != null){
							Data.paintBoats(temp,temp2);
						}
					}
				});
			}
		}
	}
}

