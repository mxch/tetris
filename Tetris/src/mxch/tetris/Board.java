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

import mxch.geometry.Block;
import mxch.geometry.IPiece;
import mxch.geometry.JPiece;
import mxch.geometry.LPiece;
import mxch.geometry.OPiece;
import mxch.geometry.SPiece;
import mxch.geometry.TPiece;
import mxch.geometry.Tetromino;
import mxch.geometry.ZPiece;

public class Board extends JPanel implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer timer = new Timer(400, this);
	private ArrayList<Block> blocks;
	private Tetris tetris;
	private Tetromino currPiece;
	//private final int boardWidth = 10, boardHeight = 20;

	// boolean values
	// private boolean gamePaused;
	private boolean gameOver = false;


	public Board(Tetris tetris) {
		this.tetris = tetris;
		//boardLayout = new int[boardWidth][boardHeight];
		blocks = new ArrayList<Block>();
		//gamePaused = false;
		currPiece = generateNewPiece();
		
		//KeyListener l = new MyKeyListener();
		addKeyListener(this);
		setFocusable(true);
		
	}

	public void start() {
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// check if current piece is already in place
		if (isPieceInPlace()) {
			// check if the game is over.
			if (isGameOver()) {
				/* Test */
				System.out.println("Game is Over, exiting.");
				gameOver = true;
				exitGame();
			} 
			/* Test */
			System.out.println("Piece in Place, generating new Piece.");
			// add the blocks of the current piece to the board
			if (currPiece != null) {
				blocks.addAll(currPiece.getBlocks());
			}
			// create new piece
			currPiece = generateNewPiece();
		} 
		// else, move the current piece down one line
		else {
			/* Test */
			System.out.println("Piece not in Place, moving Piece down.");
			moveDownOne();
		}

		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("Key Pressed.");
		int keyCode = arg0.getKeyCode();
		switch(keyCode) {
		case KeyEvent.VK_UP: break;
		case KeyEvent.VK_DOWN: break;
		case KeyEvent.VK_LEFT: 
			moveLeftOne();
			System.out.println("Moving left.");
			break;
		case KeyEvent.VK_RIGHT: 
			moveRightOne();
			break;
		}
		
		repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (currPiece != null) {
			// paint current piece.
			for (Block b : currPiece.getBlocks()) {
				paintBlock(g, b);
			}
		}
		// paint all blocks.
		for (Block b : blocks) {
			paintBlock(g, b);
		}
	}

	private void paintBlock(Graphics g, Block b) {
		g.setColor(b.getColor());
		int x = (int) b.getX();
		int y = (int) b.getY();
		int w = (int) b.getWidth();
		int h = (int) b.getHeight();
		g.fillRect(x,y,w,h);
	}

	/**
	 * This method generates a random Tetromino.
	 * @return Randomly generated Tetromino
	 */
	private Tetromino generateNewPiece() {
		Random rand = new Random();
		int type = rand.nextInt(7);

		switch (type) {
		case 0: return new IPiece(tetris);
		case 1: return new OPiece(tetris);
		case 2: return new TPiece(tetris);
		case 3: return new SPiece(tetris);
		case 4: return new ZPiece(tetris);
		case 5: return new JPiece(tetris);
		case 6:	return new LPiece(tetris);
		default: return null; // never happens.
		}
	}

	private boolean isPieceInPlace() {
		// check if any of the blocks of the current piece
		// are resting on a block on the board.
		if (currPiece.onTop(blocks) || currPiece.onBoardBottom()) {
			return true;
		}

		return false;
	}

	private boolean isGameOver() {
		// check if the piece is in place
		// and if any of the current piece is above the board
		if (currPiece != null && currPiece.aboveBoard()) {
			return true;
		}
		return false;

	}

	private void moveDownOne() {
		currPiece.moveDownOne();
	}
	
	private void moveLeftOne() {
		currPiece.moveLeftOne();
	}
	
	private void moveRightOne() {
		currPiece.moveRightOne();
	}
	
	private void exitGame() {
		System.out.println("Game over.");
		System.exit(0);
	}

	private boolean isEmpty() {
		return blocks.size() == 0;
	}


}
