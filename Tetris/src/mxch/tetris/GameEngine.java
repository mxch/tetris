package mxch.tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import mxch.geometry.block.Block;
import mxch.geometry.block.FullBlock;
import mxch.geometry.tetromino.IPiece;
import mxch.geometry.tetromino.JPiece;
import mxch.geometry.tetromino.LPiece;
import mxch.geometry.tetromino.OPiece;
import mxch.geometry.tetromino.SPiece;
import mxch.geometry.tetromino.TPiece;
import mxch.geometry.tetromino.Piece;
import mxch.geometry.tetromino.ZPiece;
import mxch.tetris.Board.Movement;

public class GameEngine extends JPanel implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Main tetris;
	private Piece currPiece;
	private Board board;
	private Timer timer = new Timer(400, this);

	//private final int boardWidth = 10, boardHeight = 20;

	// boolean values
	// private boolean gamePaused;
	private boolean gameOver = false;


	public GameEngine(Main tetris) {
		this.tetris = tetris;
		this.board = new Board();
		this.currPiece = Piece.getRandomPiece();

		//KeyListener l = new MyKeyListener();
		addKeyListener(this);
		setFocusable(true);

	}

	public void start() {
		board.add(currPiece);
		repaint();
		timer.start();
	}

	public void pause() {
		timer.stop();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// check if current piece is already in place
		if (board.isPieceInPlace(currPiece)) {
			// check if there are lines to clear.
			int numLines = board.clearLines();
			if (numLines != 0) {
				repaint();
			}
			//System.out.println("Piece in place.");
			// check if the game is over.
			if (isGameOver()) {
				exitGame();
			}
			
			// create new current piece
			currPiece = Piece.getRandomPiece();
			
			// add new current piece to board
			board.add(currPiece);
		} 
		// else, move the current piece down one line
		else {
			//System.out.println("Moving piece down.");
			board.move(currPiece, Movement.DOWN_ONE);
		}

		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		//System.out.println("Key Pressed.");
		int keyCode = arg0.getKeyCode();
		switch(keyCode) {
		case KeyEvent.VK_UP: 
			break;
		case KeyEvent.VK_SHIFT:
			break;
		case KeyEvent.VK_DOWN: 
			board.move(currPiece, Movement.DOWN_ONE);
			break;
		case KeyEvent.VK_LEFT: 
			board.move(currPiece, Movement.LEFT_ONE);
			break;
		case KeyEvent.VK_RIGHT: 
			board.move(currPiece, Movement.RIGHT_ONE);
			break;
		case KeyEvent.VK_SPACE:
			board.move(currPiece, Movement.DOWN_ALL);
			// need to run actionPerformed to prevent dropped piece from moving
			actionPerformed(new ActionEvent(this, 0, null)); 
			break;
		case KeyEvent.VK_P:
			break;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}
	
	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void paint(Graphics g) {
		//System.out.println("Painting board.");
		super.paint(g);

		// paint all blocks.
		//System.out.println("Getting blocks.");
		ArrayList<FullBlock> blocks = board.getBlocks();
		//System.out.println("Num of blocks: " + blocks.size());
		for (FullBlock b : blocks) {
			paintBlock(g, b);
		}
	}

	private void paintBlock(Graphics g, FullBlock b) {
		//System.out.println("Painting block.");
		int x = (int) b.getX();
		int y = (int) b.getY();
		int w = (int) b.getWidth();
		int h = (int) b.getHeight();

		// color the border
		g.setColor(Color.BLACK);
		g.fillRect(x,y,w,h);

		// color the block
		g.setColor(b.getColor());
		g.fillRect(x-2,y-2,w-2,h-2);
	}

	private boolean isGameOver() {
		return board.isPieceInPlace(currPiece) && board.isPieceOnTopEdge(currPiece);

	}

	private void exitGame() {
		System.out.println("Game over.");
		System.exit(0);
	}
}
