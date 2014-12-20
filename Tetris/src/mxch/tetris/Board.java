package mxch.tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Timer timer = new Timer(400, this);
	
	public void start() {
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		Random rand = new Random();
		int red = rand.nextInt(255);
		int green = rand.nextInt(255);
		int blue = rand.nextInt(255);
		Color randomColor = new Color(red,green,blue);
		g.setColor(randomColor);
		g.fillRect(0, 0, 200, 400);
	}
	

}
