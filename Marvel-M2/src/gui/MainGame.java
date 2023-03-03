package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class MainGame {
	
	JFrame frame;
	JPanel topPanel, rightPanel, leftPanel, grid, controls, abilities;
	JPanel turnOrderP;
	ArrayList<JLabel> turnOrderL; 
	Cell[][] board;
	JLabel saved_direction;
	JButton up, down, right, left;
	JButton move, attack;
	JButton ability1B, ability2B, ability3B;
	JButton leaderAbilityB, leaderAbilityB2;
	JButton endTurnB;
	JTextPane p1c1, p1c2, p1c3, p2c1, p2c2, p2c3;
	JTextPane p1c1ae, p1c2ae, p1c3ae, p2c1ae, p2c2ae, p2c3ae;
	JTextPane ability1, ability2, ability3;
	JTextPane name1, name2;
	JTextPane leaderAbilityText, leaderAbility1Used, leaderAbility2Used;	
	JTextPane selectedTarget;
	ImageIcon boardImg, TOarrow, upImg, downImg, rightImg, leftImg, BGImg;
	JLabel boardL;
	JLabel TOLabel, BGL;
	JProgressBar bar1,bar2,bar3,bar4,bar5,bar6;

	
	public MainGame() {
		
		//frame
		frame = new JFrame();
		frame.setTitle("Marvel Universe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1515,830);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(false);	
		//frame.setVisible(true);	
		frame.setLayout(null);

		bar1 = new JProgressBar();
		bar2 = new JProgressBar();
		bar3 = new JProgressBar();
		bar4 = new JProgressBar();
		bar5 = new JProgressBar();
		bar6 = new JProgressBar();

		bar1.setBounds(78,43,67,14);
		bar1.setBackground(Color.WHITE);
		bar1.setForeground(Color.GREEN);
		
		bar2.setBounds(78,62,67,14);
		bar2.setBackground(Color.WHITE);
		bar2.setForeground(Color.GREEN);
		
		bar3.setBounds(78,43,67,14);
		bar3.setBackground(Color.WHITE);
		bar3.setForeground(Color.GREEN);
		
		bar4.setValue(0);
		bar4.setBounds(78,43,67,14);
		bar4.setBackground(Color.WHITE);
		bar4.setForeground(Color.GREEN);
		
		bar5.setBounds(78,62,67,14);
		bar5.setBackground(Color.WHITE);
		bar5.setForeground(Color.GREEN);
		
		bar6.setBounds(78,43,67,14);
		bar6.setBackground(Color.WHITE);
		bar6.setForeground(Color.GREEN);
		
		
		//topPanel
		topPanel = new JPanel();
		topPanel.setBounds(425, 0, 650, 40);
		topPanel.setBackground(new Color(0x9e0129));
		topPanel.setLayout(null);
		
		turnOrderP = new JPanel();
		turnOrderP.setBounds(50, 5, 301, 30);
		turnOrderP.setBackground(new Color(0x9e0129));
		turnOrderP.setLayout(new GridLayout(0,6,5,0));
		turnOrderP.setOpaque(false);
		
		turnOrderL = new ArrayList<JLabel>(6);

		selectedTarget = new JTextPane();
		selectedTarget.setBounds(788, 10, 365, 30);
		selectedTarget.setOpaque(false);
		selectedTarget.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		selectedTarget.setForeground(Color.WHITE);
		//selectedTarget.setBackground(Color.WHITE);
		selectedTarget.setText("Selected Target: NULL");
		selectedTarget.setEditable(false);
		
		TOLabel = new JLabel();
		TOLabel.setBounds(2, 6, 35, 30);
		TOLabel.setOpaque(false);
		TOLabel.setBackground(new Color(0,0,0,0));	
		
		TOarrow = new ImageIcon("TOarrow.png");
		Image tmp5 = TOarrow.getImage().getScaledInstance(35, 30, Image.SCALE_SMOOTH);
		TOarrow = new ImageIcon(tmp5);
		
		TOLabel.setIcon(TOarrow);
		
		topPanel.add(turnOrderP);
		topPanel.add(TOLabel);


		//grid
		grid = new JPanel();
		grid.setBounds(425, 40, 650, 615);
		grid.setLayout(new GridLayout(5, 5));
		grid.setOpaque(false);
			
		board = new Cell[5][5];
		for (int i =4; i > -1; i--) {
			for(int j = 0; j < 5; j++) {
				board[i][j] = new Cell();
				grid.add(board[i][j]);
			}
		}
		

		boardL = new JLabel();
		boardL.setBounds(425, 40, 650, 615);
		boardL.setBackground(Color.red);
		boardL.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		boardL.setVisible(true);
		
		boardImg = new ImageIcon("board.jpg");
		Image boardtmp = boardImg.getImage().getScaledInstance(650, 615, Image.SCALE_SMOOTH);
		boardImg = new ImageIcon(boardtmp);		
		boardL.setIcon(boardImg);
		

	
		
		//rightPanel
		rightPanel = new JPanel();
		rightPanel.setBounds(1075, 0, 425, 655);
		rightPanel.setBackground(new Color(0x9e0129));
		rightPanel.setLayout(null);
		
		p2c1 = new JTextPane();
		p2c2 = new JTextPane();
		p2c3 = new JTextPane();
		p2c1.setLayout(null);
		p2c2.setLayout(null);
		p2c3.setLayout(null);
		p2c1.setEditable(false);
		p2c2.setEditable(false);
		p2c3.setEditable(false);		
		p2c1.setBounds(10, 75, 205, 175);
		p2c1.setOpaque(true);
		p2c1.setBackground(new Color(0xca0000));
		p2c2.setBounds(10, 255, 205, 175);
		p2c2.setOpaque(true);
		p2c2.setBackground(new Color(0xca0000));
		p2c3.setBounds(10, 435, 205, 175);
		p2c3.setOpaque(true);
		p2c3.setBackground(new Color(0xca0000));
		p2c1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		p2c2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		p2c3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		p2c1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		p2c2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		p2c3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		centerTextPane(p2c1);
		centerTextPane(p2c2);
		centerTextPane(p2c3);
		
		p2c1ae = new JTextPane();
		p2c2ae = new JTextPane();
		p2c3ae = new JTextPane();
		p2c1ae.setLayout(null);
		p2c2ae.setLayout(null);
		p2c3ae.setLayout(null);
		p2c1ae.setEditable(false);
		p2c2ae.setEditable(false);
		p2c3ae.setEditable(false);
		p2c1ae.setBounds(215, 75, 200, 175);
		p2c1ae.setOpaque(true);
		p2c1ae.setText("Applied Effects: ");
		p2c1ae.setBackground(new Color(0xca0000));
		p2c2ae.setBounds(215, 255, 200, 175);
		p2c2ae.setOpaque(true);
		p2c2ae.setText("Applied Effects: ");
		p2c2ae.setBackground(new Color(0xca0000));
		p2c3ae.setBounds(215, 435, 200, 175);
		p2c3ae.setOpaque(true);
		p2c3ae.setText("Applied Effects: ");
		p2c3ae.setBackground(new Color(0xca0000));
		p2c1ae.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		p2c2ae.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		p2c3ae.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		p2c1ae.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		p2c2ae.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		p2c3ae.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		centerTextPane(p2c1ae);
		centerTextPane(p2c2ae);
		centerTextPane(p2c3ae);
		    
		name2 = new JTextPane();
		centerTextPane(name2);
		name2.setOpaque(false);
		name2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 40));
		name2.setBounds(5, 10, 415, 65);
		name2.setText("tmp name2");
		name2.setForeground(Color.WHITE);
		name2.setEditable(false);
		
		leaderAbility2Used = new JTextPane();
		centerTextPane(leaderAbility2Used);
		leaderAbility2Used.setOpaque(false);
		leaderAbility2Used.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		leaderAbility2Used.setBounds(5, 615, 415, 35);
		leaderAbility2Used.setText("Leader Ability: Not Used");
		leaderAbility2Used.setForeground(Color.WHITE);
		leaderAbility2Used.setEditable(false);
	
		rightPanel.add(p2c1);
		rightPanel.add(p2c2);
		rightPanel.add(p2c3);
		rightPanel.add(p2c1ae);
		rightPanel.add(p2c2ae);
		rightPanel.add(p2c3ae);
		rightPanel.add(name2);
		rightPanel.add(leaderAbility2Used);

		
		
		//leftPanel
		leftPanel = new JPanel();
		leftPanel.setBounds(0, 0, 425, 655);
		leftPanel.setBackground(new Color(0x9e0129));
		leftPanel.setLayout(null);
		
		p1c1 = new JTextPane();
		p1c2 = new JTextPane();
		p1c3 = new JTextPane();
		p1c1.setLayout(null);
		p1c2.setLayout(null);
		p1c3.setLayout(null);
		p1c1.setEditable(false);
		p1c2.setEditable(false);
		p1c3.setEditable(false);
		p1c1.setBounds(10, 75, 205, 175);
		p1c1.setOpaque(true);
		p1c1.setBackground(new Color(0xca0000));
		p1c2.setBounds(10, 255, 205, 175);
		p1c2.setOpaque(true);
		p1c2.setBackground(new Color(0xca0000));
		p1c3.setBounds(10, 435, 205, 175);
		p1c3.setOpaque(true);
		p1c3.setBackground(new Color(0xca0000));
		p1c1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		p1c2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		p1c3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		p1c1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		p1c2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		p1c3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		centerTextPane(p1c1);
		centerTextPane(p1c2);
		centerTextPane(p1c3);
		
		p1c1ae = new JTextPane();
		p1c2ae = new JTextPane();
		p1c3ae = new JTextPane();
		p1c1ae.setLayout(null);
		p1c2ae.setLayout(null);
		p1c3ae.setLayout(null);
		p1c1ae.setEditable(false);
		p1c2ae.setEditable(false);
		p1c3ae.setEditable(false);
		p1c1ae.setBounds(215, 75, 200, 175);
		p1c1ae.setOpaque(true);
		p1c1ae.setText("Applied Effects: ");
		p1c1ae.setBackground(new Color(0xca0000));
		p1c2ae.setBounds(215, 255, 200, 175);
		p1c2ae.setOpaque(true);
		p1c2ae.setText("Applied Effects: ");
		p1c2ae.setBackground(new Color(0xca0000));
		p1c3ae.setBounds(215, 435, 200, 175);
		p1c3ae.setOpaque(true);
		p1c3ae.setText("Applied Effects: ");
		p1c3ae.setBackground(new Color(0xca0000));
		p1c1ae.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		p1c2ae.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		p1c3ae.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		p1c1ae.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		p1c2ae.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		p1c3ae.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		centerTextPane(p1c1ae);
		centerTextPane(p1c2ae);
		centerTextPane(p1c3ae);
		
		name1 = new JTextPane();
		centerTextPane(name1);
		name1.setOpaque(false);
		name1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 40));
		name1.setBounds(5, 10, 415, 65);
		name1.setText("tmp name1");
		name1.setForeground(Color.WHITE);
		name1.setEditable(false);
		
		leaderAbility1Used = new JTextPane();
		centerTextPane(leaderAbility1Used);
		leaderAbility1Used.setOpaque(false);
		leaderAbility1Used.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		leaderAbility1Used.setBounds(5, 615, 415, 35);
		leaderAbility1Used.setText("Leader Ability: Not Used");
		leaderAbility1Used.setForeground(Color.WHITE);
		leaderAbility1Used.setEditable(false);
		
		leftPanel.add(p1c1);
		leftPanel.add(p1c2);
		leftPanel.add(p1c3);
		leftPanel.add(p1c1ae);
		leftPanel.add(p1c2ae);
		leftPanel.add(p1c3ae);
		leftPanel.add(name1);
		leftPanel.add(leaderAbility1Used);
	
		
		//controls
		controls = new JPanel();
		controls.setBounds(0, 600, 650, 250);
		controls.setBackground(new Color(0x9e0129));
		controls.setLayout(null);
		
		JLabel direction = new JLabel();
		direction.setText("Direction");
		direction.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		direction.setBounds(80, 105, 70, 25);
		direction.setForeground(Color.WHITE);
		saved_direction = new JLabel();
		saved_direction.setText("null");
		saved_direction.setHorizontalAlignment(JLabel.CENTER);
		saved_direction.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		saved_direction.setForeground(Color.WHITE);
		saved_direction.setBounds(90, 120, 50, 25);
	
		up = new JButton();
		down = new JButton();
		right = new JButton();
		left = new JButton();
		up.setBounds(100, 70, 30, 30);
		down.setBounds(100, 150, 30, 30);
		right.setBounds(156, 110, 30, 30);
		left.setBounds(43, 110, 30, 30);
		up.setFocusable(false);
		down.setFocusable(false);
		right.setFocusable(false);
		left.setFocusable(false);
		up.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		down.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		right.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		left.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		up.setOpaque(false);
		up.setBackground(new Color(0,0,0,0));
		up.setBorder(BorderFactory.createEmptyBorder());
		down.setOpaque(false);
		down.setBackground(new Color(0,0,0,0));
		down.setBorder(BorderFactory.createEmptyBorder());
		right.setOpaque(false);
		right.setBackground(new Color(0,0,0,0));
		right.setBorder(BorderFactory.createEmptyBorder());
		left.setOpaque(false);
		left.setBackground(new Color(0,0,0,0));
		left.setBorder(BorderFactory.createEmptyBorder());
		
		move = new JButton();
		move.setBounds(230, 85, 80, 25);
		move.setText("Move");	
		move.setFocusable(false);
		move.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		move.setBackground(new Color(0xcf0000));
		move.setForeground(Color.WHITE);
		move.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		attack = new JButton();
		attack.setBounds(230, 135, 80, 25);
		attack.setText("Attack");
		attack.setFocusable(false);
		attack.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		attack.setBackground(new Color(0xcf0000));
		attack.setForeground(Color.WHITE);
		attack.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		ability1B = new JButton();
		ability2B = new JButton();
		ability3B = new JButton();
		ability1B.setBounds(365, 70, 130, 25);
		ability2B.setBounds(365, 110, 130, 25);
		ability3B.setBounds(365, 150, 130, 25);
		ability1B.setText("Cast Ability 1");
		ability2B.setText("Cast Ability 2");
		ability3B.setText("Cast Ability 3");
		ability1B.setFocusable(false);
		ability2B.setFocusable(false);
		ability3B.setFocusable(false);
		ability1B.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		ability2B.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		ability3B.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		ability1B.setBackground(new Color(0xcf0000));
		ability1B.setForeground(Color.WHITE);
		ability1B.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ability2B.setBackground(new Color(0xcf0000));
		ability2B.setForeground(Color.WHITE);
		ability2B.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ability3B.setBackground(new Color(0xcf0000));
		ability3B.setForeground(Color.WHITE);
		ability3B.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		leaderAbilityText = new JTextPane();
		leaderAbilityText.setOpaque(false);
		leaderAbilityText.setBounds(527, 58, 100, 45);
		centerTextPane(leaderAbilityText);
		leaderAbilityText.setText("Leader Ability");
		leaderAbilityText.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		leaderAbilityText.setForeground(Color.WHITE);
		leaderAbilityText.setEditable(false);
		leaderAbilityB = new JButton();
		leaderAbilityB.setBounds(537, 101, 80, 22);
		leaderAbilityB.setText("Cast");
		leaderAbilityB.setBackground(new Color(0xcf0000));
		leaderAbilityB.setForeground(Color.WHITE);
		leaderAbilityB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		leaderAbilityB.setFocusable(false);
		leaderAbilityB2 = new JButton();
		leaderAbilityB2.setBounds(537, 129, 80, 22);
		leaderAbilityB2.setText("Details");
		leaderAbilityB2.setBackground(new Color(0xcf0000));
		leaderAbilityB2.setForeground(Color.WHITE);
		leaderAbilityB2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		leaderAbilityB2.setFocusable(false);
		
		endTurnB = new JButton();
		endTurnB.setBounds(528, 162, 98, 25);
		endTurnB.setText("End Turn");
		endTurnB.setFocusable(false);
		endTurnB.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		endTurnB.setBackground(new Color(0xcf0000));
		endTurnB.setForeground(Color.WHITE);
		endTurnB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		upImg = new ImageIcon("up.png");
		downImg = new ImageIcon("down.png");
		rightImg = new ImageIcon("right.png");
		leftImg = new ImageIcon("left.png");
		
		Image uptmp = upImg.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		upImg = new ImageIcon(uptmp);
		up.setIcon(upImg);
		Image downtmp = downImg.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		downImg = new ImageIcon(downtmp);
		down.setIcon(downImg);
		Image righttmp = rightImg.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		rightImg = new ImageIcon(righttmp);
		right.setIcon(rightImg);
		Image lefttmp = leftImg.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		leftImg = new ImageIcon(lefttmp);
		left.setIcon(leftImg);
				
		controls.add(direction);
		controls.add(saved_direction);
		controls.add(up);
		controls.add(down);
		controls.add(right);
		controls.add(left);
		controls.add(move);
		controls.add(attack);
		controls.add(ability1B);
		controls.add(ability2B);
		controls.add(ability3B);	
		controls.add(leaderAbilityB);
		controls.add(leaderAbilityB2);
		controls.add(leaderAbilityText);
		controls.add(endTurnB);
		
		
		
		//abilities
		abilities = new JPanel();
		abilities.setBounds(650, 600, 850, 250);
		abilities.setBackground(new Color(0x9e0129));
		abilities.setLayout(null);
	
		ability1 = new JTextPane();
		ability2 = new JTextPane();
		ability3 = new JTextPane();
		ability1.setLayout(null);
		ability2.setLayout(null);
		ability3.setLayout(null);
		ability1.setEditable(false);
		ability2.setEditable(false);
		ability3.setEditable(false);
		ability1.setBounds(10, 60, 265, 130);
		ability1.setOpaque(true);
		ability1.setBackground(new Color(0xca0000));
		ability2.setBounds(290, 60, 265, 130);
		ability2.setOpaque(true);
		ability2.setBackground(new Color(0xca0000));
		ability3.setBounds(570, 60, 265, 130);
		ability3.setOpaque(true);
		ability3.setBackground(new Color(0xca0000));
		ability1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		ability2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		ability3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		ability1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		ability2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		ability3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		centerTextPane(ability1);
		centerTextPane(ability2);
		centerTextPane(ability3);
		
		abilities.add(ability1);
		abilities.add(ability2);
		abilities.add(ability3);
	
		

		//add bars
		p1c1.add(bar1);
		p1c2.add(bar2);
		p1c3.add(bar3);
		p2c1.add(bar4);
		p2c2.add(bar5);
		p2c3.add(bar6);
		
		//frame add
		
		frame.add(selectedTarget);
		frame.add(grid);
		frame.add(boardL);
		frame.add(topPanel);
		frame.add(rightPanel);
		frame.add(leftPanel);
		frame.add(controls);
		frame.add(abilities);
		

		
		frame.revalidate();
		frame.repaint();
			
	}
	
	public void centerTextPane(JTextPane pane) {
		StyledDocument doc1 = pane.getStyledDocument();
		SimpleAttributeSet center1 = new SimpleAttributeSet();
		StyleConstants.setAlignment(center1, StyleConstants.ALIGN_CENTER);
		doc1.setParagraphAttributes(0, doc1.getLength(), center1, false);
	}
	
	
	public static void main(String[] args) {
		new MainGame();
	}

	
	
}
