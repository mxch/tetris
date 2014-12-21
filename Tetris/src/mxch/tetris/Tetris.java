package mxch.tetris;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class Tetris extends JFrame {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JLabel statusbar;
	private int width, height;

	public Tetris() {
		width = 200;
		height = 400;
		initUI();
	}

	private void initUI() {

		statusbar = new JLabel("Press S to start, P to pause.");
		add(statusbar, BorderLayout.SOUTH);
		Board board = new Board(this);
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
				Tetris game = new Tetris();
				game.setVisible(true);
			}
		});
	}
}
