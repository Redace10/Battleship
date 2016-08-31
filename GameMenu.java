/**
 * MyungJae (Andrew) Lee, Calvin Tan, James Kattukudiyl, and Samuel Joseph
 * 
 * GameMenu class for Battleship Application
 * 
 * Wednesday, January 21, 2015
 */

import java.awt.*;

import javax.swing.*; 

import java.awt.event.*;

public class GameMenu extends JPanel{
    JComboBox shipList;
    JComboBox orientList;
    JButton btnStart;
    JButton btnNext;
    GameModel Data;
    JLabel userName;
    JLabel computerName;
    
    public GameMenu(GameModel sh){
    	Data = sh;
    	//gets variables from the GameModel class
    	shipList = Data.getshipList();
    	orientList = Data.getorientList();
    	btnNext = Data.getbtnNext();
    	btnStart = Data.getbtnStart();
    	userName = Data.getUserName();
        computerName = Data.getComputerName();
    	
    	this.setLayout(new GridLayout(1, 2));
    	JPanel divider = new JPanel();
    	JPanel divider2 = new JPanel();
    	divider.setLayout(new FlowLayout());// from http://docs.oracle.com/javase/tutorial/uiswing/layout/flow.html
    	divider2.setLayout(new FlowLayout());
    	divider.add(shipList);
    	divider.add(orientList);
    	divider.add(btnNext);
    	divider.add(userName);
    	divider2.add(btnStart);
    	divider2.add(computerName);
    	userName.setForeground(Color.red);
    	computerName.setForeground(Color.red);
    	this.add(divider);
    	this.add(divider2);
    	this.addEvents();
    	
    }
   // adds event to the JComboBox that contains the ship list
    public void addEvents(){
    	btnNext.setEnabled(false);
    	btnStart.setEnabled(false);
		shipList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Data.setLength();
			}
		});
		// adds event to the JComboBox that contains the options: vertical or horizontal
		orientList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Data.setDirection(orientList.getSelectedIndex());
				if(Data.getisDisplayed()){
					Data.paintBoats(Data.getPositioni(),Data.getPositionj());
				}
				if(Data.getRotateError()){
					if(orientList.getSelectedIndex() == 0){
						orientList.setSelectedIndex(1);
						Data.setDirection(1);
					}else if(orientList.getSelectedIndex() == 1){
						orientList.setSelectedIndex(0);
						Data.setDirection(0);
					}
					Data.setRotateErrorFalse(); //put it back to default
				}
			}
		});
		// adds event to the button that allows the user to start the game
		btnStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Data.beginGame();
				btnStart.setVisible(false);
				JOptionPane.showMessageDialog(null, "We are now ready to attack the enemy! \n Choose the location you want to fire at");
				Data.AttackingComputer();
			}
		});
		//next button that allows the user to move on to the next ship
		btnNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Data.setisDisplayedFalse();
				Data.keepPlayerSpace();	
				shipList.removeItem(shipList.getSelectedItem()); //the ship placed on the grid is deleted from the list
				btnNext.setEnabled(false); // the button is disabled again 
				shipList.setEnabled(true); // the list of the ships is enabled again
				
				if (shipList.getSelectedItem() == null){
					btnNext.setVisible(false); 
					shipList.setVisible(false);
					orientList.setVisible(false);
					btnStart.setEnabled(true);
				}
			}
		});
	}
}
