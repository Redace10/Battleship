/**
 * MyungJae (Andrew) Lee, Calvin Tan, James Kattukudiyil, and Samuel Joseph
 * 
 * GameModel class for Battleship Application
 * 
 * Wednesday, January 21, 2015
 */

import java.awt.*;

import javax.swing.*; 

import java.awt.event.*;
import java.util.Arrays;

public class GameModel {
	private int size, i, j, length, direction;
	private JButton[][] btnPlayer; // from http://stackoverflow.com/questions/12231453/creating-two-dimensional-array
	private JButton[][] btnComputer;
	private boolean[][] isPlayerTaken;
	private boolean[][] isComputerTaken;
	private String[] boats = {"Carrier", "Battleship", "Submarine", "Destroyer", "PatrolBoat"}; // from http://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html 
	private String[] orientation = {"Vertical", "Horizontal"};
    private JComboBox shipList = new JComboBox(boats);
    private JComboBox orientList = new JComboBox(orientation);
    private int clicked = 1;
    private int clicked2 = 1;
    private JButton btnStart = new JButton("Start");
    private JButton btnNext = new JButton("Next");
    private String inputName;
    private JLabel userName;
    private JLabel computerName;
    private int compass = 0;
    private boolean RotateError = false;
    private boolean isDisplayed = false;
    private boolean isFirstHit = true, isActive = false;
    private int old_x = 0, old_y = 0;
	
	public GameModel(){
		length = 5;
		direction = 0;
		RotateError = false;
		isDisplayed = false;
		btnPlayer = new JButton[10][10];
		btnComputer = new JButton[10][10];
		
		isPlayerTaken = new boolean[10][10];
		isComputerTaken = new boolean[10][10];
		i = 0;
		j = 0;
		shipList = new JComboBox(boats);
		orientList = new JComboBox(orientation);
		inputName = "";
	}
	public void setName(){
		inputName = JOptionPane.showInputDialog("Enter your name:");
		if (inputName == null || inputName.equals("")){		 
			inputName = JOptionPane.showInputDialog("Please enter your name.");
			if (inputName == null || inputName.equals("")){
				inputName = JOptionPane.showInputDialog("Please enter something!");
				if (inputName == null || inputName.equals("")){
					inputName = "Player";
					JOptionPane.showMessageDialog(null, "Okay, since you don't have a name, I'll just call you Player.");
				   	JOptionPane.showMessageDialog(null, "Welcome to the battlefield General "  + inputName + "!");
				}else{
					JOptionPane.showMessageDialog(null, "Welcome to the battlefield General "  + inputName + "!");	
				}
			}else{
				JOptionPane.showMessageDialog(null, "Welcome to the battlefield General "  + inputName + "!");	
			}
		}else{
			JOptionPane.showMessageDialog(null, "Welcome to the battlefield General "  + inputName + "!");  
		}
		JOptionPane.showMessageDialog(null, "If you are unsure of what to do,\nplease refer to the rules on the top left corner.");  
	}
	public String getName(){
		return inputName;
	}
	public void setLength(){
		//determines which ship the user clicked from the JComboBox
		if(shipList.getSelectedItem() == null){
			
		}else if (shipList.getSelectedItem().equals("Carrier")){
			length = 5;
		}else if (shipList.getSelectedItem().equals("Battleship")){
			length = 4;						
		}else if (shipList.getSelectedItem().equals("Submarine")){
			length = 3;						
		}else if (shipList.getSelectedItem().equals("Destroyer")){
			length = 3;						
		}else if (shipList.getSelectedItem().equals("PatrolBoat")){
			length = 2;		
		}
	}
	
	//Gets the length of the ship
    public int getLength(){
    	return length;
    }
    
  //Determines whether the boat is placed vertical or horizontal
    public void setDirection(int p){
    	direction = p;
    }
    public int getDirection(){
    	return direction;
    }
    public int getComDirection(){
    	direction = (int)(Math.random()*2);
    	return direction;
    }
    public JButton[][] getPlayerBtn(){
    	return btnPlayer;
    }
    public JButton[][] getComputerBtn(){
    	return btnComputer;
    }
    //determines if the space is occupied by a ship
    public boolean[][] getPlayerSpace(){
    	return isPlayerTaken;
    }
    public boolean[][] getComputerSpace(){
    	return isComputerTaken;
    }
    public int getPositioni(){
    	return i;
    }
    public int getPositionj(){
    	return j;
    }
    public void setPosition(int x, int y){
    	i = x;
    	j = y;
    }
    public void setRotateErrorFalse(){
    	RotateError = false;
    }
    public boolean getRotateError(){
    	return RotateError;
    }
    public void setisDisplayedFalse(){
    	isDisplayed = false;
    }
    public boolean getisDisplayed(){
    	return isDisplayed;
    }
    
    //Paints the boats
    public void paintBoats(int temp, int temp2){
    	if (this.getDirection() == 0){ // if vertical ships
			if (temp > 10 - this.getLength()){ // program doesn't allow ship to go out of bounds
				JOptionPane.showMessageDialog(null, "You cannot place the ship there \n it will go out of boundary");
				RotateError = true;
			}else{
				//Check for overlapping
				boolean check = false;
				for (int a = 0; a < this.getLength(); a++) {
					check = check || isPlayerTaken[temp + a][temp2]; //like OR gate
				}
				if (check == true){
					JOptionPane.showMessageDialog(null, "You cannot place the ship there \n it will overlap with another ship");
					RotateError = true;
				}else{
					defaultColour();
					for(int a = 0; a < this.getLength(); a++) {
						btnPlayer[temp + a][temp2].setBackground(Color.black);
					}
					isDisplayed = true;
					this.setPosition(temp, temp2);
					shipList.setEnabled(false);
					btnNext.setEnabled(true);
					RotateError = false;
				}
			}
		}else if (this.getDirection() == 1){ // if horizontal ships
			if (temp2 > 10 - this.getLength()){ // program doesn't allow ship to go out of bounds
				JOptionPane.showMessageDialog(null, "You cannot place the ship there \n it will go out of boundary");
				RotateError = true;
			}else{
				//Check for overlapping;
				boolean check = false;
				for (int a = 0; a < this.getLength(); a++){
					check = check || isPlayerTaken[temp][temp2 +a];
				}
				if (check == true){
					JOptionPane.showMessageDialog(null, "You cannot place the ship there \n it will overlap with another ship");
					RotateError = true;
				}else{
					defaultColour();
					for(int a = 0; a < this.getLength(); a++) {
						btnPlayer[temp][temp2 + a].setBackground(Color.black);
					}
					isDisplayed = true;
					this.setPosition(temp, temp2); //places ship
					shipList.setEnabled(false); // the JComboBox with the list of ships is disabled
					btnNext.setEnabled(true); // the next button is enabled
					RotateError = false;
				}
			}
		}
    }
    
    // method allows only one type of ship to be placed once
 	public void defaultColour(){
 		for(int i = 0; i <= 9; i++) {
 			for(int j = 0; j <= 9; j++) {
 				if(isPlayerTaken[i][j] == false){
 					btnPlayer[i][j].setBackground(null);
 				}
 			}
 		}
 	}
    
    //once the user places the ship, this allows the ship to be saved and placed there
    public void keepPlayerSpace(){
    	if (direction == 0){
    		for(int a = 0; a < length; a++) {
    			isPlayerTaken[i+a][j] = true;
			}
		}
		if (direction == 1){
			for(int a = 0; a < length; a++) {
				isPlayerTaken[i][j+a] = true;
			}
		}
    }
    
    public JComboBox getshipList(){
    	return shipList;
    }
    public JComboBox getorientList(){
    	return orientList;
    }
    public JButton getbtnNext(){
    	return btnNext;
    }
    public JButton getbtnStart(){
    	return btnStart;
    }
    public JLabel getUserName(){
    	return userName = new JLabel(getName());
    }
    public JLabel getComputerName(){
    	return computerName = new JLabel("Enemy");
    }
    
    //Computer places ship
    public void beginGame(){
    	int[] boatLength = {5,4,3,3,2};
		for(int boats = 0; boats < 5; boats++) {
			
			direction = getComDirection();
			
			if (direction == 0){
				//Check for overlapping
				boolean taken = true;
				
				while(taken == true){
					// program doesn't allow ship to go out of bounds
					i = (int)(Math.random()*(boatLength[boats] + 1)); //range from 0-5
					j = (int)(Math.random()*10);
					//checks if there is already a ship in that position
					for (int k = 0; k < boatLength[boats]; k++) {
						taken = isComputerTaken[i + k][j];
						
						if(taken == true){
							break;
						}
					}
				}
				
				for(int k = 0; k < boatLength[boats]; k++) {
					//btnComputer[i + k][j].setBackground(Color.black); //this code makes the computer ships visible
					isComputerTaken[i+k][j] = true;
				}
			}else if (direction == 1){
				//Check for overlapping
				boolean taken = true;
				
				while(taken == true){
					// program doesn't allow ship to go out of bounds
					i = (int)(Math.random()*10); 
					j = (int)(Math.random()*(boatLength[boats] + 1)); //range from 0-5
					//checks if there is already a ship in that position
					for (int k = 0; k < boatLength[boats]; k++) {
						taken = isComputerTaken[i][j + k];
						
						if(taken == true){
							break;
						}
					}
				}
		
				for(int k = 0; k < boatLength[boats]; k++) {
					//btnComputer[i][j + k].setBackground(Color.black); //this code makes the computer ships visible
					isComputerTaken[i][j + k] = true;
				}
			}
		}
    }
    
    //Computer attacks
    public void AttackingUser(){
    	boolean picked = true; //checks if that spot isn't picked
    	
    	if(btnPlayer[i][j].getBackground() == Color.blue){
    		//If the guessing part near the red block is still active
    		if(isActive){
	    		i = old_x;
				j = old_y;
    		}
    	}
    	
		if (btnPlayer[i][j].getBackground() == Color.red){
			boolean success = false; //checks if the computer tried up, right, down, left
			
			if(isFirstHit){
				isActive = true;
				old_x = i;
				old_y = j;
				isFirstHit = false;
			}
			
			//success if a block has been guessed
			while(success == false){
				
				switch(compass){
					case 0: //north
						i = i - 1;
						break;
					case 1: //south
						i = i + 1;
						break;
					case 2: //west
						j = j - 1;
						break;
					case 3: //east
						j = j + 1;
						break;
					
				}
				
				//it reaches a boundary
				if(i < 0){
					i = 0;
				}else if(j > 9){
					j = 9;
				}else if(i > 9){
					i = 9;
				}else if(j < 0){
					j = 0;
				}
				
				success = true;
				
				if(btnPlayer[i][j].getBackground() == Color.blue || btnPlayer[i][j].getBackground() == Color.red){
					compass++;
					//remembers the first hit of the ship
					i = old_x; 
					j = old_y;
					success = false;
				}
				
				//If computer tried all directions and failed, computer then guesses a new location
				if(compass >= 4){
					success = true;
					isFirstHit = true;
					isActive = false;
					
					while(picked == true){
		    			i = (int)(Math.random()*10);
		    			j = (int)(Math.random()*10);
		    		
		    			//if red part is hit, then go up, right, down, or left
		    			if (btnPlayer[i][j].getBackground() != Color.blue && btnPlayer[i][j].getBackground() != Color.red){ // checks if the computer already picked this position
		    				picked = false;
		    			}
					}
					compass = 0;
				}
			}
		}else{
			while(picked == true){
    			i = (int)(Math.random()*10);
    			j = (int)(Math.random()*10);
    		
    			//if red part is hit, then u go up, right, down, left
    			if (btnPlayer[i][j].getBackground() != Color.blue && btnPlayer[i][j].getBackground() != Color.red){ // checks if the computer already picked this position
    				picked = false;
    			}
			}
			compass = 0;
			isFirstHit = true;
		}
		
		if (isPlayerTaken[i][j] == true){
			btnPlayer[i][j].setBackground(Color.red); // button turns red if the user hits the enemy ship
			//JOptionPane.showMessageDialog(null, "Yes! We hit the enemy ship general! \nIts in flames!");
			//AttackingUser();
			if (clicked == 17){
				JOptionPane.showMessageDialog(null, "All our ships have been destroyed... \nI...I...I'm afraid we lost sir.... \nLet's retreat before they capture us!"); //program exits once all user ships are sunk
				System.exit(0);								
			}
			clicked ++;
		}else{
			btnPlayer[i][j].setBackground(Color.blue); // button turns blue if the user misses the enemy ship
			//JOptionPane.showMessageDialog(null, "Ahhhhh, general, we missed the enemy ship and fired at the water!");
		}
		
    }
    public void AttackingComputer(){
		for(int i = 0; i <= 9; i++) {
			for(int j = 0; j <= 9; j++) {
				final int temp = i; // temporary variable
				final int temp2 = j;
				btnComputer[i][j].addActionListener(new ActionListener(){ //ship arrangement for the computer
					public void actionPerformed(ActionEvent e) {
						if (btnComputer[temp][temp2].getBackground() == Color.blue || btnComputer[temp][temp2].getBackground() == Color.red){ // checks if the user already picked this position
							JOptionPane.showMessageDialog(null, "You already picked that coordinate!");
						}else{
							if (isComputerTaken[temp][temp2] == true){
								btnComputer[temp][temp2].setBackground(Color.red); // button turns red if the user hits the enemy ship
								//JOptionPane.showMessageDialog(null, "Yes! We hit the enemy ship, general! \nIt's in flames!");
								
								if (clicked2 == 17){
									JOptionPane.showMessageDialog(null, "Congratulations General! We defeated the enemy!!! \nAll ships are destroyed! \nWE WON!!!!!!"); //program exits once all enemny ships are sunk
									System.exit(0);								}
								clicked2 ++;
							}else{
								btnComputer[temp][temp2].setBackground(Color.blue); // button turns blue if the user misses the enemy ship
								//JOptionPane.showMessageDialog(null, "Ahhhhh, general, we missed the enemy ship and fired at the water!");
							}
							AttackingUser();
						}
					}
				});
			}
		}
    }	
}
