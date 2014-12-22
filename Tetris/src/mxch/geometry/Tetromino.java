package mxch.geometry;

import java.awt.Color;
import java.util.ArrayList;

import mxch.tetris.Tetris;

public abstract class Tetromino {
	public static enum Type {I,O,T,S,Z,J,L};
	public static enum Orientation {UP, DOWN, LEFT, RIGHT};
	private Type type;
	private Orientation orient;
	private Color color;
	private ArrayList<Block> blocks;
	private Tetris tetris;
	
	Tetromino(Type type, Color color, Tetris tetris) {
		this.type = type;
		this.color = color;
		this.tetris = tetris;
		this.orient = Orientation.UP;
		blocks = new ArrayList<Block>();
	}
	
	public Type getType() {
		return type;
	}
	
	public Orientation getOrientation() {
		return orient;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void addBlock(Block b) {
		blocks.add(b);
	}
	
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
	
	public void setOrientation(Orientation o) {
		this.orient = o;
	}
	
	public boolean onTop(ArrayList<Block> boardBlocks) {
		for (Block b1 : blocks) {
			for (Block b2 : boardBlocks) {
				if (b1.onTop(b2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean onBoardBottom() {
		for (Block b : blocks) {
			if (b.onBoardBottom()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean aboveBoard() {
		for (Block b : blocks) {
			if (b.aboveBoard()) {
				return true;
			}
		}
		return false;
	}
	
	public void moveDownOne() {
		for (Block b: blocks) {
			b.moveDownOne();
		}
	}
	public void moveRightOne() {
		for (Block b: blocks) {
			b.moveRightOne();
		}
	}
	public void moveLeftOne() {
		for (Block b: blocks) {
			b.moveLeftOne();
		}
	}
	
	public boolean canMoveDownOne(ArrayList<Block> boardBlocks) {
		for (Block b : blocks) {
			if (!b.canMoveDownOne(boardBlocks)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean canMoveRightOne(ArrayList<Block> boardBlocks) {
		for (Block b : blocks) {
			if (!b.canMoveRightOne(boardBlocks)) {
				System.out.println("Cannot move right.");
				return false;
			}
		}
		return true;
	}
	
	public boolean canMoveLeftOne(ArrayList<Block> boardBlocks) {
		for (Block b : blocks) {
			if (!b.canMoveLeftOne(boardBlocks)) {
				System.out.println("Cannot move left.");
				return false;
			}
		}
		return true;
	}
	
	public abstract void rotateR();
	public abstract void rotateL();
	public abstract boolean canRotateR(ArrayList<Block> boardBlocks);
	public abstract boolean canRotateL(ArrayList<Block> boardBlocks);
	
	public String toString() {
		return color.toString();
	}
	
}
