package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import engine.Game;
import engine.Player;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.EffectType;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Damageable;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;

public class Controller implements ActionListener{
	
	Player p1, p2;
	Game game;
	SelectionMenu selectionMenu;
	StartScreen startScreen;
	MainGame mainGame;
	ArrayList<ImageIcon> selectionImages;
	ArrayList<ImageIcon> boardImages;
	ArrayList<ImageIcon> iconImages;
	ArrayList<Champion> turnOrderAL;
	ArrayList<Champion> p1Team;
	ArrayList<Champion> p2Team;
	ImageIcon CoverImg;
	boolean f1,f2,f3,f4,f5,f6; 
	Champion c1, c2, c3, c4, c5, c6;
	Champion tmpChamp;
	int direction;
	JButton tmpButton;
	Object[][] gameBoard;
	Champion currentChamp;
	Damageable selectedTarget;
	int x,y;
	Sound smSound, mgSound;
	Sound2 moveSound, attSound, caSound;
	
	

	
	public Controller() throws IOException {
		
		//initialization
		p1 = new Player("");
		p2 = new Player("");
		game = new Game(p1, p2);
		
		selectionImages = new ArrayList<ImageIcon>();
		boardImages = new ArrayList<ImageIcon>();
		iconImages = new ArrayList<ImageIcon>();
		
		

		startScreen = new StartScreen();
		smSound = new Sound();
		smSound.runMusic("smSound.wav");
		
		selectionMenu = new SelectionMenu();
		mainGame = new MainGame();	
		
		f1 = false;
		f2 = false;
		f3 = false;
		f4 = false;
		f5 = false;
		f6 = false;
		
		tmpChamp = null;
		turnOrderAL = new ArrayList<Champion>();
		
		
		
		//game related
		Game.loadAbilities("Abilities.csv");
		Game.loadChampions("Champions.csv");
		
		//change

		//end change
		
		
		//view related
		
		addImages();
		

		

		
		//change

		
		//gameBoard = game.getBoard();
//		currentChamp = game.getCurrentChampion();
//		selectedTarget = null;
//		
//		for (int i = 4; i > -1; i--) {
//			for(int j = 0; j < 5; j++) {
//				if(gameBoard[i][j] instanceof Cover)
//					mainGame.board[i][j].setIcon(CoverImg);
//			}
//		}
//
//		
//		while(!game.getTurnOrder().isEmpty())
//			turnOrderAL.add((Champion) game.getTurnOrder().remove());
//		
//		for(int i = 0; i < turnOrderAL.size(); i++) {
//			game.getTurnOrder().insert(turnOrderAL.get(i));
//		}
//
//		for(int i = 0; i < turnOrderAL.size(); i++) {
//			JLabel l = new JLabel();
//			l.setVisible(true);	
//			l.setOpaque(true);
//			l.setLayout(null);
//			l.setIcon(getChampIcon(turnOrderAL.get(i)));
//			mainGame.turnOrderL.add(l);
//		}
//		
//		for(int i = 0; i < mainGame.turnOrderL.size(); i++) {
//			mainGame.turnOrderP.add(mainGame.turnOrderL.get(i));
//		}
//		
//		
//		mainGame.ability1.setText(setAbilityDetails(currentChamp.getAbilities().get(0)));
//		mainGame.ability2.setText(setAbilityDetails(currentChamp.getAbilities().get(1)));
//		mainGame.ability3.setText(setAbilityDetails(currentChamp.getAbilities().get(2)));
//		//end change
		
		for(int i = 0; i < 15; i++) {
			JButton b = (JButton) selectionMenu.allChampions.getComponent(i);
			b.setIcon(selectionImages.get(i));
			b.addActionListener(this);
		}
		
		//action listeners
		
		startScreen.start.addActionListener(this);
		startScreen.firstNameButton.addActionListener(this);
		startScreen.secondNameButton.addActionListener(this);
		
		selectionMenu.firstTeam.addActionListener(this);
		selectionMenu.secondTeam.addActionListener(this);
		selectionMenu.p1c1.addActionListener(this);
		selectionMenu.p1c2.addActionListener(this);
		selectionMenu.p1c3.addActionListener(this);
		selectionMenu.p2c1.addActionListener(this);
		selectionMenu.p2c2.addActionListener(this);
		selectionMenu.p2c3.addActionListener(this);
		selectionMenu.clearTeam1.addActionListener(this);
		selectionMenu.clearTeam2.addActionListener(this);
		selectionMenu.startGame.addActionListener(this);
		
		mainGame.up.addActionListener(this);
		mainGame.down.addActionListener(this);
		mainGame.right.addActionListener(this);
		mainGame.left.addActionListener(this);
		mainGame.move.addActionListener(this);
		mainGame.attack.addActionListener(this);
		for (int i = 4; i > -1; i--) {
			for(int j = 0; j < 5; j++) {
				mainGame.board[i][j].addActionListener(this);		
			}
		}
		mainGame.ability1B.addActionListener(this);	
		mainGame.ability2B.addActionListener(this);	
		mainGame.ability3B.addActionListener(this);	
		mainGame.endTurnB.addActionListener(this);	
		mainGame.leaderAbilityB.addActionListener(this);	
		mainGame.leaderAbilityB2.addActionListener(this);	
		

	}
	
	//action performed
	public void actionPerformed(ActionEvent e) {
		
		/*START SCREEN*/
		
		//initializing players' names
		if (e.getSource()==startScreen.firstNameButton) {	
			if(startScreen.firstName.getText().length() == 0) 
				JOptionPane.showMessageDialog(null, "You did not enter a name", "Error", JOptionPane.ERROR_MESSAGE);
			else
				p1 = new Player(startScreen.firstName.getText());
		}
		if (e.getSource()==startScreen.secondNameButton) {
			if(startScreen.secondName.getText().length() == 0) 
				JOptionPane.showMessageDialog(null, "You did not enter a name", "Error", JOptionPane.ERROR_MESSAGE);
			else
				p2 = new Player(startScreen.secondName.getText());
		}
		
		//go to selection menu
		if(e.getSource() == startScreen.start) {
			if((startScreen.firstName.getText().length() == 0) || startScreen.secondName.getText().length() == 0)
				JOptionPane.showMessageDialog(null, "You need to enter both players' names", "Error", JOptionPane.ERROR_MESSAGE);
			else {
				selectionMenu.first.setText(p1.getName());
				selectionMenu.second.setText(p2.getName());
				startScreen.frame.setVisible(false);

				selectionMenu.frame.setVisible(true);
			}
		}		
		
		
		
		/*SELECTION MENU*/
		
		//adding all champions to buttons
		for (int i = 0; i < 15; i++) {
			if (e.getSource() == selectionMenu.allChampions.getComponent(i)) {
				tmpButton = (JButton) selectionMenu.allChampions.getComponent(i);
				tmpChamp = Game.getAvailableChampions().get(i);
				JTextPane attribute = (JTextPane) selectionMenu.description.getComponent(0);
				JTextPane abilities = (JTextPane) selectionMenu.description.getComponent(1);
				attribute.setText(getAttributes(tmpChamp));
				abilities.setText(getAbilities(tmpChamp));
			}
		}
		
		//add to first team
		if (e.getSource() == selectionMenu.firstTeam) {

			selectionMenu.firstLeader.setVisible(true);
			selectionMenu.currentLeader1.setVisible(true);
			selectionMenu.clearTeam1.setVisible(true);
			if (!f1) {
				if (p1.getTeam().contains(tmpChamp) || p2.getTeam().contains(tmpChamp))
					JOptionPane.showMessageDialog(null, "You can only add the champion once", "Error",
							JOptionPane.ERROR_MESSAGE);
				else {
					selectionMenu.p1c1.setIcon(tmpButton.getIcon());
					//selectionMenu.p1c1.setText(tmpChamp.getName());
					selectionMenu.p1c1.setVisible(true);
					p1.getTeam().add(tmpChamp);
					c1 = tmpChamp;
					selectionMenu.allChampions.getComponent(getButton(tmpChamp)).setEnabled(false);
					f1 = true;
				}
			} 
			else if (!f2) {
				if (p1.getTeam().contains(tmpChamp) || p2.getTeam().contains(tmpChamp))
					JOptionPane.showMessageDialog(null, "You can only add the champion once", "Error",
							JOptionPane.ERROR_MESSAGE);
				else {
					selectionMenu.p1c2.setIcon(tmpButton.getIcon());
					//selectionMenu.p1c2.setText(tmpChamp.getName());
					selectionMenu.p1c2.setVisible(true);
					p1.getTeam().add(tmpChamp);
					c2 = tmpChamp;
					selectionMenu.allChampions.getComponent(getButton(tmpChamp)).setEnabled(false);
					f2 = true;
				}
			} 
			else if (!f3) {
				if (p1.getTeam().contains(tmpChamp) || p2.getTeam().contains(tmpChamp))
					JOptionPane.showMessageDialog(null, "You can only add the champion once", "Error",
							JOptionPane.ERROR_MESSAGE);
				else {
					selectionMenu.p1c3.setIcon(tmpButton.getIcon());
					//selectionMenu.p1c3.setText(tmpChamp.getName());
					selectionMenu.p1c3.setVisible(true);
					p1.getTeam().add(tmpChamp);
					c3 = tmpChamp;
					selectionMenu.allChampions.getComponent(getButton(tmpChamp)).setEnabled(false);
					f3 = true;
					selectionMenu.firstTeam.setEnabled(false);
				}
			}

		}

		// add to second team
		if (e.getSource() == selectionMenu.secondTeam) {
			selectionMenu.secondLeader.setVisible(true);
			selectionMenu.currentLeader2.setVisible(true);
			selectionMenu.clearTeam2.setVisible(true);
			if (!f4) {
				if (p2.getTeam().contains(tmpChamp) || p1.getTeam().contains(tmpChamp))
					JOptionPane.showMessageDialog(null, "You can only add the champion once", "Error",
							JOptionPane.ERROR_MESSAGE);
				else {
					selectionMenu.p2c1.setIcon(tmpButton.getIcon());
					//selectionMenu.p2c1.setText(tmpChamp.getName());
					selectionMenu.p2c1.setVisible(true);
					p2.getTeam().add(tmpChamp);
					c4 = tmpChamp;
					selectionMenu.allChampions.getComponent(getButton(tmpChamp)).setEnabled(false);
					f4 = true;
				}
			} 
			else if (!f5) {
				if (p2.getTeam().contains(tmpChamp) || p1.getTeam().contains(tmpChamp))
					JOptionPane.showMessageDialog(null, "You can only add the champion once", "Error",
							JOptionPane.ERROR_MESSAGE);
				else {
					selectionMenu.p2c2.setIcon(tmpButton.getIcon());
					//selectionMenu.p2c2.setText(tmpChamp.getName());
					selectionMenu.p2c2.setVisible(true);
					p2.getTeam().add(tmpChamp);
					c5 = tmpChamp;
					selectionMenu.allChampions.getComponent(getButton(tmpChamp)).setEnabled(false);
					f5 = true;
				}
			} 
			else if (!f6) {
				if (p2.getTeam().contains(tmpChamp) || p1.getTeam().contains(tmpChamp))
					JOptionPane.showMessageDialog(null, "You can only add the champion once", "Error",
							JOptionPane.ERROR_MESSAGE);
				else {
					selectionMenu.p2c3.setIcon(tmpButton.getIcon());
					//selectionMenu.p2c3.setText(tmpChamp.getName());
					selectionMenu.p2c3.setVisible(true);
					p2.getTeam().add(tmpChamp);
					c6 = tmpChamp;
					selectionMenu.allChampions.getComponent(getButton(tmpChamp)).setEnabled(false);
					f6 = true;
					selectionMenu.secondTeam.setEnabled(false);
				}
			}
		}
		
		//clear team 1
		if(e.getSource() == selectionMenu.clearTeam1) {
			p1.setLeader(null);
			int j = p1.getTeam().size() - 1;
			while(p1.getTeam().size() > 0) {
				p1.getTeam().remove(j);
				j--;  
			}
			selectionMenu.firstTeam.setEnabled(true);
			selectionMenu.p1c1.setVisible(false);
			selectionMenu.p1c2.setVisible(false);
			selectionMenu.p1c3.setVisible(false);
			f1 = false;
			f2 = false;
			f3 = false;
			selectionMenu.currentLeader1.setText("Current Leader: ");
			for (int i = 0; i < 15; i++) {
				JButton b = (JButton) selectionMenu.allChampions.getComponent(i);
				if (b.getIcon().equals(selectionMenu.p1c1.getIcon())) 
					selectionMenu.allChampions.getComponent(i).setEnabled(true);
			}
			for (int i = 0; i < 15; i++) {
				JButton b = (JButton) selectionMenu.allChampions.getComponent(i);
				if (b.getIcon().equals(selectionMenu.p1c2.getIcon())) 
					selectionMenu.allChampions.getComponent(i).setEnabled(true);
			}
			for (int i = 0; i < 15; i++) {
				JButton b = (JButton) selectionMenu.allChampions.getComponent(i);
				if (b.getIcon().equals(selectionMenu.p1c3.getIcon())) 
					selectionMenu.allChampions.getComponent(i).setEnabled(true);
			}
			selectionMenu.startGame.setEnabled(false);
		}
		
		//clear team 2
		if(e.getSource() == selectionMenu.clearTeam2) {
			p2.setLeader(null);
			int j = p2.getTeam().size() - 1;
			while(p2.getTeam().size() > 0) {
				p2.getTeam().remove(j);
				j--;  
			}
			selectionMenu.secondTeam.setEnabled(true);
			selectionMenu.p2c1.setVisible(false);
			selectionMenu.p2c2.setVisible(false);
			selectionMenu.p2c3.setVisible(false);
			f4 = false;
			f5 = false;
			f6 = false;
			selectionMenu.currentLeader2.setText("Current Leader: ");
			for (int i = 0; i < 15; i++) {
				JButton b = (JButton) selectionMenu.allChampions.getComponent(i);
				if (b.getIcon().equals(selectionMenu.p2c1.getIcon())) 
					selectionMenu.allChampions.getComponent(i).setEnabled(true);
			}
			for (int i = 0; i < 15; i++) {
				JButton b = (JButton) selectionMenu.allChampions.getComponent(i);
				if (b.getIcon().equals(selectionMenu.p2c2.getIcon())) 
					selectionMenu.allChampions.getComponent(i).setEnabled(true);
			}
			for (int i = 0; i < 15; i++) {
				JButton b = (JButton) selectionMenu.allChampions.getComponent(i);
				if (b.getIcon().equals(selectionMenu.p2c3.getIcon())) 
					selectionMenu.allChampions.getComponent(i).setEnabled(true);
			}
			selectionMenu.startGame.setEnabled(false);
		}
		
		//select leaders
		if (e.getSource()==selectionMenu.p1c1) {
			p1.setLeader(p1.getTeam().get(0));
			String s = p1.getTeam().get(0).getName();
			selectionMenu.currentLeader1.setText("Current Leader: " + s);	
		}
		if (e.getSource()==selectionMenu.p1c2) {
			p1.setLeader(p1.getTeam().get(1));
			String s = p1.getTeam().get(1).getName();
			selectionMenu.currentLeader1.setText("Current Leader: " + s);
		}
		if (e.getSource()==selectionMenu.p1c3) {
			p1.setLeader(p1.getTeam().get(2));
			String s = p1.getTeam().get(2).getName();
			selectionMenu.currentLeader1.setText("Current Leader: " + s);
		}
		if (e.getSource()==selectionMenu.p2c1) {
			p2.setLeader(p2.getTeam().get(0));
			String s = p2.getTeam().get(0).getName();
			selectionMenu.currentLeader2.setText("Current Leader: " + s);
		}
		if (e.getSource()==selectionMenu.p2c2) {
			p2.setLeader(p2.getTeam().get(1));
			String s = p2.getTeam().get(1).getName();
			selectionMenu.currentLeader2.setText("Current Leader: " + s);
		}
		if (e.getSource()==selectionMenu.p2c3) {
			p2.setLeader(p2.getTeam().get(2));
			String s = p2.getTeam().get(2).getName();
			selectionMenu.currentLeader2.setText("Current Leader: " + s);
		}	
		
		if (checkStartingCondition())
			selectionMenu.startGame.setEnabled(true);
		
		// go to and make main game
		if (e.getSource()==selectionMenu.startGame) {
			selectionMenu.frame.setVisible(false);
			
			mainGame.name1.setText(p1.getName());
			mainGame.name2.setText(p2.getName());
			
			mainGame.p1c1.setText(getAttributes2(p1.getTeam().get(0)));
			int v1 = p1.getTeam().get(0).getCurrentHP()*100/p1.getTeam().get(0).getMaxHP();
			mainGame.bar1.setValue(v1);
			if (v1>70)
				mainGame.bar1.setForeground(Color.GREEN);
			if (v1<70 && v1>30)
				mainGame.bar1.setForeground(new Color(0xffcb00));
			if (v1<30)
				mainGame.bar1.setForeground(new Color(0xf30000));
			
			mainGame.p1c2.setText(getAttributes2(p1.getTeam().get(1)));
			int v2 = p1.getTeam().get(0).getCurrentHP()*100/p1.getTeam().get(1).getMaxHP();
			mainGame.bar2.setValue(v2);
			if (v2>70) 
				mainGame.bar2.setForeground(Color.GREEN);
			
			if (v2<70 && v2>30) 
				mainGame.bar2.setForeground(new Color(0xffcb00));
			if (v2<30) 
				mainGame.bar2.setForeground(new Color(0xf30000));
			
			mainGame.p1c3.setText(getAttributes2(p1.getTeam().get(2)));
			int v3 = p1.getTeam().get(0).getCurrentHP()*100/p1.getTeam().get(2).getMaxHP();
			mainGame.bar3.setValue(v3);
			if (v3>70) 
				mainGame.bar3.setForeground(Color.GREEN);
			
			if (v3<70 && v3>30) 
				mainGame.bar3.setForeground(new Color(0xffcb00));
			if (v3<30) 
				mainGame.bar3.setForeground(new Color(0xf30000));
			
			
			mainGame.p2c1.setText(getAttributes2(p2.getTeam().get(0)));
			int v4 = p2.getTeam().get(0).getCurrentHP()*100/p2.getTeam().get(0).getMaxHP();
			mainGame.bar4.setValue(v4);
			if (v4>70) 
				mainGame.bar4.setForeground(Color.GREEN);
			
			if (v4<70 && v4>30) 
				mainGame.bar4.setForeground(new Color(0xffcb00));
			if (v4<30) 
				mainGame.bar4.setForeground(new Color(0xf30000));
			
			mainGame.p2c2.setText(getAttributes2(p2.getTeam().get(1)));
			int v5 = p2.getTeam().get(1).getCurrentHP()*100/p2.getTeam().get(1).getMaxHP();
			mainGame.bar5.setValue(v5);
			if (v5>70)
				mainGame.bar5.setForeground(Color.GREEN);
			
			if (v5<70 && v5>30)
				mainGame.bar5.setForeground(new Color(0xffcb00));
			if (v5<30)
				mainGame.bar5.setForeground(new Color(0xf30000));
			
			mainGame.p2c3.setText(getAttributes2(p2.getTeam().get(2)));
			int v6 = p2.getTeam().get(2).getCurrentHP()*100/p2.getTeam().get(2).getMaxHP();
			mainGame.bar6.setValue(v6);
			if (v6>70) 
				mainGame.bar6.setForeground(Color.GREEN);
			
			if (v6<70 && v6>30) 
				mainGame.bar6.setForeground(new Color(0xffcb00));
			if (v6<30) 
				mainGame.bar6.setForeground(new Color(0xf30000));
			

			
			mainGame.board[0][1].setIcon(getChampImg(p1.getTeam().get(0)));
			mainGame.board[0][2].setIcon(getChampImg(p1.getTeam().get(1)));
			mainGame.board[0][3].setIcon(getChampImg(p1.getTeam().get(2)));
			mainGame.board[4][1].setIcon(getChampImg(p2.getTeam().get(0)));
			mainGame.board[4][2].setIcon(getChampImg(p2.getTeam().get(1)));
			mainGame.board[4][3].setIcon(getChampImg(p2.getTeam().get(2)));
			game = new Game(p1, p2);
			try {
				Game.loadAbilities("Abilities.csv");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				Game.loadChampions("Champions.csv");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			gameBoard = game.getBoard();
			for (int i = 4; i > -1; i--) {
				for(int j = 0; j < 5; j++) {
					if(gameBoard[i][j] instanceof Cover)
						mainGame.board[i][j].setIcon(CoverImg);
				}
			}
			
			while(!game.getTurnOrder().isEmpty())
				turnOrderAL.add((Champion) game.getTurnOrder().remove());
			
			for(int i = 0; i < turnOrderAL.size(); i++) {
				game.getTurnOrder().insert(turnOrderAL.get(i));
			}

			for(int i = 0; i < turnOrderAL.size(); i++) {
				JLabel l = new JLabel();
				l.setVisible(true);	
				l.setOpaque(false);
				l.setLayout(null);
				l.setBackground(new Color(0,0,0,0));
				l.setIcon(getChampIcon(turnOrderAL.get(i)));
				mainGame.turnOrderL.add(l);
			}
			
			for(int i = 0; i < mainGame.turnOrderL.size(); i++) {
				mainGame.turnOrderP.add(mainGame.turnOrderL.get(i));
			}
			currentChamp = game.getCurrentChampion();

			
			mainGame.ability1.setText(setAbilityDetails(currentChamp.getAbilities().get(0)));
			mainGame.ability2.setText(setAbilityDetails(currentChamp.getAbilities().get(1)));
			mainGame.ability3.setText(setAbilityDetails(currentChamp.getAbilities().get(2)));
			
			smSound.stopMusic();
			
			mgSound = new Sound();
			mgSound.runMusic("mainF.wav");
			
			mainGame.frame.setVisible(true);
		}

		/*MAIN GAME*/
		
		//selected target
		for (int i = 4; i > -1; i--) {
			for(int j = 0; j < 5; j++) {
				if(e.getSource() == mainGame.board[i][j]) {
					selectedTarget = (Damageable) gameBoard[i][j];
					String x = "";
					if(selectedTarget instanceof Cover)
						x = "Cover    " + "HP: " + selectedTarget.getCurrentHP();
					else if (selectedTarget instanceof Champion){
						Champion champ = (Champion) selectedTarget;
						x = champ.getName() + "    HP: " + champ.getCurrentHP() + "/" + champ.getMaxHP();
					}
					else
						x = "Empty Cell";
					mainGame.selectedTarget.setText("Selected Target: " + x);
				}
					
			}
		}
		
		//leader ability details
		if(e.getSource() == mainGame.leaderAbilityB2) {
			if(p1.getTeam().contains(currentChamp)) {
				if(p1.getLeader() instanceof Villain) {
					JOptionPane.showMessageDialog(null, "Immediately eliminates (knocks out) all enemy champions who have "
							+ "less than 30% health", p1.getName()+"'s " + "Leader Ability", JOptionPane.INFORMATION_MESSAGE);
				}
				if(p1.getLeader() instanceof Hero) {
					JOptionPane.showMessageDialog(null, "Removes all negative effects from this player’s entire team and adds an"
							+ " Embrace effect to them that lasts for 2 turns"
							, p1.getName()+"'s " + "Leader Ability", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "All champions on the board except for the leaders of each team will "
							+ "be stunned for 2 turns", p1.getName()+"'s " + "Leader Ability", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			if(p2.getTeam().contains(currentChamp)) {
				if(p1.getLeader() instanceof Villain) {
					JOptionPane.showMessageDialog(null, "Immediately eliminates (knocks out) all enemy champions who have "
							+ "less than 30% health", p2.getName()+"'s " + "Leader Ability", JOptionPane.INFORMATION_MESSAGE);
				}
				if(p1.getLeader() instanceof Hero) {
					JOptionPane.showMessageDialog(null, "Removes all negative effects from this player’s entire team and adds an"
							+ " Embrace effect to them that lasts for 2 turns"
							, p2.getName()+"'s " + "Leader Ability", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "All champions on the board except for the leaders of each team will "
							+ "be stunned for 2 turns", p2.getName()+"'s " + "Leader Ability", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		//direction buttons
		if (e.getSource() == mainGame.up) {
			mainGame.saved_direction.setText("up");
			direction = 1;
		}
		if (e.getSource() == mainGame.down) {
			mainGame.saved_direction.setText("down");
			direction = 2;
		}
		if (e.getSource() == mainGame.right) {
			mainGame.saved_direction.setText("right");
			direction = 3;
		}
		if (e.getSource() == mainGame.left) {
			mainGame.saved_direction.setText("left");
			direction = 4;
		}
		
		//move
		if(e.getSource() == mainGame.move) {
			x = currentChamp.getLocation().x;
			y = currentChamp.getLocation().y;
			try {
				if (direction == 1) {
					game.move(Direction.UP);
					updatecurrent(currentChamp);
					mainGame.board[x+1][y].setIcon(mainGame.board[x][y].getIcon());
					mainGame.board[x][y].setIcon(null);
					playMoveSound();
				}
				else if (direction == 2) {
					game.move(Direction.DOWN);
					updatecurrent(currentChamp);
					mainGame.board[x-1][y].setIcon(mainGame.board[x][y].getIcon());
					mainGame.board[x][y].setIcon(null);
					playMoveSound();
				}
				else if (direction == 3) {
					game.move(Direction.RIGHT);
					updatecurrent(currentChamp);
					mainGame.board[x][y+1].setIcon(mainGame.board[x][y].getIcon());
					mainGame.board[x][y].setIcon(null);
					playMoveSound();
				}
				else if (direction == 4) {
					game.move(Direction.LEFT);
					updatecurrent(currentChamp);
					mainGame.board[x][y-1].setIcon(mainGame.board[x][y].getIcon());
					mainGame.board[x][y].setIcon(null);
					playMoveSound();
				}
				else
					JOptionPane.showMessageDialog(null, "You did not choose a direction", "Error", JOptionPane.ERROR_MESSAGE);
			} 
			catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} 
			catch (UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		//attack
		if (e.getSource() == mainGame.attack) {
			try {
				if (direction == 1) {
					ArrayList<Damageable> en=new ArrayList<Damageable>();
					Champion c=(Champion) game.getTurnOrder().peekMin();
					int range=c.getAttackRange();
					int x=c.getLocation().x;
					int y=c.getLocation().y;
					for (int i = 0; i < range; i++){
						 x++;
						if( x>4){
							break;
						}
						if(game.getBoard()[x][y]!=null){
							Damageable t= (Damageable)game.getBoard()[x][y] ;
							if(game.getBoard()[x][y] instanceof Cover){
								
								en.add(t);
								break;
							}
							if(game.getBoard()[x][y] instanceof Champion){
								Champion cha=(Champion) game.getBoard()[x][y];
								if((game.getFirstPlayer().getTeam().contains(c) && game.getSecondPlayer().getTeam().contains(cha)) || (game.getFirstPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))){
									en.add(t);
									break;
								}
								
							}
						}
					}
					game.attack(Direction.UP);
					playAttackSound();
					updateViewAttack();
					updatelabels(en);
					updatecurrent(c);
					gameOverCheck();
				}
				
				else if (direction == 2) {
					ArrayList<Damageable> en=new ArrayList<Damageable>();
					Champion c=(Champion) game.getTurnOrder().peekMin();
					int range=c.getAttackRange();
					int x=c.getLocation().x;
					int y=c.getLocation().y;
					for (int i = 0; i < range; i++){
						 x--;
						if(x<0){
							break;
						}
						if(game.getBoard()[x][y]!=null){
							Damageable t= (Damageable)game.getBoard()[x][y] ;
							if(game.getBoard()[x][y] instanceof Cover){
								
								en.add(t);
								break;
							}
							if(game.getBoard()[x][y] instanceof Champion){
								Champion cha=(Champion) game.getBoard()[x][y];
								if((game.getFirstPlayer().getTeam().contains(c) && game.getSecondPlayer().getTeam().contains(cha)) || (game.getFirstPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))){
									en.add(t);
									break;
								}
								
							}
						}
					}
					game.attack(Direction.DOWN);
					playAttackSound();
					updateViewAttack();
					updatelabels(en);
					updatecurrent(c);
					gameOverCheck();
				}
				else if (direction == 3) {
					ArrayList<Damageable> en=new ArrayList<Damageable>();
					Champion c=(Champion) game.getTurnOrder().peekMin();
					int range=c.getAttackRange();
					int x=c.getLocation().x;
					int y=c.getLocation().y;
					for (int i = 0; i < range; i++){
						 y++;
						if( y>4){
							break;
						}
						if(game.getBoard()[x][y]!=null){
							Damageable t= (Damageable)game.getBoard()[x][y] ;
							if(game.getBoard()[x][y] instanceof Cover){
								
								en.add(t);
								break;
							}
							if(game.getBoard()[x][y] instanceof Champion){
								Champion cha=(Champion) game.getBoard()[x][y];
								if((game.getFirstPlayer().getTeam().contains(c) && game.getSecondPlayer().getTeam().contains(cha)) || (game.getFirstPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))){
									en.add(t);
									break;
								}
								
							}
						}
					}
					game.attack(Direction.RIGHT);
					playAttackSound();
					updateViewAttack();
					updatelabels(en);
					updatecurrent(c);
					gameOverCheck();
				}
				else if (direction == 4) {
					ArrayList<Damageable> en=new ArrayList<Damageable>();
					Champion c=(Champion) game.getTurnOrder().peekMin();
					int range=c.getAttackRange();
					int x=c.getLocation().x;
					int y=c.getLocation().y;
					for (int i = 0; i < range; i++){
						 y--;
						if( y<0){
							break;
						}
						if(game.getBoard()[x][y]!=null){
							Damageable t= (Damageable)game.getBoard()[x][y] ;
							if(game.getBoard()[x][y] instanceof Cover){
								
								en.add(t);
								break;
							}
							if(game.getBoard()[x][y] instanceof Champion){
								Champion cha=(Champion) game.getBoard()[x][y];
								if((game.getFirstPlayer().getTeam().contains(c) && game.getSecondPlayer().getTeam().contains(cha)) || (game.getFirstPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))){
									en.add(t);
									break;
								}
								
							}
						}
					}
					game.attack(Direction.LEFT);
					playAttackSound();
					updateViewAttack();
					updatelabels(en);
					updatecurrent(c);
					gameOverCheck();
				}
				else
					JOptionPane.showMessageDialog(null, "You did not choose a direction", "Error", JOptionPane.ERROR_MESSAGE);
			} 
			catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} 
			catch (ChampionDisarmedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} 
			catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}

		}
		
		//casting abilities
		if(e.getSource() == mainGame.ability1B) {
			Ability a = currentChamp.getAbilities().get(0);
			try {
			if(a.getCastArea().equals(AreaOfEffect.DIRECTIONAL)) {
				if(direction == 1) {
					ArrayList<Damageable> en=new ArrayList<Damageable>();
					Champion c=(Champion) game.getTurnOrder().peekMin();
					int range=a.getCastRange();
					int x=c.getLocation().x;
					int y=c.getLocation().y;
					for (int i = 0; i < range; i++){
						 x++;
						if( x>4){
							break;
						}
						
						if(game.getBoard()[x][y]!=null){
							Damageable t= (Damageable)game.getBoard()[x][y] ;
						  if(a instanceof DamagingAbility) {	
							
							if(game.getBoard()[x][y] instanceof Cover){
								
								en.add(t);
								
							}
						  
						 
							 
							 if(t instanceof Champion){
								Champion cha=(Champion)t;
								
								if((game.getFirstPlayer().getTeam().contains(c) && game.getSecondPlayer().getTeam().contains(cha)) || (game.getFirstPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))){
									en.add(t);
									
								}
								
							}
						  }
						  else if((a instanceof CrowdControlAbility && ((CrowdControlAbility) a).getEffect().getType() == EffectType.DEBUFF )){
							  if(t instanceof Champion){
									Champion cha=(Champion)t;
									
									if((game.getFirstPlayer().getTeam().contains(c) && game.getSecondPlayer().getTeam().contains(cha)) || (game.getFirstPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))){
										en.add(t);
										
									}
									
								}
						  }
						  
						  else if(a instanceof HealingAbility || (a instanceof CrowdControlAbility && ((CrowdControlAbility) a).getEffect().getType() == EffectType.BUFF )){
							  if(game.getBoard()[x][y] instanceof Champion) {  
								  Champion cha=(Champion) game.getBoard()[x][y];
							    if( (game.getFirstPlayer().getTeam().contains(c) && game.getFirstPlayer().getTeam().contains(cha)) || (game.getSecondPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))) {
							    	en.add(t);
							    }
							  }
						  
						  }
						
					  }
					}
					game.castAbility(a, Direction.UP);
					playCastSound();
					updateViewAttack();
					updatelabels(en);
					updatecurrent(c);
					gameOverCheck();
					
				}
				else if(direction == 2) {
					ArrayList<Damageable> en=new ArrayList<Damageable>();
					Champion c=(Champion) game.getTurnOrder().peekMin();
					int range=a.getCastRange();
					int x=c.getLocation().x;
					int y=c.getLocation().y;
					for (int i = 0; i < range; i++){
						 x--;
						if( x<0){
							break;
						}
						
						if(game.getBoard()[x][y]!=null){
							Damageable t= (Damageable)game.getBoard()[x][y] ;
						  if(a instanceof DamagingAbility) {	
							
							if(game.getBoard()[x][y] instanceof Cover){
								
								en.add(t);
								
							}
						  
						 
							 
							 if(t instanceof Champion){
								Champion cha=(Champion)t;
								
								if((game.getFirstPlayer().getTeam().contains(c) && game.getSecondPlayer().getTeam().contains(cha)) || (game.getFirstPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))){
									en.add(t);
									
								}
								
							}
						  }
						  else if((a instanceof CrowdControlAbility && ((CrowdControlAbility) a).getEffect().getType() == EffectType.DEBUFF )){
							  if(t instanceof Champion){
									Champion cha=(Champion)t;
									
									if((game.getFirstPlayer().getTeam().contains(c) && game.getSecondPlayer().getTeam().contains(cha)) || (game.getFirstPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))){
										en.add(t);
										
									}
									
								}
						  }
						  
						  else if(a instanceof HealingAbility || (a instanceof CrowdControlAbility && ((CrowdControlAbility) a).getEffect().getType() == EffectType.BUFF )){
							  if(game.getBoard()[x][y] instanceof Champion) {  
								  Champion cha=(Champion) game.getBoard()[x][y];
							    if( (game.getFirstPlayer().getTeam().contains(c) && game.getFirstPlayer().getTeam().contains(cha)) || (game.getSecondPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))) {
							    	en.add(t);
							    }
							  }
						  
						  }
						
					  }
					}
					game.castAbility(a, Direction.DOWN);
					playCastSound();
					updateViewAttack();
					updatelabels(en);
					updatecurrent(c);
					gameOverCheck();
				}
				else if(direction == 3) {
					ArrayList<Damageable> en=new ArrayList<Damageable>();
					Champion c=(Champion) game.getTurnOrder().peekMin();
					int range=a.getCastRange();
					int x=c.getLocation().x;
					int y=c.getLocation().y;
					for (int i = 0; i < range; i++){
						 y++;
						if( y>4){
							break;
						}
						
						if(game.getBoard()[x][y]!=null){
							Damageable t= (Damageable)game.getBoard()[x][y] ;
						  if(a instanceof DamagingAbility) {	
							
							if(game.getBoard()[x][y] instanceof Cover){
								
								en.add(t);
								
							}
						  
						 
							 
							 if(t instanceof Champion){
								Champion cha=(Champion)t;
								
								if((game.getFirstPlayer().getTeam().contains(c) && game.getSecondPlayer().getTeam().contains(cha)) || (game.getFirstPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))){
									en.add(t);
									
								}
								
							}
						  }
						  else if((a instanceof CrowdControlAbility && ((CrowdControlAbility) a).getEffect().getType() == EffectType.DEBUFF )){
							  if(t instanceof Champion){
									Champion cha=(Champion)t;
									
									if((game.getFirstPlayer().getTeam().contains(c) && game.getSecondPlayer().getTeam().contains(cha)) || (game.getFirstPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))){
										en.add(t);
										
									}
									
								}
						  }
						  
						  else if(a instanceof HealingAbility || (a instanceof CrowdControlAbility && ((CrowdControlAbility) a).getEffect().getType() == EffectType.BUFF )){
							  if(game.getBoard()[x][y] instanceof Champion) {  
								  Champion cha=(Champion) game.getBoard()[x][y];
							    if( (game.getFirstPlayer().getTeam().contains(c) && game.getFirstPlayer().getTeam().contains(cha)) || (game.getSecondPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))) {
							    	en.add(t);
							    }
							  }
						  
						  }
						
					  }
					}
					game.castAbility(a, Direction.RIGHT);
					playCastSound();
					updateViewAttack();
					updatelabels(en);
					updatecurrent(c);
					gameOverCheck();
				}
				else if(direction == 4) {
					ArrayList<Damageable> en=new ArrayList<Damageable>();
					Champion c=(Champion) game.getTurnOrder().peekMin();
					int range=a.getCastRange();
					int x=c.getLocation().x;
					int y=c.getLocation().y;
					for (int i = 0; i < range; i++){
						 y--;
						if( y<0){
							break;
						}
						
						if(game.getBoard()[x][y]!=null){
							Damageable t= (Damageable)game.getBoard()[x][y] ;
						  if(a instanceof DamagingAbility) {	
							
							if(game.getBoard()[x][y] instanceof Cover){
								
								en.add(t);
								
							}
						  
						 
							 
							 if(t instanceof Champion){
								Champion cha=(Champion)t;
								
								if((game.getFirstPlayer().getTeam().contains(c) && game.getSecondPlayer().getTeam().contains(cha)) || (game.getFirstPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))){
									en.add(t);
									
								}
								
							}
						  }
						  else if((a instanceof CrowdControlAbility && ((CrowdControlAbility) a).getEffect().getType() == EffectType.DEBUFF )){
							  if(t instanceof Champion){
									Champion cha=(Champion)t;
									
									if((game.getFirstPlayer().getTeam().contains(c) && game.getSecondPlayer().getTeam().contains(cha)) || (game.getFirstPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))){
										en.add(t);
										
									}
									
								}
						  }
						  
						  else if(a instanceof HealingAbility || (a instanceof CrowdControlAbility && ((CrowdControlAbility) a).getEffect().getType() == EffectType.BUFF )){
							  if(game.getBoard()[x][y] instanceof Champion) {  
								  Champion cha=(Champion) game.getBoard()[x][y];
							    if( (game.getFirstPlayer().getTeam().contains(c) && game.getFirstPlayer().getTeam().contains(cha)) || (game.getSecondPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))) {
							    	en.add(t);
							    }
							  }
						  
						  }
						
					  }
					}
					game.castAbility(a, Direction.LEFT);
					playCastSound();
					updateViewAttack();
					updatelabels(en);
					updatecurrent(c);
					gameOverCheck();
				}
					
				else
					JOptionPane.showMessageDialog(null, "You did not choose a direction", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(a.getCastArea().equals(AreaOfEffect.SINGLETARGET)) {
				if(selectedTarget == null)
					JOptionPane.showMessageDialog(null, "Invaild Target", "Error", JOptionPane.ERROR_MESSAGE);
				else {
					ArrayList<Damageable> en=new ArrayList<Damageable>();
					Champion c=(Champion) game.getTurnOrder().peekMin();
					en.add((Damageable) game.getBoard()[selectedTarget.getLocation().x][selectedTarget.getLocation().y]);
					game.castAbility(a, selectedTarget.getLocation().x, selectedTarget.getLocation().y);
					playCastSound();
					updateViewAttack();
					updatelabels(en);
					updatecurrent(c);
					gameOverCheck();
				}
				     
				}
			else {
				if(a.getCastArea()==AreaOfEffect.TEAMTARGET) {
					ArrayList<Damageable> en=new ArrayList<Damageable>();
					Champion c=(Champion) game.getTurnOrder().peekMin();
					for(int i=0;i<game.getFirstPlayer().getTeam().size();i++) {
						en.add(game.getFirstPlayer().getTeam().get(i));
					}
					for(int i=0;i<game.getSecondPlayer().getTeam().size();i++) {
						en.add(game.getSecondPlayer().getTeam().get(i));
					}
					game.castAbility(a);
					playCastSound();
					updateViewAttack();
					updatelabels(en);
					updatecurrent(c);
					gameOverCheck();
				}

			   if(a.getCastArea()==AreaOfEffect.SELFTARGET) {
				   ArrayList<Damageable> en=new ArrayList<Damageable>();
	                en.add(currentChamp);
				 game.castAbility(a);
				 playCastSound();
				 updatecurrent(currentChamp);
				 updatelabels(en);
				 gameOverCheck();
			   }

			  if(a.getCastArea()==AreaOfEffect.SURROUND) {
				  ArrayList<Damageable> en=new ArrayList<Damageable>();
					Champion c=(Champion) game.getTurnOrder().peekMin();
				  for (int i =4; i > -1; i--) {
						for(int j = 0; j < 5; j++) {
							if(game.getBoard()[i][j]!=null) {		
								en.add((Damageable) game.getBoard()[i][j]);
							}
						}
					}
				game.castAbility(a);
				playCastSound();
				updateViewAttack();
				updatelabels(en);
				updatecurrent(c);
				gameOverCheck();
				
			  }
			}
				
			}
			catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} 
			catch (AbilityUseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			catch (CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(e.getSource() == mainGame.ability2B) {
			Ability a = currentChamp.getAbilities().get(1);
			try {
				if(a.getCastArea().equals(AreaOfEffect.DIRECTIONAL)) {
					if(direction == 1) {
					ArrayList<Damageable> en=new ArrayList<Damageable>();
					Champion c=(Champion) game.getTurnOrder().peekMin();
					int range=a.getCastRange();
					int x=c.getLocation().x;
					int y=c.getLocation().y;
					for (int i = 0; i < range; i++){
						 x++;
						if( x>4){
							break;
						}
						
						if(game.getBoard()[x][y]!=null){
							Damageable t= (Damageable)game.getBoard()[x][y] ;
						  if(a instanceof DamagingAbility) {	
						
							if(game.getBoard()[x][y] instanceof Cover){
								
								en.add(t);
								
							}
						  
						 
							 
							if(t instanceof Champion){
								Champion cha=(Champion)t;
								if((game.getFirstPlayer().getTeam().contains(c) && game.getSecondPlayer().getTeam().contains(cha)) || (game.getFirstPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))){
									en.add(t);
								}
								
							}
						  }
						  
						  else if(a instanceof HealingAbility || (a instanceof CrowdControlAbility && ((CrowdControlAbility) a).getEffect().getType() == EffectType.BUFF )){
							  if(game.getBoard()[x][y] instanceof Champion) {  
								  Champion cha=(Champion) game.getBoard()[x][y];
							    if( (game.getFirstPlayer().getTeam().contains(c) && game.getFirstPlayer().getTeam().contains(cha)) || (game.getSecondPlayer().getTeam().contains(cha) && game.getSecondPlayer().getTeam().contains(c))) {
							    	en.add(t);
							    }
							  }
						  
						  }
						
					  }
					}
					game.castAbility(a, Direction.UP);
					playCastSound();
					updateViewAttack();
					updatelabels(en);
					updatecurrent(currentChamp);
					gameOverCheck();
					}
					else if(direction == 2)
						game.castAbility(a, Direction.DOWN);
					else if(direction == 3)
						game.castAbility(a, Direction.RIGHT);
					else if(direction == 4)
						game.castAbility(a, Direction.LEFT);
					else
						JOptionPane.showMessageDialog(null, "You did not choose a direction", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(a.getCastArea().equals(AreaOfEffect.SINGLETARGET)) {
					if(selectedTarget == null)
						JOptionPane.showMessageDialog(null, "Invaild Target", "Error", JOptionPane.ERROR_MESSAGE);
					else {
						ArrayList<Damageable> en=new ArrayList<Damageable>();
						Champion c=(Champion) game.getTurnOrder().peekMin();
						en.add((Damageable) game.getBoard()[selectedTarget.getLocation().x][selectedTarget.getLocation().y]);
						game.castAbility(a, selectedTarget.getLocation().x, selectedTarget.getLocation().y);
						updateViewAttack();
						updatelabels(en);
						updatecurrent(c);
						gameOverCheck();
					}
					     
					}
				else {
					if(a.getCastArea()==AreaOfEffect.TEAMTARGET) {
						ArrayList<Damageable> en=new ArrayList<Damageable>();
						Champion c=(Champion) game.getTurnOrder().peekMin();
						for(int i=0;i<game.getFirstPlayer().getTeam().size();i++) {
							en.add(game.getFirstPlayer().getTeam().get(i));
						}
						for(int i=0;i<game.getSecondPlayer().getTeam().size();i++) {
							en.add(game.getSecondPlayer().getTeam().get(i));
						}
						game.castAbility(a);
						updateViewAttack();
						updatelabels(en);
						updatecurrent(c);
						gameOverCheck();
					}

				   if(a.getCastArea()==AreaOfEffect.SELFTARGET) {
					   ArrayList<Damageable> en=new ArrayList<Damageable>();
		                en.add(currentChamp);
					 game.castAbility(a);
					 playCastSound();
					 updatecurrent(currentChamp);
					updatelabels(en);
					gameOverCheck();
				   }

				   if(a.getCastArea()==AreaOfEffect.SURROUND) {
						  ArrayList<Damageable> en=new ArrayList<Damageable>();
							Champion c=(Champion) game.getTurnOrder().peekMin();
						  for (int i =4; i > -1; i--) {
								for(int j = 0; j < 5; j++) {
									if(game.getBoard()[i][j]!=null) {		
										en.add((Damageable) game.getBoard()[i][j]);
									}
								}
							}
						game.castAbility(a);
						playCastSound();
						updateViewAttack();
						updatelabels(en);
						updatecurrent(c);
						gameOverCheck();
						
					  }
				}

			}
			catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} 
			catch (AbilityUseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			catch (CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(e.getSource() == mainGame.ability3B) {
			Ability a = currentChamp.getAbilities().get(2);
			try {
				if(a.getCastArea().equals(AreaOfEffect.DIRECTIONAL)) {
					if(direction == 1) 
						game.castAbility(a, Direction.UP);
					else if(direction == 2)
						game.castAbility(a, Direction.DOWN);
					else if(direction == 3)
						game.castAbility(a, Direction.RIGHT);
					else if(direction == 4)
						game.castAbility(a, Direction.LEFT);
					else
						JOptionPane.showMessageDialog(null, "You did not choose a direction", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(a.getCastArea().equals(AreaOfEffect.SINGLETARGET)) {
					if(selectedTarget == null)
						JOptionPane.showMessageDialog(null, "Invaild Target", "Error", JOptionPane.ERROR_MESSAGE);
					else {
						ArrayList<Damageable> en=new ArrayList<Damageable>();
						Champion c=(Champion) game.getTurnOrder().peekMin();
						en.add((Damageable) game.getBoard()[selectedTarget.getLocation().x][selectedTarget.getLocation().y]);
						game.castAbility(a, selectedTarget.getLocation().x, selectedTarget.getLocation().y);
						playCastSound();
						updateViewAttack();
						updatelabels(en);
						updatecurrent(c);
					}
					     
					}
				else {
					if(a.getCastArea()==AreaOfEffect.TEAMTARGET) {
						ArrayList<Damageable> en=new ArrayList<Damageable>();
						Champion c=(Champion) game.getTurnOrder().peekMin();
						for(int i=0;i<game.getFirstPlayer().getTeam().size();i++) {
							en.add(game.getFirstPlayer().getTeam().get(i));
						}
						for(int i=0;i<game.getSecondPlayer().getTeam().size();i++) {
							en.add(game.getSecondPlayer().getTeam().get(i));
						}
						game.castAbility(a);
						playCastSound();
						updateViewAttack();
						updatelabels(en);
						updatecurrent(c);
					}

				   if(a.getCastArea()==AreaOfEffect.SELFTARGET) {
					   ArrayList<Damageable> en=new ArrayList<Damageable>();
		                en.add(currentChamp);
					 game.castAbility(a);
					 playCastSound();
					 updatecurrent(currentChamp);
					updatelabels(en);
				   }

				   if(a.getCastArea()==AreaOfEffect.SURROUND) {
						  ArrayList<Damageable> en=new ArrayList<Damageable>();
							Champion c=(Champion) game.getTurnOrder().peekMin();
						  for (int i =4; i > -1; i--) {
								for(int j = 0; j < 5; j++) {
									if(game.getBoard()[i][j]!=null) {		
										en.add((Damageable) game.getBoard()[i][j]);
									}
								}
							}
						game.castAbility(a);
						playCastSound();
						updateViewAttack();
						updatelabels(en);
						updatecurrent(c);
						
					  }
				}
			}
			catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} 
			catch (AbilityUseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			catch (CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		//casting leader ability
		if(e.getSource() == mainGame.leaderAbilityB) {
			try {
				ArrayList<Damageable> en=new ArrayList<Damageable>();
				Champion c=(Champion) game.getTurnOrder().peekMin();
			     for (int i =4; i > -1; i--) {
					for(int j = 0; j < 5; j++) {
						if(game.getBoard()[i][j]!=null) {		
							en.add((Damageable) game.getBoard()[i][j]);
						}
					}
				}
			    if(p1.getTeam().contains(currentChamp)) {
			    	mainGame.leaderAbility1Used.setText("Leader Ability: Used");
			    }
			    if(p2.getTeam().contains(currentChamp)) {
			    	mainGame.leaderAbility2Used.setText("Leader Ability: Used");
			    }
				game.useLeaderAbility();
				playCastSound();
				updateViewAttack();
				updatelabels(en);
				updatecurrent(c);
			} 
			catch (LeaderNotCurrentException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} 
			catch (LeaderAbilityAlreadyUsedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		//end turn
		if(e.getSource() == mainGame.endTurnB) {
			game.endTurn();
			currentChamp = game.getCurrentChampion();
			updateViewEndTurn();
			  ArrayList<Damageable> en=new ArrayList<Damageable>();
              en.add(currentChamp);
			updatelabels(en);
			updatecurrent(currentChamp);
			updateAbilities();
		}


	
		
	}
	
	
	
	//helper methods
	
	public void gameOverCheck() {
		if(game.checkGameOver() != null) {
			JOptionPane.showMessageDialog(mainGame.frame, game.checkGameOver().getName() + " Has Won The Game!", "Game Over", JOptionPane.PLAIN_MESSAGE);
			mainGame.frame.dispose();
			System.exit(0);
		}
	}
	
	public void updatecurrent(Champion c) {
		if(c.getName()==c1.getName()) {
			mainGame.p1c1.setText(getAttributes2(c));
			int v= c.getCurrentHP()*100/c.getMaxHP();
			mainGame.bar1.setValue(v);
			if (v>70)
				mainGame.bar1.setForeground(Color.GREEN);
			if (v<70 && v>30)
				mainGame.bar1.setForeground(new Color(0xffcb00));
			if (v<30)
				mainGame.bar1.setForeground(new Color(0xf30000));
		}
		if(c.getName()==c2.getName()) {
			mainGame.p1c2.setText(getAttributes2(c));
			int v= c.getCurrentHP()*100/c.getMaxHP();
			mainGame.bar2.setValue(v);
			if (v>70)
				mainGame.bar2.setForeground(Color.GREEN);
			if (v<70 && v>30)
				mainGame.bar2.setForeground(new Color(0xffcb00));
			if (v<30)
				mainGame.bar2.setForeground(new Color(0xf30000));
		}
		if(c.getName()==c3.getName()) {
			mainGame.p1c3.setText(getAttributes2(c));
			int v= c.getCurrentHP()*100/c.getMaxHP();
			mainGame.bar3.setValue(v);
			if (v>70)
				mainGame.bar3.setForeground(Color.GREEN);
			if (v<70 && v>30)
				mainGame.bar3.setForeground(new Color(0xffcb00));
			if (v<30)
				mainGame.bar3.setForeground(new Color(0xf30000));
		}
		if(c.getName()==c4.getName()) {
			mainGame.p2c1.setText(getAttributes2(c));
			int v= c.getCurrentHP()*100/c.getMaxHP();
			mainGame.bar4.setValue(v);
			if (v>70)
				mainGame.bar4.setForeground(Color.GREEN);
			if (v<70 && v>30)
				mainGame.bar4.setForeground(new Color(0xffcb00));
			if (v<30)
				mainGame.bar4.setForeground(new Color(0xf30000));
		}
		if(c.getName()==c5.getName()) {
			mainGame.p2c2.setText(getAttributes2(c));
			int v= c.getCurrentHP()*100/c.getMaxHP();
			mainGame.bar5.setValue(v);
			if (v>70)
				mainGame.bar5.setForeground(Color.GREEN);
			if (v<70 && v>30)
				mainGame.bar5.setForeground(new Color(0xffcb00));
			if (v<30)
				mainGame.bar5.setForeground(new Color(0xf30000));
		}
		if(c.getName()==c6.getName()) {
			mainGame.p2c3.setText(getAttributes2(c));
			int v= c.getCurrentHP()*100/c.getMaxHP();
			mainGame.bar6.setValue(v);
			if (v>70)
				mainGame.bar6.setForeground(Color.GREEN);
			if (v<70 && v>30)
				mainGame.bar6.setForeground(new Color(0xffcb00));
			if (v<30)
				mainGame.bar6.setForeground(new Color(0xf30000));
		}
	}
	public void updatelabels(ArrayList<Damageable> en) {
		for(int i=0;i<en.size();i++) {
			Damageable e = en.get(i);
			if(e instanceof Champion) {
				Champion r=(Champion) e;
				if(r.getName()==c1.getName()) {
					mainGame.p1c1.setText(getAttributes2(r));
					int v= r.getCurrentHP()*100/r.getMaxHP();
					mainGame.bar1.setValue(v);
					if (v>70)
						mainGame.bar1.setForeground(Color.GREEN);
					if (v<70 && v>30)
						mainGame.bar1.setForeground(new Color(0xffcb00));
					if (v<30)
						mainGame.bar1.setForeground(new Color(0xf30000));
					mainGame.p1c1ae.setText(setAppliedEffects(r));
				}
				if(r.getName()==c2.getName()) {
					mainGame.p1c2.setText(getAttributes2(r));
					int v= r.getCurrentHP()*100/r.getMaxHP();
					mainGame.bar2.setValue(v);
					if (v>70)
						mainGame.bar2.setForeground(Color.GREEN);
					if (v<70 && v>30)
						mainGame.bar2.setForeground(new Color(0xffcb00));
					if (v<30)
						mainGame.bar2.setForeground(new Color(0xf30000));
					mainGame.p1c2ae.setText(setAppliedEffects(r));
				}
				if(r.getName()==c3.getName()) {
					mainGame.p1c3.setText(getAttributes2(r));
					int v= r.getCurrentHP()*100/r.getMaxHP();
					mainGame.bar3.setValue(v);
					if (v>70)
						mainGame.bar3.setForeground(Color.GREEN);
					if (v<70 && v>30)
						mainGame.bar3.setForeground(new Color(0xffcb00));
					if (v<30)
						mainGame.bar3.setForeground(new Color(0xf30000));
					mainGame.p1c3ae.setText(setAppliedEffects(r));
				}
				if(r.getName()==c4.getName()) {
					mainGame.p2c1.setText(getAttributes2(r));
					int v= r.getCurrentHP()*100/r.getMaxHP();
					mainGame.bar4.setValue(v);
					if (v>70)
						mainGame.bar4.setForeground(Color.GREEN);
					if (v<70 && v>30)
						mainGame.bar4.setForeground(new Color(0xffcb00));
					if (v<30)
						mainGame.bar4.setForeground(new Color(0xf30000));
					mainGame.p2c1ae.setText(setAppliedEffects(r));
				}
				if(r.getName()==c5.getName()) {
					mainGame.p2c2.setText(getAttributes2(r));
					int v= r.getCurrentHP()*100/r.getMaxHP();
					mainGame.bar5.setValue(v);
					if (v>70)
						mainGame.bar5.setForeground(Color.GREEN);
					if (v<70 && v>30)
						mainGame.bar5.setForeground(new Color(0xffcb00));
					if (v<30)
						mainGame.bar5.setForeground(new Color(0xf30000));
					mainGame.p2c2ae.setText(setAppliedEffects(r));
				}
				if(r.getName()==c6.getName()) {
					mainGame.p2c3.setText(getAttributes2(r));
					int v= r.getCurrentHP()*100/r.getMaxHP();
					mainGame.bar6.setValue(v);
					if (v>70)
						mainGame.bar6.setForeground(Color.GREEN);
					if (v<70 && v>30)
						mainGame.bar6.setForeground(new Color(0xffcb00));
					if (v<30)
						mainGame.bar6.setForeground(new Color(0xf30000));
					mainGame.p2c3ae.setText(setAppliedEffects(r));
				}
			}
		}
	}
	
	public void playAttackSound(){
		attSound = new Sound2();
		attSound.runMusic("attackF.wav");
	}
	public void playMoveSound(){
		moveSound = new Sound2();
		moveSound.runMusic("moveF.wav");
	}
	public void playCastSound(){
		caSound = new Sound2();
		caSound.runMusic("castF.wav");
	}

	public void updateViewAttack() {
		
		for (int i = 4; i > -1; i--) {
			for(int j = 0; j < 5; j++) {
				if(gameBoard[i][j] == null && mainGame.board[i][j].getIcon() != null)
					mainGame.board[i][j].setIcon(null);				
			}
		}
		
		String x = "";
		if(selectedTarget instanceof Cover)
			x = "Cover    " + "HP: " + selectedTarget.getCurrentHP();
		else if (selectedTarget instanceof Champion){
			Champion champ = (Champion) selectedTarget;
			x = champ.getName() + "    HP: " + champ.getCurrentHP() + "/" + champ.getMaxHP();
		}
		else
			x = "Empty Cell";
		mainGame.selectedTarget.setText("Selected Target: " + x);

		
	}
	
	public void updateViewEndTurn() {
		int counter =0;
		while(counter!=mainGame.turnOrderP.getComponentCount()) {
			JLabel d=(JLabel)mainGame.turnOrderP.getComponent(counter);
			d.setIcon(null);
			
			counter++;
		}
		for(int i = turnOrderAL.size()-1; i >= 0; i--) {
			turnOrderAL.remove(i);
		}
		
		for(int i = mainGame.turnOrderL.size()-1; i >= 0; i--) {
			mainGame.turnOrderL.remove(i);
		}
		
//		mainGame.turnOrderP.removeAll();
//		for(int i = 0; i < mainGame.turnOrderL.size(); i++) {
//			mainGame.turnOrderP.remove(mainGame.turnOrderP.getComponent(i));
//		}
//		
		while(!game.getTurnOrder().isEmpty())
			turnOrderAL.add((Champion) game.getTurnOrder().remove());
		
		for(int i = 0; i < turnOrderAL.size(); i++) {
			game.getTurnOrder().insert(turnOrderAL.get(i));
		}
		
		for(int i = 0; i < turnOrderAL.size(); i++) {
			JLabel d=(JLabel)mainGame.turnOrderP.getComponent(i);
			d.setBackground(new Color(0,0,0,0));
			d.setOpaque(false);
			d.setIcon(getChampIcon(turnOrderAL.get(i)));
		}
		
//		for(int i = 0; i < mainGame.turnOrderL.size(); i++) {
//			mainGame.turnOrderP.add(mainGame.turnOrderL.get(i));
//		}
	}
	public void createTargets() {
		
	}
	
	public void updateAbilities(){
		mainGame.ability1.setText(setAbilityDetails(currentChamp.getAbilities().get(0)));
		mainGame.ability2.setText(setAbilityDetails(currentChamp.getAbilities().get(1)));
		mainGame.ability3.setText(setAbilityDetails(currentChamp.getAbilities().get(2)));
	}
	
	
	
	public void updateChampAttributes() {
		if(!turnOrderAL.contains(c1))
			c1 = null;
		if(!turnOrderAL.contains(c2))
			c2 = null;
		if(!turnOrderAL.contains(c3))
			c3 = null;
		if(!turnOrderAL.contains(c4))
			c4 = null;
		if(!turnOrderAL.contains(c5))
			c5 = null;
		if(!turnOrderAL.contains(c6))
			c6 = null;
		
		if(c1 == null) {
			mainGame.p1c1.setVisible(false);
			mainGame.frame.remove(mainGame.p1c1);
		}
		else {
			mainGame.p1c1.setText(getAttributes2(p1.getTeam().get(0)));
			int v = p1.getTeam().get(0).getCurrentHP()*100/p1.getTeam().get(0).getMaxHP();
			mainGame.bar1.setValue(v);
			if (v>70)
				mainGame.bar1.setForeground(Color.GREEN);
			if (v<70 && v>30)
				mainGame.bar1.setForeground(new Color(0xffcb00));
			if (v<30)
				mainGame.bar1.setForeground(new Color(0xf30000));
		}
		
		
		mainGame.p1c1.setText(getAttributes2(p1.getTeam().get(0)));
		
		int v1 = p1.getTeam().get(0).getCurrentHP()*100/p1.getTeam().get(0).getMaxHP();
		mainGame.bar1.setValue(v1);
		if (v1>70)
			mainGame.bar1.setForeground(Color.GREEN);
		if (v1<70 && v1>30)
			mainGame.bar1.setForeground(new Color(0xffcb00));
		if (v1<30)
			mainGame.bar1.setForeground(new Color(0xf30000));
		
		mainGame.p1c2.setText(getAttributes2(p1.getTeam().get(1)));
		
		int v2 = p1.getTeam().get(0).getCurrentHP()*100/p1.getTeam().get(1).getMaxHP();
		mainGame.bar2.setValue(v2);
		if (v2>70) 
			mainGame.bar2.setForeground(Color.GREEN);
		if (v2<70 && v2>30) 
			mainGame.bar2.setForeground(new Color(0xffcb00));
		if (v2<30) 
			mainGame.bar2.setForeground(new Color(0xf30000));
		
		mainGame.p1c3.setText(getAttributes2(p1.getTeam().get(2)));
		
		int v3 = p1.getTeam().get(0).getCurrentHP()*100/p1.getTeam().get(2).getMaxHP();
		mainGame.bar3.setValue(v3);
		if (v3>70) 
			mainGame.bar3.setForeground(Color.GREEN);
		if (v3<70 && v3>30) 
			mainGame.bar3.setForeground(new Color(0xffcb00));
		if (v3<30) 
			mainGame.bar3.setForeground(new Color(0xf30000));
		
		mainGame.p2c1.setText(getAttributes2(p2.getTeam().get(0)));
		
		int v4 = p2.getTeam().get(0).getCurrentHP()*100/p2.getTeam().get(0).getMaxHP();
		mainGame.bar4.setValue(v4);
		if (v4>70)
			mainGame.bar4.setForeground(Color.GREEN);
		if (v4<70 && v4>30) 
			mainGame.bar4.setForeground(new Color(0xffcb00));
		if (v4<30) 
			mainGame.bar4.setForeground(new Color(0xf30000));
		
		mainGame.p2c2.setText(getAttributes2(p2.getTeam().get(1)));
		
		int v5 = p2.getTeam().get(1).getCurrentHP()*100/p2.getTeam().get(1).getMaxHP();
		mainGame.bar5.setValue(v5);
		if (v5>70) 
			mainGame.bar5.setForeground(Color.GREEN);
		if (v5<70 && v5>30) 
			mainGame.bar5.setForeground(new Color(0xffcb00));
		if (v5<30) 
			mainGame.bar5.setForeground(new Color(0xf30000));
		
		mainGame.p2c3.setText(getAttributes2(p2.getTeam().get(2)));
		
		int v6 = p2.getTeam().get(2).getCurrentHP()*100/p2.getTeam().get(2).getMaxHP();
		mainGame.bar6.setValue(v6);
		if (v6>70) 
			mainGame.bar6.setForeground(Color.GREEN);
		if (v6<70 && v6>30) 
			mainGame.bar6.setForeground(new Color(0xffcb00));
		if (v6<30) 
			mainGame.bar6.setForeground(new Color(0xf30000));
	}
	
	public void addImages() {
		CoverImg = new ImageIcon("Cover3.png");
		Image itmpC = CoverImg.getImage().getScaledInstance(130, 95, Image.SCALE_SMOOTH);
		CoverImg = new ImageIcon(itmpC);
		ImageIcon CA2 = new ImageIcon("Captain America 2.png");
		Image itmp1 = CA2.getImage().getScaledInstance(132, 80, Image.SCALE_SMOOTH);
		CA2 = new ImageIcon(itmp1);
		ImageIcon DP2 = new ImageIcon("Deadpool 2.png");
		Image itmp2 = DP2.getImage().getScaledInstance(132, 80, Image.SCALE_SMOOTH);
		DP2 = new ImageIcon(itmp2);
		ImageIcon DrS2 = new ImageIcon("Dr Strange 2.png");
		Image itmp3 = DrS2.getImage().getScaledInstance(132, 80, Image.SCALE_SMOOTH);
		DrS2 = new ImageIcon(itmp3);
		ImageIcon Elec2 = new ImageIcon("Electro 2.png");
		Image itmp4 = Elec2.getImage().getScaledInstance(132, 80, Image.SCALE_SMOOTH);
		Elec2 = new ImageIcon(itmp4);
		ImageIcon GR2 = new ImageIcon("Ghost Rider 2.png");
		Image itmp5 = GR2.getImage().getScaledInstance(132, 80, Image.SCALE_SMOOTH);
		GR2 = new ImageIcon(itmp5);
		ImageIcon Hela2 = new ImageIcon("Hela 2.png");
		Image itmp6 = Hela2.getImage().getScaledInstance(132, 80, Image.SCALE_SMOOTH);
		Hela2 = new ImageIcon(itmp6);
		ImageIcon Hulk2 = new ImageIcon("Hulk 2.png");
		Image itmp7 = Hulk2.getImage().getScaledInstance(132, 80, Image.SCALE_SMOOTH);
		Hulk2 = new ImageIcon(itmp7);
		ImageIcon IceM2 = new ImageIcon("Iceman 2.png");
		Image itmp8 = IceM2.getImage().getScaledInstance(132, 80, Image.SCALE_SMOOTH);
		IceM2 = new ImageIcon(itmp8);
		ImageIcon IronM2 = new ImageIcon("Iron Man 2.png");
		Image itmp9 = IronM2.getImage().getScaledInstance(132, 80, Image.SCALE_SMOOTH);
		IronM2 = new ImageIcon(itmp9);
		ImageIcon Loki2 = new ImageIcon("Loki 2.png");
		Image itmp10 = Loki2.getImage().getScaledInstance(132, 80, Image.SCALE_SMOOTH);
		Loki2 = new ImageIcon(itmp10);
		ImageIcon QS2 = new ImageIcon("Quicksilver 2.png");
		Image itmp11 = QS2.getImage().getScaledInstance(132, 80, Image.SCALE_SMOOTH);
		QS2 = new ImageIcon(itmp11);
		ImageIcon SM2 = new ImageIcon("Spider Man 2.png");
		Image itmp12 = SM2.getImage().getScaledInstance(132, 80, Image.SCALE_SMOOTH);
		SM2 = new ImageIcon(itmp12);
		ImageIcon Thor2 = new ImageIcon("Thor 2.png");
		Image itmp13 = Thor2.getImage().getScaledInstance(132, 80, Image.SCALE_SMOOTH);
		Thor2 = new ImageIcon(itmp13);
		ImageIcon Ven2 = new ImageIcon("Venom 2.png");
		Image itmp14 = Ven2.getImage().getScaledInstance(132, 80, Image.SCALE_SMOOTH);
		Ven2 = new ImageIcon(itmp14);
		ImageIcon YJ2 = new ImageIcon("Yellow Jacket 2.png");
		Image itmp15 = YJ2.getImage().getScaledInstance(132, 80, Image.SCALE_SMOOTH);
		YJ2 = new ImageIcon(itmp15);
		
		selectionImages.add(CA2);	
		selectionImages.add(DP2);	
		selectionImages.add(DrS2);	
		selectionImages.add(Elec2);	
		selectionImages.add(GR2);	
		selectionImages.add(Hela2);	
		selectionImages.add(Hulk2);	
		selectionImages.add(IceM2);	
		selectionImages.add(IronM2);	
		selectionImages.add(Loki2);	
		selectionImages.add(QS2);	
		selectionImages.add(SM2);	
		selectionImages.add(Thor2);	
		selectionImages.add(Ven2);	
		selectionImages.add(YJ2);	
		
		ImageIcon CA = new ImageIcon("Captain America.png"); 		//130x123
		Image itmp16 = CA.getImage().getScaledInstance(130, 123, Image.SCALE_SMOOTH);
		CA = new ImageIcon(itmp16);
		ImageIcon DP = new ImageIcon("Deadpool.png"); 		//130x123
		Image itmp17 = DP.getImage().getScaledInstance(130, 123, Image.SCALE_SMOOTH);
		DP = new ImageIcon(itmp17);
		ImageIcon DrS = new ImageIcon("Dr Strange.png"); 		//130x123
		Image itmp18 = DrS.getImage().getScaledInstance(130, 123, Image.SCALE_SMOOTH);
		DrS = new ImageIcon(itmp18);
		ImageIcon Elec = new ImageIcon("Electro.png"); 		//130x123
		Image itmp19 = Elec.getImage().getScaledInstance(130, 123, Image.SCALE_SMOOTH);
		Elec = new ImageIcon(itmp19);
		ImageIcon GR = new ImageIcon("Ghost Rider.png"); 		//130x123
		Image itmp20 = GR.getImage().getScaledInstance(130, 123, Image.SCALE_SMOOTH);
		GR = new ImageIcon(itmp20);
		ImageIcon Hela = new ImageIcon("Hela.png"); 		//130x123
		Image itmp21 = Hela.getImage().getScaledInstance(130, 123, Image.SCALE_SMOOTH);
		Hela = new ImageIcon(itmp21);
		ImageIcon Hulk = new ImageIcon("Hulk.png"); 		//130x123
		Image itmp22 = Hulk.getImage().getScaledInstance(130, 123, Image.SCALE_SMOOTH);
		Hulk = new ImageIcon(itmp22);
		ImageIcon IceM = new ImageIcon("Iceman.png"); 		//130x123
		Image itmp23 = IceM.getImage().getScaledInstance(130, 123, Image.SCALE_SMOOTH);
		IceM = new ImageIcon(itmp23);
		ImageIcon IronM = new ImageIcon("Ironman.png"); 		//130x123
		Image itmp24 = IronM.getImage().getScaledInstance(130, 123, Image.SCALE_SMOOTH);
		IronM = new ImageIcon(itmp24);
		ImageIcon Loki = new ImageIcon("Loki.png"); 		//130x123
		Image itmp25 = Loki.getImage().getScaledInstance(130, 123, Image.SCALE_SMOOTH);
		Loki = new ImageIcon(itmp25);
		ImageIcon QS = new ImageIcon("Quicksilver.png"); 		//130x123
		Image itmp26 = QS.getImage().getScaledInstance(130, 123, Image.SCALE_SMOOTH);
		QS = new ImageIcon(itmp26);
		ImageIcon SM = new ImageIcon("Spiderman.png"); 		//130x123
		Image itmp27 = SM.getImage().getScaledInstance(130, 123, Image.SCALE_SMOOTH);
		SM = new ImageIcon(itmp27);
		ImageIcon Thor = new ImageIcon("Thor.png"); 		//130x123
		Image itmp28 = Thor.getImage().getScaledInstance(130, 123, Image.SCALE_SMOOTH);
		Thor = new ImageIcon(itmp28);
		ImageIcon Ven = new ImageIcon("Venom.png"); 		//130x123
		Image itmp29 = Ven.getImage().getScaledInstance(130, 123, Image.SCALE_SMOOTH);
		Ven = new ImageIcon(itmp29);
		ImageIcon YJ = new ImageIcon("Yellow Jacket.png"); 		//130x123
		Image itmp30 = YJ.getImage().getScaledInstance(130, 123, Image.SCALE_SMOOTH);
		YJ = new ImageIcon(itmp30);
		
		boardImages.add(CA);	
		boardImages.add(DP);	
		boardImages.add(DrS);	
		boardImages.add(Elec);	
		boardImages.add(GR);	
		boardImages.add(Hela);	
		boardImages.add(Hulk);	
		boardImages.add(IceM);	
		boardImages.add(IronM);	
		boardImages.add(Loki);	
		boardImages.add(QS);	
		boardImages.add(SM);	
		boardImages.add(Thor);	
		boardImages.add(Ven);	
		boardImages.add(YJ);
		
		ImageIcon CAI = new ImageIcon("CAI.png");
		Image itmp31 = CAI.getImage().getScaledInstance(46, 30, Image.SCALE_SMOOTH);
		CAI = new ImageIcon(itmp31);
		ImageIcon DPI = new ImageIcon("DPI.png");
		Image itmp32 = DPI.getImage().getScaledInstance(46, 30, Image.SCALE_SMOOTH);
		DPI = new ImageIcon(itmp32);
		ImageIcon DrSI = new ImageIcon("DrsI.png");
		Image itmp33 = DrSI.getImage().getScaledInstance(46, 30, Image.SCALE_SMOOTH);
		DrSI = new ImageIcon(itmp33);
		ImageIcon ElecI = new ImageIcon("ElecI.png");
		Image itmp34 = ElecI.getImage().getScaledInstance(46, 30, Image.SCALE_SMOOTH);
		ElecI = new ImageIcon(itmp34);
		ImageIcon GRI = new ImageIcon("GRI.png");
		Image itmp35 = GRI.getImage().getScaledInstance(46, 30, Image.SCALE_SMOOTH);
		GRI = new ImageIcon(itmp35);
		ImageIcon HelaI = new ImageIcon("HelaI.png");
		Image itmp36 = HelaI.getImage().getScaledInstance(46, 30, Image.SCALE_SMOOTH);
		HelaI = new ImageIcon(itmp36);
		ImageIcon HulkI = new ImageIcon("HulkI.png");
		Image itmp37 = HulkI.getImage().getScaledInstance(46, 30, Image.SCALE_SMOOTH);
		HulkI = new ImageIcon(itmp37);
		ImageIcon IceMI = new ImageIcon("IceMI.png");
		Image itmp38 = IceMI.getImage().getScaledInstance(46, 30, Image.SCALE_SMOOTH);
		IceMI = new ImageIcon(itmp38);
		ImageIcon IMI = new ImageIcon("IMI.png");
		Image itmp39 = IMI.getImage().getScaledInstance(46, 30, Image.SCALE_SMOOTH);
		IMI = new ImageIcon(itmp39);
		ImageIcon LokiI = new ImageIcon("LokiI.png");
		Image itmp40 = LokiI.getImage().getScaledInstance(46, 30, Image.SCALE_SMOOTH);
		LokiI = new ImageIcon(itmp40);
		ImageIcon QSI = new ImageIcon("QSI.png");
		Image itmp41 = QSI.getImage().getScaledInstance(46, 30, Image.SCALE_SMOOTH);
		QSI = new ImageIcon(itmp41);
		ImageIcon SMI = new ImageIcon("SMI.png");
		Image itmp42 = SMI.getImage().getScaledInstance(46, 30, Image.SCALE_SMOOTH);
		SMI = new ImageIcon(itmp42);
		ImageIcon ThorI = new ImageIcon("ThorI.png");
		Image itmp43 = ThorI.getImage().getScaledInstance(46, 30, Image.SCALE_SMOOTH);
		ThorI = new ImageIcon(itmp43);
		ImageIcon VenI = new ImageIcon("VenI.png");
		Image itmp44 = VenI.getImage().getScaledInstance(46, 30, Image.SCALE_SMOOTH);
		VenI = new ImageIcon(itmp44);
		ImageIcon YJI = new ImageIcon("YJI.png");
		Image itmp45 = YJI.getImage().getScaledInstance(46, 30, Image.SCALE_SMOOTH);
		YJI = new ImageIcon(itmp45);
		
		iconImages.add(CAI);	
		iconImages.add(DPI);	
		iconImages.add(DrSI);	
		iconImages.add(ElecI);	
		iconImages.add(GRI);	
		iconImages.add(HelaI);	
		iconImages.add(HulkI);	
		iconImages.add(IceMI);	
		iconImages.add(IMI);	
		iconImages.add(LokiI);	
		iconImages.add(QSI);	
		iconImages.add(SMI);	
		iconImages.add(ThorI);	
		iconImages.add(VenI);	
		iconImages.add(YJI);
	}
	
	public ImageIcon getChampIcon(Champion c) {
		for(int i = 0; i < Game.getAvailableChampions().size(); i++) {			
			if(Game.getAvailableChampions().get(i).getName().equals(c.getName())) {			
				return iconImages.get(i);
			}
		}
		return null;
	}
	
	public ImageIcon getChampImg(Champion c) {
		for(int i = 0; i < Game.getAvailableChampions().size(); i++) {
			if(Game.getAvailableChampions().get(i).equals(c)) {
				return boardImages.get(i);
			}
		}
		return null;
	}
	
	public boolean checkStartingCondition() {
		return (p1.getLeader() != null) && (p2.getLeader() != null) && (p1.getTeam().size() == 3) && (p2.getTeam().size() == 3);
	}

	public String getAttributes(Champion c) {
		String type = "";
		if(c instanceof Hero)
			type = "Hero";
		if(c instanceof Villain)
			type = "Villain";
		if(c instanceof AntiHero)
			type = "Anti-Hero";
		String s = "\n"+ c.getName() + "\n";
		s += type + "\n" + "HP: " + c.getMaxHP() + "\n" + "Mana: " + c.getMana() + "\n" + "Action Points: " +
				c.getMaxActionPointsPerTurn() + "\n" + "Speed: " + c.getSpeed() + "\n" + "Range: " + c.getAttackRange() + "\n"
					+ "Damage: " + c.getAttackDamage() + "\n";	
		return s;
	}
	
	public String getAttributes2(Champion c) {
		String type = "";
		if(c instanceof Hero)
			type = "Hero";
		if(c instanceof Villain)
			type = "Villain";
		if(c instanceof AntiHero)
			type = "Anti-Hero";	
		String x = "";
		if(p1.getLeader().equals(c) || p2.getLeader().equals(c))
			x = "LEADER" + "\n";
		String s = c.getName() + "\n" + type + "\n" + x + "HP:    -------    " + "\n" + "Mana: " + 
				c.getMana() + "\n" + "Action Points: " + c.getCurrentActionPoints() + "/" + c.getMaxActionPointsPerTurn() + "\n" +
				"Speed: " + c.getSpeed() + "\n" + "Range: " + c.getAttackRange() + "\n"+ "Damage: " + c.getAttackDamage() + "\n";
		
		return s;
	}
	
	public String setAbilityDetails(Ability a) {
		String type = "";
		if(a instanceof DamagingAbility)
			type = "Damaging Ability" + "\n" + "Damage: " + ((DamagingAbility) a).getDamageAmount() + "\n";
		if(a instanceof HealingAbility)
			type = "Healing Ability" + "\n" + "Heal Amount: " + ((HealingAbility) a).getHealAmount() + "\n";
		else if(a instanceof CrowdControlAbility)
			type = "Crowd Control Ability" + "\n" + "Effect Applied: " + ((CrowdControlAbility) a).getEffect().getName() 
				+ " (" + ((CrowdControlAbility) a).getEffect().getDuration() + " turns)" + "\n";
		String s = a.getName() + " \n" + type + a.getCastArea() + "\n" + "Range: " + a.getCastRange() + "\n" + "Mana Cost: "
			+ a.getManaCost() + "\n" + "Action Points Needed: " + a.getRequiredActionPoints() + "\n" +
				"Cooldown: " + a.getCurrentCooldown() + "/" +a.getBaseCooldown();
		return s;
	}
	
	public String setAppliedEffects(Champion c) {
		String s = "Applied Effects: " + "\n";
		for(int i = 0; i < c.getAppliedEffects().size(); i++) {
			s += c.getAppliedEffects().get(i).getName() + " (" + c.getAppliedEffects().get(i).getDuration() + " turns)" + "\n";
		}
		return s;
	}
	
	public String getAbilities(Champion c) {
		String s = "\n" + "Abilities" + "\n" + "\n";
		for(int i = 0; i < c.getAbilities().size(); i++) {
			s += c.getAbilities().get(i).getName() + "\n" + "\n";
		}
		return s;
	}

	public int getButton(Champion c) {
		for (int i = 0 ; i<Game.getAvailableChampions().size();i++) {
			if (c.equals(Game.getAvailableChampions().get(i))) {
				return i;
			}
		}
		return -1;
	}
	
	public Champion getLeaderButton(String s) {
		for (int i = 0; i < Game.getAvailableChampions().size();i++) {
			if(s.equals(Game.getAvailableChampions().get(i).getName())) {
				return Game.getAvailableChampions().get(i);
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("unused")
		Controller c = new Controller();
	}
	
	
}
