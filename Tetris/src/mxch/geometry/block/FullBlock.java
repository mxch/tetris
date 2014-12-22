package mxch.geometry.block;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import mxch.tetris.Main;

public class FullBlock extends Block {

	protected Color color;
	protected int dx, dy;

	public FullBlock(int intX, int intY, int dx, int dy, Color color) {
		super(BlockType.FULL, intX * dx, intY * dy, dx, dy);
		this.dx = dx;
		this.dy = dy;
		this.color = color;
	}
	
	/**
	 * Copy Constructor
	 * @return
	 */
	public FullBlock(FullBlock b) {
		super(BlockType.FULL, b.getIntX(), b.getIntY(), b.getDx(), b.getDy());
		this.color = b.getColor();
	}
	
	public int getXcoord() {
		return (int) this.getX();
	}
	
	public int getYcoord() {
		return (int) this.getY();
	}
	
	public int getIntX() {
		return (int) (this.getX()/dx);
	}
	
	public int getIntY() {
		return (int) (this.getY()/dy);
	}
	
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}

	@Override
	public Color getColor() {
		return color;
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

	@Override
	public void moveBlock(int xChange, int yChange) {
		this.translate(xChange * dx, yChange * dy);
	}

	@Override
	public void setColor(Color c) {
		this.color = c;
	}
}
