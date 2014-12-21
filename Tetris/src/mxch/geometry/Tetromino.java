package mxch.geometry;

import java.awt.Color;
import java.util.ArrayList;

import mxch.tetris.Tetris;

public abstract class Tetromino {
	public static enum Type {I,O,T,S,Z,J,L};
	private Type type;
	private Color color;
	private ArrayList<Block> blocks;
	private Tetris tetris;
	
	Tetromino(Type type, Color color, Tetris tetris) {
		this.type = type;
		this.color = color;
		this.tetris = tetris;
		blocks = new ArrayList<Block>();
	}
	
	public Type getType() {
		return type;
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
	
	public abstract void rotateR();
	public abstract void rotateL();
	
}
