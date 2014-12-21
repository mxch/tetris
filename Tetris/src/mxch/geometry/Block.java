package mxch.geometry;

import java.awt.Color;
import java.awt.Rectangle;

import mxch.tetris.Tetris;

public class Block extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
	private Tetris tetris;
	private int x, y, dx, dy;
	
	public Block(int xcoord, int ycoord, Tetris tetris, Color color) {
		super(xcoord * tetris.getWidth()/10, ycoord * tetris.getHeight()/20, 
				tetris.getWidth()/10, tetris.getHeight()/20);
		this.tetris = tetris;
		this.color = color;
		this.x = xcoord * tetris.getWidth()/10;
		this.y = ycoord * tetris.getWidth()/20;
		this.dx = tetris.getWidth()/10;
		this.dy = tetris.getHeight()/20;
		tetris.getStatusBar().getY();
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
		if ((this.getY() + dy) == tetris.getHeight() - dy*2) {
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
	
	public void moveDownOne() {
		this.translate(0, dy);
	}
	
	public void moveRightOne() {
		this.translate(dx, 0);
	}
	
	public void moveLeftOne() {
		this.translate(-dx, 0);
	}
}
