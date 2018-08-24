package com.hao.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

	class AdaptivePanel extends JPanel {
	private static final long serialVersionUID = -4410794262945788084L;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//…Ë÷√±≥æ∞
		Image image = new ImageIcon("background.jpg").getImage();
		g.drawImage(image, 0, 0, this);
	}

		

}
