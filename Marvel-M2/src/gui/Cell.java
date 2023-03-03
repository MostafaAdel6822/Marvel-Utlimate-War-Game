package gui;

import java.awt.Color;

import javax.swing.JButton;

public class Cell extends JButton{
	
	public Cell() {
		this.setFocusable(false);
		this.setOpaque(false);
		this.setBackground(new Color(0,0,0,0));
		this.setVisible(true);
	}

}
