package mxch.geometry.block;

import java.awt.Color;
import java.awt.Rectangle;

public abstract class Block extends Rectangle {
	public enum BlockType{EMPTY, FULL};
	protected BlockType type;
	
	public Block(BlockType t, int x, int y, int w, int h) {
		super(x,y,w,h);
		this.type = t;
	}
	
	public BlockType getType() {
		return type;
	}
	
	public abstract int getXcoord();
	public abstract int getYcoord();
	public abstract int getIntX();
	public abstract int getIntY();
	public abstract int getDx();
	public abstract int getDy();
	public abstract Color getColor();
	public abstract void moveDownOne();
	public abstract void moveRightOne();
	public abstract void moveLeftOne();
	public abstract void moveBlock(int xChange, int yChange);
	public abstract void setColor(Color c);
}
