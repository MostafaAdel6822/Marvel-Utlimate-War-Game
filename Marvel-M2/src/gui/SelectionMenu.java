package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.world.Champion;

public class SelectionMenu {
	
	JFrame frame;
	JPanel champions, description, firstPlayer, secondPlayer;
	JPanel allChampions;
	JButton firstTeam, secondTeam;
	JTextPane first, second;
	JTextPane firstLeader, secondLeader;
	JTextPane currentLeader1, currentLeader2;
	JButton p1c1, p1c2, p1c3, p2c1, p2c2, p2c3;
	JButton clearTeam1, clearTeam2;
	JButton startGame;
	JTextPane attributes, abilities;
	JLabel background;
	ImageIcon BImg,BImg2,BImg3,BImg4,BImg5,BImg6;
	
	public SelectionMenu() {
		
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
		
		
		//champions
		champions = new JPanel();
		champions.setBounds(0, 0, 1000, 300);
		champions.setBackground(new Color(0x660000));
		champions.setLayout(null);
		allChampions = new JPanel();
		allChampions.setBounds(20, 20, 700, 260);
		allChampions.setLayout(new GridLayout(3, 5, 10, 10));
		allChampions.setBackground(new Color(0x660000));
		for(int i = 0; i < 15; i++) {
			JButton b = new JButton();
			b.setFocusable(false);
			b.setOpaque(false);
			b.setBackground(new Color(0,0,0,0));
			b.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, false));
			allChampions.add(b);
		}
		
		firstTeam = new JButton();
		secondTeam = new JButton();
		firstTeam.setBounds(720, 85, 300, 50);
		firstTeam.setText("add Champion to first team");
		firstTeam.setFocusable(false);
		secondTeam.setBounds(720, 170, 300, 50);
		
		secondTeam.setFocusable(false);
		firstTeam.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		firstTeam.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, false));
		firstTeam.setBackground(Color.WHITE);
		firstTeam.setForeground(Color.WHITE);
		secondTeam.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		//secondTeam.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, false));
		secondTeam.setBorder(BorderFactory.createEmptyBorder());
		secondTeam.setForeground(Color.WHITE);
		secondTeam.setBackground(Color.BLACK);
		secondTeam.setBackground(new Color(0,0,0,0));
		secondTeam.setOpaque(false);
		firstTeam.setBorder(BorderFactory.createEmptyBorder());
		firstTeam.setBackground(new Color(0,0,0,0));
		firstTeam.setOpaque(false);
		
		
		BImg = new ImageIcon("BBB.png");
		Image Btmp = BImg.getImage().getScaledInstance(350, 50, Image.SCALE_SMOOTH);
		BImg = new ImageIcon(Btmp);
		BImg2 = new ImageIcon("BBB.png");
		Image Btmp2 = BImg2.getImage().getScaledInstance(350, 50, Image.SCALE_SMOOTH);
		BImg2 = new ImageIcon(Btmp2);
		
		secondTeam.setText("add Champion to second team");
		secondTeam.setHorizontalTextPosition(SwingConstants.CENTER);
		firstTeam.setHorizontalTextPosition(SwingConstants.CENTER);
		
		
		secondTeam.setIcon(BImg);
		
		firstTeam.setIcon(BImg2);
		
		champions.add(allChampions);
		champions.add(firstTeam);
		champions.add(secondTeam);
		
		
		//description
		description = new JPanel();
		description.setBounds(1000, 0, 500, 300);
		description.setLayout(null);
		attributes = new JTextPane();
		attributes.setBounds(0, 0, 250, 300);
		attributes.setEditable(false);
		abilities = new JTextPane();
		abilities.setBounds(250, 0, 250, 300);
		abilities.setEditable(false);
		attributes.setLayout(null);
		abilities.setLayout(null);
		abilities.setBackground(new Color(0x660000));
		abilities.setForeground(Color.WHITE);
		attributes.setOpaque(true);
		abilities.setOpaque(true);
		attributes.setBackground(new Color(0x660000));
		attributes.setForeground(Color.WHITE);
		abilities.setFont(new Font("Berlin Sans FB", Font.PLAIN, 26));
		attributes.setFont(new Font("Berlin Sans FB", Font.PLAIN, 26));
		centerTextPane(abilities);
		centerTextPane(attributes);

		description.add(attributes);
		description.add(abilities);
		
		//background
		background = new JLabel();
		background.setBounds(0, 0, 1515, 830);
		background.setIcon(new ImageIcon("bg6.jpg"));
		background.setVisible(true);
		background.setFocusable(false);
		
		//firstPlayer
		firstPlayer = new JPanel();
		firstPlayer.setBounds(0, 300, 1500, 250);
		firstPlayer.setBackground(new Color(0x990000));
		firstPlayer.setLayout(null);
		
		first = new JTextPane();
		first.setText("tmp name 1");
		first.setBounds(520, 20, 450, 50);
		first.setFont(new Font("Berlin Sans FB", Font.PLAIN, 35));
		first.setForeground(Color.WHITE);
		first.setOpaque(false);
		first.setEditable(false);
		centerTextPane(first);
			
		firstLeader = new JTextPane();
		firstLeader.setBounds(240, 110, 200, 40);
		firstLeader.setText("Select Your Leader:");
		firstLeader.setFont(new Font("Berlin Sans FB", Font.PLAIN, 24));
		firstLeader.setForeground(Color.WHITE);
		firstLeader.setOpaque(false);
		firstLeader.setVisible(false);
		firstLeader.setEditable(false);
		
		
		currentLeader1 = new JTextPane();
		currentLeader1.setBounds(570, 200, 350, 40);
		currentLeader1.setText("Current Leader: ");
		currentLeader1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		currentLeader1.setForeground(Color.WHITE);
		centerTextPane(currentLeader1);
		currentLeader1.setOpaque(false);
		currentLeader1.setVisible(false);
		
		
		p1c1 = new JButton();
		p1c2 = new JButton();
		p1c3 = new JButton();
		p1c1.setBounds(475, 80, 150, 100);
		p1c2.setBounds(675, 80, 150, 100);
		p1c3.setBounds(875, 80, 150, 100);
		p1c1.setVisible(false);
		p1c2.setVisible(false);
		p1c3.setVisible(false);
		p1c1.setFocusable(false);
		p1c2.setFocusable(false);
		p1c3.setFocusable(false);
		p1c1.setBackground(new Color(0,0,0,0));
		p1c2.setBackground(new Color(0,0,0,0));
		p1c3.setBackground(new Color(0,0,0,0));
		p1c1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, false));
		p1c2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, false));
		p1c3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, false));
		p1c1.setOpaque(false);
		p1c2.setOpaque(false);
		p1c3.setOpaque(false);
		
		clearTeam1 = new JButton();
		clearTeam1.setBounds(1100, 115, 105, 27);
		clearTeam1.setText("Clear Team");
		clearTeam1.setVisible(false);
		clearTeam1.setFocusable(false);
		clearTeam1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		clearTeam1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		clearTeam1.setBackground(Color.WHITE);
		
		clearTeam1.setBackground(new Color(0,0,0,0));
		clearTeam1.setBorder(BorderFactory.createEmptyBorder());
		clearTeam1.setForeground(Color.WHITE);
		clearTeam1.setOpaque(false);
		clearTeam1.setHorizontalTextPosition(SwingConstants.CENTER);
		
		BImg5 = new ImageIcon("BBB.png");
		Image Btmp5 = BImg5.getImage().getScaledInstance(115, 40, Image.SCALE_SMOOTH);
		BImg5 = new ImageIcon(Btmp5);
		
		clearTeam1.setIcon(BImg5);
		
		
		firstPlayer.add(first);
		firstPlayer.add(firstLeader);
		firstPlayer.add(currentLeader1);
		firstPlayer.add(p1c1);
		firstPlayer.add(p1c2);
		firstPlayer.add(p1c3);
		firstPlayer.add(clearTeam1);
			
		
		//secondPLayer
		secondPlayer = new JPanel();
		secondPlayer.setBounds(0, 550, 1500, 250);
		secondPlayer.setBackground(new Color(0x990000));
		secondPlayer.setLayout(null);
		
		second = new JTextPane();
		second.setText("tmp name 2");
		second.setBounds(520, 20, 450, 50);
		second.setFont(new Font("Berlin Sans FB", Font.PLAIN, 35));
		second.setForeground(Color.WHITE);
		second.setOpaque(false);
		second.setEditable(false);
		centerTextPane(second);
			
		secondLeader = new JTextPane();
		secondLeader.setBounds(240, 110, 200, 40);
		secondLeader.setText("Select Your Leader:");
		secondLeader.setFont(new Font("Berlin Sans FB", Font.PLAIN, 24));
		secondLeader.setForeground(Color.WHITE);
		secondLeader.setOpaque(false);
		secondLeader.setVisible(false);
		secondLeader.setEditable(false);
		
		currentLeader2 = new JTextPane();
		currentLeader2.setBounds(570, 200, 350, 40);
		currentLeader2.setText("Current Leader: ");
		currentLeader2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		currentLeader2.setForeground(Color.WHITE);
		centerTextPane(currentLeader2);
		currentLeader2.setOpaque(false);
		currentLeader2.setVisible(false);
		currentLeader2.setEditable(false);
		
		
		p2c1 = new JButton();
		p2c2 = new JButton();
		p2c3 = new JButton();
		p2c1.setBounds(475, 80, 150, 100);
		p2c2.setBounds(675, 80, 150, 100);
		p2c3.setBounds(875, 80, 150, 100);
		p2c1.setVisible(false);
		p2c2.setVisible(false);
		p2c3.setVisible(false);
		p2c1.setFocusable(false);
		p2c2.setFocusable(false);
		p2c3.setFocusable(false);
		p2c1.setBackground(new Color(0,0,0,0));
		p2c2.setBackground(new Color(0,0,0,0));
		p2c3.setBackground(new Color(0,0,0,0));
		p2c1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, false));
		p2c2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, false));
		p2c3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, false));
		p2c1.setOpaque(false);
		p2c2.setOpaque(false);
		p2c3.setOpaque(false);
		
		clearTeam2 = new JButton();
		clearTeam2.setBounds(1100, 118, 105, 27);
		clearTeam2.setText("Clear Team");
		clearTeam2.setVisible(false);
		clearTeam2.setFocusable(false);
		clearTeam2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		clearTeam2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		clearTeam2.setBackground(Color.WHITE);
		
		clearTeam2.setBackground(new Color(0,0,0,0));
		clearTeam2.setBorder(BorderFactory.createEmptyBorder());
		clearTeam2.setForeground(Color.WHITE);
		clearTeam2.setOpaque(false);
		clearTeam2.setHorizontalTextPosition(SwingConstants.CENTER);
		
		BImg4 = new ImageIcon("BBB.png");
		Image Btmp4 = BImg4.getImage().getScaledInstance(115, 40, Image.SCALE_SMOOTH);
		BImg4 = new ImageIcon(Btmp4);
		
		clearTeam2.setIcon(BImg4);
		
		startGame = new JButton();
		startGame.setBounds(1370, 185, 120, 50);
		startGame.setText("Start Game");
		startGame.setEnabled(false);
		startGame.setFocusable(false);
		startGame.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		startGame.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, false));
		startGame.setBackground(Color.WHITE);
		startGame.setBackground(new Color(0,0,0,0));
		startGame.setBorder(BorderFactory.createEmptyBorder());
		startGame.setForeground(Color.WHITE);
		startGame.setOpaque(false);
		startGame.setHorizontalTextPosition(SwingConstants.CENTER);
		
		BImg3 = new ImageIcon("BBB.png");
		Image Btmp3 = BImg3.getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH);
		BImg3 = new ImageIcon(Btmp3);
		
		startGame.setIcon(BImg3);
		
		//startGame.setForeground(new Color(0x660000));
		
		secondPlayer.add(second);
		secondPlayer.add(secondLeader);
		secondPlayer.add(currentLeader2);
		secondPlayer.add(p2c1);
		secondPlayer.add(p2c2);
		secondPlayer.add(p2c3);
		secondPlayer.add(clearTeam2);
		secondPlayer.add(startGame);
		
		
		//frame add
		frame.add(champions);
		frame.add(description);
		frame.add(firstPlayer);
		frame.add(secondPlayer);
		frame.add(background);
		
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
		new SelectionMenu();
	}

}
