/**
 * MyungJae (Andrew) Lee, Calvin Tan, James Kattukudiyl, and Samuel Joseph
 * 
 * Main class for Battleship Application
 * 
 * Wednesday, January 21, 2015
 */

import java.awt.*;
import javax.swing.*;      

import java.util.*;
import java.awt.event.*;


public class BattleshipApplication extends JFrame{
		
	public static void main(String[] args) {
		GameModel Data = new GameModel(); 
		//Calls method setName from GameModel class
		Data.setName();
		// calls BattleLayout & GameMenu class  
		GameMenu bar = new GameMenu(Data); // both classes will have the same shipData
		BattleLayout view = new BattleLayout(Data);
		
		JMenuBar menubar;
		JMenu game, help, menuRules;
		JMenuItem menuForfeit, rule, rule2, rule3;

		menubar = new JMenuBar(); //menubar and menu items from https://www.youtube.com/watch?v=j6LVBEikup4
		//menu options
		game = new JMenu("Game");
		menuForfeit = new JMenuItem("Forfeit");
		game.add(menuForfeit);
		menubar.add(game);
		
		help = new JMenu("Help");
		menubar.add(help);
		menuRules = new JMenu("Rules");
		help.add(menuRules);

		rule = new JMenuItem("About the game");
		rule2 = new JMenuItem("How to play");
		rule3 = new JMenuItem("About the ships");
		menuRules.add(rule);
		menuRules.add(rule2);
		menuRules.add(rule3);
			
		JFrame jFrame = new JFrame();
		jFrame.setJMenuBar(menubar);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setResizable(false);
		jFrame.setTitle("Battleship");
		jFrame.setSize(850, 450);
		jFrame.setLayout(new BorderLayout());
		jFrame.add(bar, BorderLayout.NORTH);
	    jFrame.add(view, BorderLayout.CENTER); // added jPanel in the jFrame
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//adds event for the forfeit menu
		menuForfeit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "I guess you are not up to the challenge.");
				System.exit(0);
			}
		});
		//rules about the game
		rule.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null, "Both the player and the computer start with 1 carrier, battleship, submarine, destroyer, and patrol boat. \n" +
						"Ships can be placed anywhere on the grid as long as it is horizontal or vertical, and it does not exceed the boundaries.\n" +
						"Ships are placed onto the grid by selecting the ship, its orientation, and coordinate.\n" +
						"Once all ships are placed, the game may begin by pressing start.");  
			}
		});
		rule2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null, "The player gets to attack first. \n" +
						"If a ship is hit, the coordinate will turn red, otherwise, the coordinate will turn blue.\n" +
						"You cannot attack yourself or attack the same coordinate twice.\n" +
						"Once all ships are sunk, the side that still has ships remaining wins.");  
			}
		});
		rule3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null, "You have: \n1 Carrier          → 5 spaces \n1 Battleship    → 4 spaces \n1 Submarine   → 3 spaces \n1 Destroyer     → 3 spaces \n1 Patrol Boat   → 2 spaces");  
			}
		});
	}	
}

