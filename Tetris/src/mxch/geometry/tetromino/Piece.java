package mxch.geometry.tetromino;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import mxch.geometry.block.FullBlock;
import mxch.tetris.Board.Movement;
import mxch.tetris.Main;

public abstract class Piece {
	public static enum PieceType {I,O,T,S,Z,J,L};
	public static enum Orientation {UP, DOWN, LEFT, RIGHT};
	private PieceType type;
	private Orientation orient;
	private Color color;
	private ArrayList<FullBlock> blocks;

	Piece(PieceType type, Color color) {
		this.type = type;
		this.color = color;
		this.orient = Orientation.DOWN;
		blocks = new ArrayList<FullBlock>();
	}

	public static Piece getRandomPiece() {
		Random rand = new Random();
		int type = rand.nextInt(7);

		switch (type) {
		
		case 0: return new IPiece();
		case 1: return new OPiece();
		case 2: return new TPiece();
		case 3: return new SPiece();
		case 4: return new ZPiece();
		case 5: return new JPiece();
		case 6:	return new LPiece();
		
		default: return null; // never happens.
		}
	}

	public PieceType getType() {
		return type;
	}

	public Orientation getOrientation() {
		return orient;
	}

	public Color getColor() {
		return color;
	}

	public void addBlock(FullBlock b) {
		blocks.add(b);
	}

	public ArrayList<FullBlock> getBlocks() {
		return blocks;
	}

	public void setOrientation(Orientation o) {
		this.orient = o;
	}

	public void moveOne(Movement m) {
		switch (m) {
		case RIGHT_ONE:
			for (FullBlock b: blocks) {
				b.moveRightOne();
			}
			break;
		case LEFT_ONE:
			for (FullBlock b: blocks) {
				b.moveLeftOne();
			}
			break;
		case DOWN_ONE:
			for (FullBlock b: blocks) {
				b.moveDownOne();
			}
			break;
		}
	}
	
	public void setColor(Color c) {
		this.color = c;
		for (FullBlock b : blocks) {
			b.setColor(c);
		}
	}
	
	public abstract Piece getGhostPiece();
	public abstract void rotateR();
	public abstract void rotateL();
	public abstract Piece getRotateR();
	public abstract Piece getRotateL();

}
