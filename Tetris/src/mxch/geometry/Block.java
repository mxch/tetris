package mxch.geometry;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import mxch.tetris.Tetris;

public class Block extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
	private Tetris tetris;
	private int xcoord, ycoord, x, y, dx, dy;

	public Block(int xcoord, int ycoord, Tetris tetris, Color color) {
		super(xcoord * tetris.getWidth()/10, ycoord * tetris.getHeight()/20, 
				tetris.getWidth()/10, tetris.getHeight()/20);
		this.tetris = tetris;
		this.color = color;
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		this.x = xcoord * tetris.getWidth()/10;
		this.y = ycoord * tetris.getWidth()/20;
		this.dx = tetris.getWidth()/10;
		this.dy = tetris.getHeight()/20;
		//tetris.getStatusBar().getY();
	}
	
	/**
	 * Copy Constructor
	 * @return
	 */
	public Block(Block b) {
		super(b.getIntX(), b.getIntY(), b.getDx(), b.getDy());
		this.tetris = b.getTetris();
		this.color = b.getColor();
		this.xcoord = b.getXcoord();
		this.ycoord = b.getYcoord();
		this.x = b.getIntX();
		this.y = b.getIntY();
		this.dx = b.getDx();
		this.dy = b.getDy();
		//tetris.getStatusBar().getY();
	}
	
	public int getXcoord() {
		return xcoord;
	}
	
	public int getYcoord() {
		return ycoord;
	}
	
	public int getIntX() {
		return x;
	}
	
	public int getIntY() {
		return y;
	}
	
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}
	
	public Tetris getTetris() {
		return tetris;
	}

	public Color getColor() {
		return color;
	}

	public boolean onTop(Block b) {
		if (this.getX() == b.getX() &&
				((this.getY() + dy == b.getY()) || 
						(this.getY() - dy == b.getY()))) {
			return true;
		}
		return false;			
	}

	public boolean onBoardBottom() {
		if ((this.getY() + dy) == tetris.getHeight() - dy * 2) {
			return true;
		}
		return false;
	}

	public boolean aboveBoard() {
		if (this.getY() <= 0) {
			return true;
		}
		return false;
	}

	private void moveUpOne() {
		this.translate(-dx, 0);
	}

	public void moveDownOne() {
		System.out.println("Moving block down.");
		this.translate(0, dy);
	}

	public void moveRightOne() {
		System.out.println("Moving block right.");
		this.translate(dx, 0);
	}

	public void moveLeftOne() {
		System.out.println("Moving block left.");
		this.translate(-dx, 0);
	}

	public boolean canMoveDownOne(ArrayList<Block> boardBlocks) {
		//Block temp = new Block(this);
		for (Block b : boardBlocks) {
			//temp.moveDownOne();
			if (this.getY() + dy == b.getY() && this.getX() == b.getX()) {
				return false;
			}
		}
		return true;
	}

	public boolean canMoveRightOne(ArrayList<Block> boardBlocks) {
		// check if right side is touching edge
		if (this.getX() + dx == tetris.getWidth()) {
			System.out.println("Block touching edge.");
			return false;
		}
		//Block temp = new Block(this);
		for (Block b : boardBlocks) {
			//temp.moveRightOne();
			if (this.getX() + dx == b.getX() && this.getY() == b.getY()) {
				System.out.println("Block touching Block");
				return false;
			}
		}
		return true;
	}

	public boolean canMoveLeftOne(ArrayList<Block> boardBlocks) {
		// check if left side is touching edge
		if (this.getX() == 0) {
			return false;
		}
		//Block temp = new Block(this);
		for (Block b : boardBlocks) {
			//temp.moveLeftOne();
			if (this.getX() == b.getX() + dx && this.getY() == b.getY()) {
				return false;
			}
		}
		return true;
	}
}
