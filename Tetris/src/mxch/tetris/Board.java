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
	
	private ArrayList<ArrayList<Block>> boardLayout;
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
		// check if there are lines to clear.
		
		
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
				blocks.addAll(0, currPiece.getBlocks()); // append to the front to paint first blocks last.
				// add blocks to board layout
				//for (Bloc)
			}
			// create new piece
			currPiece = generateNewPiece();
		} 
		// else, move the current piece down one line
		else {
			/* Test */
			System.out.println("Piece not in Place, moving " + currPiece.toString() + " down.");
			moveDownOne();
		}

		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("Key Pressed.");
		int keyCode = arg0.getKeyCode();
		switch(keyCode) {
		case KeyEvent.VK_UP: 
			break;
		case KeyEvent.VK_DOWN: 
			break;
		case KeyEvent.VK_LEFT: 
			moveLeftOne();
			System.out.println("Moving left.");
			break;
		case KeyEvent.VK_RIGHT: 
			moveRightOne();
			System.out.println("Moving Right.");
			break;
		case KeyEvent.VK_SPACE:
			moveDownAll();
			System.out.println("Dropping block.");
			break;
		case KeyEvent.VK_P:
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

		// paint all blocks.
		for (Block b : blocks) {
			paintBlock(g, b);
		}
		
		if (currPiece != null) {
			// paint current piece.
			for (Block b : currPiece.getBlocks()) {
				paintBlock(g, b);
			}
		}
	}

	private void paintBlock(Graphics g, Block b) {
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
		default: return new IPiece(tetris); //return null; // never happens.
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
	
	private void rotateRight() {
		if (!isPieceInPlace() && currPiece.canRotateR(blocks)) currPiece.rotateR();
	}
	
	private void rotateL() {
		if (!isPieceInPlace() && currPiece.canRotateL(blocks)) currPiece.rotateL();
	}
	
	private void moveDownAll() {
		while(!isPieceInPlace() && currPiece.canMoveDownOne(blocks)) currPiece.moveDownOne();
	}

	private void moveDownOne() {
		if (!isPieceInPlace() && currPiece.canMoveDownOne(blocks)) currPiece.moveDownOne();
	}
	
	private void moveLeftOne() {
		if (!isPieceInPlace() && currPiece.canMoveLeftOne(blocks)) currPiece.moveLeftOne();
	}
	
	private void moveRightOne() {
		if (!isPieceInPlace() && currPiece.canMoveRightOne(blocks)) currPiece.moveRightOne();
	}
	
	private void exitGame() {
		System.out.println("Game over.");
		System.exit(0);
	}

	/*
	private boolean isEmpty() {
		return blocks.size() == 0;
	}
	*/


}
