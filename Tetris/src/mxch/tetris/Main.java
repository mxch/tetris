package mxch.tetris;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class Main extends JFrame {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JLabel statusbar;
	private int width = 200, height = 400 + 40; // offset for status bar

	public Main() {
		initUI();
	}

	private void initUI() {

		statusbar = new JLabel("Welcome to Tetris.");
		add(statusbar, BorderLayout.SOUTH);
		GameEngine board = new GameEngine(this);
		add(board);
		board.start();

		setSize(width, height);
		setTitle("Tetris");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public JLabel getStatusBar() {
		return statusbar;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Main game = new Main();
				game.setUndecorated(true);
				game.setVisible(true);
			}
		});
	}
}
