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
import mxch.geometry.tetromino.Piece.PieceType;
import mxch.geometry.tetromino.SPiece;
import mxch.geometry.tetromino.TPiece;
import mxch.geometry.tetromino.Piece;
import mxch.geometry.tetromino.ZPiece;
import mxch.tetris.Board.Movement;

/**
 * The GameEngine handles game flow and paints the board onto the screen.
 * @author maxchiang
 *
 */
public class GameEngine extends JPanel implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Main tetris;
	private Piece currPiece;
	private Piece ghostPiece;
	private Board board;
	private int startingTimer = 1000;
	private int currentTimer;
	private Timer timer;
	private int timerOffset = 200;
	private int level;
	private int linesCleared;
	private int score;

	private boolean gamePaused = true;
	private boolean gameOver = false;

	// OPTIONS
	private boolean useGhost = true;


	public GameEngine(Main tetris) {
		this.tetris = tetris;
		addKeyListener(this);
		setFocusable(true);

	}

	public void start() {
		this.timer = new Timer(startingTimer, this);
		this.currentTimer = startingTimer;
		this.board = new Board();
		this.currPiece = Piece.getRandomPiece();
		level = 0;
		linesCleared = 0;
		score = 0;
		board.addPiece(currPiece);
	}

	private void restart() {
		pause();
		this.timer = new Timer(startingTimer, this);
		this.currentTimer = startingTimer;
		this.board = new Board();
		this.currPiece = Piece.getRandomPiece();
		score = 0;
		gameOver = false;
	}

	private void resume() {
		gamePaused = false;
		displayGhostPiece();
		timer.start();
	}

	private void pause() {
		gamePaused = true;
		timer.stop();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// check if current piece is already in place
		if (board.isPieceInPlace(currPiece)) {
			// check if there are lines to clear.
			int numLines = board.clearLines();
			if (numLines != 0) {
				linesCleared += numLines;
				updateScore(numLines);
				// check for level up.
				if (linesCleared % 10 == 0) { // level up every 10 lines cleared
					/*TEST*/
					//System.out.println("LEVEL UP");
					levelUp();
				}
			}
			// check if the game is over.
			if (isGameOver()) {
				/*TEST*/
				System.out.println("GAME IS OVER.");
				gameOver();
			} else {
				currPiece = Piece.getRandomPiece(); // create new current piece
				board.addPiece(currPiece); // add new current piece to board
				displayGhostPiece();
			}

		} 
		// else, move the current piece down one line
		else {
			displayGhostPiece();
			board.move(currPiece, Movement.DOWN_ONE);
		}

		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		/*TEST*/
		System.out.println("Key Pressed");
		int keyCode = arg0.getKeyCode();
		switch(keyCode) {
		case KeyEvent.VK_UP:
			if (!gamePaused && !gameOver) {
				board.move(currPiece, Movement.ROTATE_R);
				displayGhostPiece();
			}
			break;
		case KeyEvent.VK_SHIFT:
			if (!gamePaused && !gameOver) {
				board.move(currPiece, Movement.ROTATE_L);
				displayGhostPiece();
			}
			break;
		case KeyEvent.VK_DOWN: 
			if (!gamePaused && !gameOver) {
				board.move(currPiece, Movement.DOWN_ONE);
				displayGhostPiece();
			}
			break;
		case KeyEvent.VK_LEFT: 
			if (!gamePaused && !gameOver) {
				board.move(currPiece, Movement.LEFT_ONE);
				displayGhostPiece();
			}
			break;
		case KeyEvent.VK_RIGHT: 
			if (!gamePaused && !gameOver) {
				board.move(currPiece, Movement.RIGHT_ONE);
				displayGhostPiece();
			}
			break;
		case KeyEvent.VK_SPACE:
			if (!gamePaused && !gameOver) {
				board.move(currPiece, Movement.DOWN_ALL);
				// need to run actionPerformed to prevent dropped piece from moving
				actionPerformed(new ActionEvent(this, 0, null)); 
			}
			break;
		case KeyEvent.VK_P:
			pause();
			break;
		case KeyEvent.VK_S:
			resume();
			break;
		case KeyEvent.VK_R:
			restart();
			break;
		case KeyEvent.VK_E:
			exitGame();
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		/*
		// paint background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, tetris.getWidth(), tetris.getHeight());
		 */

		// paint all blocks.

		ArrayList<FullBlock> blocks = board.getBlocks();
		/*TEST*/
		System.out.println("Painting Board. Blocks: " + blocks.size());
		for (FullBlock b : blocks) {
			paintBlock(g, b);
		}
	}

	private void paintBlock(Graphics g, FullBlock b) {
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

	private void levelUp() {
		level++;
		if (currentTimer > 0) {
			timer = new Timer(currentTimer - timerOffset * level, this);
		}
		timer.start();
	}

	/**
	 * Scoring based on the original Nintendo Scoring system.
	 * http://tetris.wikia.com/wiki/Scoring
	 * @param numLines
	 */
	private void updateScore(int numLines) {
		switch (numLines) {
		case 1:
			score += 40*(level+1);
			break;
		case 2:
			score += 100*(level+1);
			break;
		case 3:
			score += 300*(level+1);
			break;
		case 4:
			score += 1200*(level+1);
			break;
		}
		tetris.getStatusBar().setText("Level: " + (level+1) + " Score: " + score);
		repaint();
	}

	private void displayGhostPiece() {
		if (useGhost) {
			/*TEST*/
			System.out.println("Getting ghostpiece.");
			ghostPiece = currPiece.getGhostPiece();
			board.updateGhostPiece(currPiece, ghostPiece);
			/*TEST*/
			System.out.println("Ghostpiece got.");

			// dirty way to clear ghost blocks
			if (board.isPieceInPlace(currPiece)) {
				board.clearGhostBlocks();
			}
		}
	}

	private boolean isGameOver() {
		return board.isPieceInPlace(currPiece) && board.isPieceOnTopEdge(currPiece);
	}

	private void gameOver() {
		tetris.getStatusBar().setText("GAME OVER - Lines: " + 
				linesCleared +" Score: " + score);
		gameOver = true;
		pause();
	}

	private void exitGame() {
		System.exit(0);
	}
}
