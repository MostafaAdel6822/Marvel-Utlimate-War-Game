package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class StartScreen {

	JFrame frame;
	JButton start;
	JLabel background;
	JButton firstNameButton, secondNameButton;
	JTextField firstName, secondName;
	JLabel first, second;
	ImageIcon bgImg;
	ImageIcon BImg,BImg2,BImg3;
	
	JButton tryImage;
	
	public StartScreen() {
		
		//frame
		frame = new JFrame();
		frame.setTitle("Marvel Universe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1515,830);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setLayout(null);
		
		//play button
		start = new JButton();
		start.setBounds(660, 670, 155, 70);
		start.setText("Play Game");
		start.setFont(new Font("Berlin Sans FB", Font.PLAIN, 22));
		start.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		start.setBackground(new Color(0xcf0031));
		start.setForeground(Color.WHITE);
		start.setFocusable(false);
		
		start.setBackground(new Color(0,0,0,0));
		start.setBorder(BorderFactory.createEmptyBorder());
		start.setForeground(Color.WHITE);
		start.setOpaque(false);
		start.setHorizontalTextPosition(SwingConstants.CENTER);
		
		BImg3 = new ImageIcon("BBB.png");
		Image Btmp3 = BImg3.getImage().getScaledInstance(175, 65, Image.SCALE_SMOOTH);
		BImg3 = new ImageIcon(Btmp3);
		
		start.setIcon(BImg3);
		
		//background
		background = new JLabel();
		background.setBounds(0, 0, 1515, 830);
		bgImg = new ImageIcon("bgggg.png");
		Image itmp = bgImg.getImage().getScaledInstance(1515, 830, Image.SCALE_SMOOTH);
		bgImg = new ImageIcon(itmp);
		background.setIcon(bgImg);
		background.setVisible(true);
		background.setFocusable(false);
		
		//firstPLayer
		first = new JLabel();
		first.setText("First Player");
		first.setBounds(313, 600, 300, 150);
		first.setVerticalAlignment(JLabel.TOP);
		first.setFont(new Font("Berlin Sans FB", Font.PLAIN, 35));
		first.setForeground(Color.RED);
		first.setFocusable(false);
		firstName = new JTextField();
		firstName.setBounds(305, 650, 180, 30);
		//firstName.setEditable(false);
		firstNameButton = new JButton();
		firstNameButton.setBounds(360, 690, 70, 30);
		firstNameButton.setText("Submit");
		firstNameButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		firstNameButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		firstNameButton.setBackground(new Color(0xcf0031));
		firstNameButton.setForeground(Color.WHITE);
		firstNameButton.setFocusable(false);
		
		firstNameButton.setBackground(new Color(0,0,0,0));
		firstNameButton.setBorder(BorderFactory.createEmptyBorder());
		firstNameButton.setForeground(Color.WHITE);
		firstNameButton.setOpaque(false);
		firstNameButton.setHorizontalTextPosition(SwingConstants.CENTER);
		
		BImg = new ImageIcon("BBB.png");
		Image Btmp = BImg.getImage().getScaledInstance(95, 40, Image.SCALE_SMOOTH);
		BImg = new ImageIcon(Btmp);
		
		firstNameButton.setIcon(BImg);
		
		//secondPlayer
		second = new JLabel();
		second.setText("Second Player");
		second.setBounds(950, 600, 300, 150);
		second.setVerticalAlignment(JLabel.TOP);
		second.setFont(new Font("Berlin Sans FB", Font.PLAIN, 35));
		second.setForeground(Color.RED);
		second.setFocusable(false);
		secondName = new JTextField();
		secondName.setBounds(965, 650, 180, 30);	
		//secondName.setEditable(false);
		secondNameButton = new JButton();
		secondNameButton.setBounds(1025, 690, 70, 30);
		secondNameButton.setText("Submit");
		secondNameButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		secondNameButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		secondNameButton.setBackground(new Color(0xcf0031));
		secondNameButton.setForeground(Color.WHITE);
		secondNameButton.setFocusable(false);
		
		secondNameButton.setBackground(new Color(0,0,0,0));
		secondNameButton.setBorder(BorderFactory.createEmptyBorder());
		secondNameButton.setForeground(Color.WHITE);
		secondNameButton.setOpaque(false);
		secondNameButton.setHorizontalTextPosition(SwingConstants.CENTER);
		
		BImg2 = new ImageIcon("BBB.png");
		Image Btmp2 = BImg2.getImage().getScaledInstance(95, 40, Image.SCALE_SMOOTH);
		BImg2 = new ImageIcon(Btmp2);
		
		secondNameButton.setIcon(BImg2);
		
		//frame add
		frame.add(start);
		frame.add(first);
		frame.add(firstName);
		frame.add(firstNameButton);
		frame.add(second);
		frame.add(secondName);
		frame.add(secondNameButton);
		frame.getContentPane().add(background);
		
		frame.revalidate();
		frame.repaint();
		
	}
	
	
	
	public static void main(String[] args) {	
		new StartScreen();	
	}

	
	
	
}
	